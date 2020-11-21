package mx.com.rc.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import mx.com.rc.dto.Arbol;
import mx.com.rc.dto.OpcionMenu;
import mx.com.rc.entidades.Opcion;
import mx.com.rc.entidades.Permiso;
import mx.com.rc.entidades.Rol;
import mx.com.rc.entidades.RolUsuario;
import mx.com.rc.rest.IGestorNegocioMenu;

@ApplicationScoped
@Persistor
public class GestorNegocioMenu implements IGestorNegocioMenu{
	
	@Inject
	private IGestorDatosMenu gestorDatosMenu;

	@Override
	public Arbol getArbolMenu(List<RolUsuario> roles) {
		Arbol menu = new Arbol();
		System.out.println("roles: " + roles.toString());
		List<String> sinNodosRepetidos = new ArrayList<String>();
		try {
			
			for(RolUsuario rolUsuarioI : roles) {
				System.out.println("Rol: " + rolUsuarioI.getDescripcion());
				Rol rol = rolUsuarioI.getRol();
				
				for(Permiso permiso : rol.getPermisos()) {
					System.out.println("id opcion: " + permiso.getOpcion().getIdOpcion());
					String idOpcion = permiso.getOpcion().getIdOpcion();
					
					List<String> listaConNodos = this.getListaConNodos(idOpcion);
					
					System.out.println("La lista de nodos: " + listaConNodos.toString());
					sinNodosRepetidos = this.agregarNodos(listaConNodos, sinNodosRepetidos);		
				}		
			}
			
			System.out.println("Lista nuevos nodos: " + sinNodosRepetidos.toString());
			menu = this.construirArbol(sinNodosRepetidos, menu);
			
			return menu;
			
		} catch (NegocioException nEx) {
			
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getArbolMenu(List<RolUsuario> roles)");
			detEx.put("msg", nEx.getMessage());
			NegocioException ne = ManejadorErrNegocio.crearEx(detEx, nEx);
			throw ne;
		}
	}

	private Arbol construirArbol(List<String> sinNodosRepetidos, Arbol menu) {
		
		List<Opcion> listaOpciones = this.gestorDatosMenu.leerOpciones(sinNodosRepetidos);
		System.out.println("listaOpciones: " + listaOpciones.toString());
		List<OpcionMenu> listaOpcionesPermitidas = new ArrayList<OpcionMenu>();
		List<OpcionMenu> ramas = new ArrayList<OpcionMenu>();
		try {
			for(Opcion opcionI:listaOpciones) {
				OpcionMenu nuevaOpcion = new OpcionMenu(opcionI.getIdOpcion(), opcionI.getNombreOpcion(), opcionI.isParametros(), opcionI.getUrl());
				if(opcionI.getOpcionPadre() != null) {
					nuevaOpcion.setIdOpcionPadre(opcionI.getOpcionPadre().getIdOpcion());
				}
				listaOpcionesPermitidas.add(nuevaOpcion);
				
			}
			System.out.println("listaOpcionesPermitidas: "+listaOpcionesPermitidas.toString());
			//menu.getRamas(listaOpcionesPermitidas);
			menu.setMenu(listaOpcionesPermitidas);
			
		
			//menu.setRamas(ramas);
			return menu;
		} catch (NegocioException nEx) {
			
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getArbolMenu(List<RolUsuario> roles)");
			detEx.put("msg", nEx.getMessage());
			NegocioException ne = ManejadorErrNegocio.crearEx(detEx, nEx);
			throw ne;
		}
	}

	private List<String> getListaConNodos(String idOpcion) {
		String[] listaNodos = null;
		List<String> listaConNodos = new ArrayList<String>();
		try {
			String idOpcionNodo = "";
			listaNodos = idOpcion.split("\\.");
			
			for(int i = 0 ; i< listaNodos.length; i++) {
				
				if(i < listaNodos.length) {
					
					idOpcionNodo += listaNodos[i];
					
					listaConNodos.add(idOpcionNodo);
					
					idOpcionNodo += ".";
					
				}
			}
			return listaConNodos;
		} catch (NegocioException nEx) {
			
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getArbolMenu(List<RolUsuario> roles)");
			detEx.put("msg", nEx.getMessage());
			NegocioException ne = ManejadorErrNegocio.crearEx(detEx, nEx);
			throw ne;
		}
		
	}

	private List<String> agregarNodos(List<String> listaConNodos, List<String> sinNodosRepetidos) {
		try {
			for(int i = 0 ; i< listaConNodos.size(); i++) {
				
				sinNodosRepetidos.add(listaConNodos.get(i));
			}
			Set<String> nuevosNodos = new HashSet<String>(sinNodosRepetidos);
			
			List<String> lista = new ArrayList<String>(nuevosNodos);
			
			return lista;
		} catch (NegocioException nEx) {
			
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getArbolMenu(List<RolUsuario> roles)");
			detEx.put("msg", nEx.getMessage());
			NegocioException ne = ManejadorErrNegocio.crearEx(detEx, nEx);
			throw ne;
		}
	}

}
