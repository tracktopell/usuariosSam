package mx.com.rc.negocio;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import mx.com.rc.entidades.Empresa;
import mx.com.rc.entidades.Opcion;
import mx.com.rc.entidades.Permiso;
import mx.com.rc.entidades.Rol;
import mx.com.rc.entidades.Usuario;
import mx.com.rc.entidades.RolUsuario;
import mx.com.rc.rest.IGestorNegocioUsuario;

@ApplicationScoped
@Persistor
public class GestorNegocioUsuario implements IGestorNegocioUsuario {
	
	@Inject
	private IGestorDatosUsuario gestorDatos;

	@Override
	public List<Usuario> getUsuarios(boolean conRoles) {
		List<Usuario> usuarios = null;
		try {
			usuarios = this.gestorDatos.leerUsuarios(conRoles);
			
			return usuarios;
		} catch (NegocioException nEx) {
			
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getUsuarios(conRoles: " + conRoles +")");
			detEx.put("msg", nEx.getMessage());
			NegocioException ne = ManejadorErrNegocio.crearEx(detEx, nEx);
			throw ne;
		}
	}
	@Override
	public Usuario getUsuario(String idUsuario, boolean conRoles) {
		
		Usuario usuario = null;
		Set <Opcion> arbolMenuConOpciones = new HashSet<Opcion>();
		try {
			
			usuario = this.gestorDatos.leerUsuario(idUsuario, conRoles);
			
			arbolMenuConOpciones = this.getArbolMenuConOpciones(usuario);
			
			Usuario usuarioConPermisos = this.reemplazarPermisosAlusuario(usuario, arbolMenuConOpciones);
			
			
			
			return usuario;
			
		} catch (NegocioException nex) {
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getUsuario(idUsuario: " + idUsuario + ", conRoles: " + conRoles +")");
			detEx.put("msg", nex.getMessage());
			NegocioException nEx = ManejadorErrNegocio.crearEx(detEx, nex);
			throw nEx;
		}
		
	}
	private Usuario reemplazarPermisosAlusuario(Usuario usuario, Set<Opcion> arboldeOpciones) {
		// TODO Auto-generated method stub
		return null;
	}
	private Set <Opcion>  getArbolMenuConOpciones(Usuario usuario) {
		Set <Opcion> arbolOpciones = new HashSet<Opcion>();
		
		Set<String> setSinNodosRepetidos = new HashSet<String>();
		List<Opcion> listaOpciones = new ArrayList<Opcion>();
		List<String> listaConNodos = new ArrayList<String>();
		Opcion rama = new Opcion();
		Set<RolUsuario> roles = usuario.getRoles();
		for(RolUsuario usuarioRolI : roles) {
			Rol rol = usuarioRolI.getRol();
			
			List<String> listaNuevosNodos = new ArrayList<String>();
			
			System.out.println("ListaNuevosNodos: " + listaNuevosNodos.toString());
			for(Permiso opcionI : rol.getPermisos()) {
				String idOpcion = opcionI.getOpcion().getIdOpcion();
				
				List<String> listaConNodosXopcion = this.getListaConNodosXopcion(idOpcion);
				
				System.out.println("La lista de nodos: " + listaConNodosXopcion.toString());
				setSinNodosRepetidos = this.agregarNodos(listaConNodosXopcion, listaNuevosNodos);
				//listaOpcionesNodo = this.crearRama(listaNodos);
				//System.out.println("El nodo: " + listaConNodos.toString());
				
			}
			
			
		}
		
		System.out.println("El set de nodos: " + setSinNodosRepetidos.toString());
		arbolOpciones = this.construirArbol(setSinNodosRepetidos);
		
		
		
		return arbolOpciones;
	}
	
	
	
	private Set<Opcion> construirArbol(Set<String> sinNodosRepetidos) {
		
		Opcion nodoRaiz = new Opcion("nodoRaiz", null);
		
		System.out.println("Estoy aquí");
		Set<Opcion> opcionesPermitidas = new HashSet<Opcion>();
		Set<Opcion> arbolConHojas = new HashSet<Opcion>();
		Opcion rama = new Opcion("Padre", null);
		for(String nodo : sinNodosRepetidos) {
			Opcion opcionPermiso = this.gestorDatos.leerOpcionXidOpcion(nodo, false);
			//System.out.println("Opcion:" + opcionPermiso.getIdOpcion());
			opcionesPermitidas.add(opcionPermiso);
		}
		for(Opcion opcionI: opcionesPermitidas) {
			System.out.println("Las opciones en el set: " + opcionI.getIdOpcion());
			if(opcionI.getOpcionPadre() != null) {
				System.out.println("soy hijo de:" + opcionI.getOpcionPadre().getIdOpcion());
			}else {
				System.out.println("Soy padre:" + opcionI.getIdOpcion());
			}
		}
		return null;
	}
	private Set<String> agregarNodos(List<String> listaNodos, List<String> listaOpcionesNodo) {
		
		for(int i = 0 ; i< listaNodos.size(); i++) {
			
			listaOpcionesNodo.add(listaNodos.get(i));
		}
		Set<String> sinNodosRepetidos = new HashSet<String>(listaOpcionesNodo);
		
		
		
		return sinNodosRepetidos;
		
	}
	private Opcion agregarNodos2(List<String> listaNodos) {
		Opcion rama = new Opcion();
		for(int i = 0 ; i< listaNodos.size(); i++) {
			if(i==0) {
				rama = this.gestorDatos.leerOpcionXidOpcion(listaNodos.get(i), true);
				System.out.println("soy el papa: " +rama.getOpcionesMenu());
				
			}
			
		}
		
		return rama;
		
	}
	private List<Opcion> crearRama( List<String> listaNodos) {

		//Menu menu;
		Opcion rama = new Opcion("Nodo padre", null);
		
		Opcion copiaRama = rama;
		
		List<Opcion> listaRama = new ArrayList<Opcion>();
		for(int i = 0 ; i< listaNodos.size(); i++) {
			
			Opcion nodo = new Opcion();
			
			nodo.setIdOpcion(listaNodos.get(i));
			
			listaRama.add(nodo);
			/*System.out.println("La lista de opciones: " + listaNodos.get(i));
			//String rama = Integer.valueOf(parts[i]+1);
			System.out.println("Hoja: " + nodo.getIdOpcion());
			
			int indiceNodo = copiaRama.contieneNodo(nodo);
			System.out.println("el indice: " + indiceNodo);
			if(indiceNodo != -1) {
				System.out.println("indiceNodo: " + copiaRama.getOpcionesMenu().size());
				copiaRama.getOpcionesMenu();
			}else {
				copiaRama.insertarHijo(nodo);
				copiaRama = nodo; 
			}*/
			
		}
		return listaRama;
	}
	private List<String> getListaConNodosXopcion(String idOpcion) {
		String[] listaNodos = null;
		List<String> listaConNodos = new ArrayList<String>();
		System.out.println("El permiso rol: " + idOpcion);
		String idOpcionNodo = "";
		listaNodos = idOpcion.split("\\.");
		Opcion rama = new Opcion("Padre", null);
		for(int i = 0 ; i< listaNodos.length; i++) {
			
			if(i < listaNodos.length) {
				
				idOpcionNodo += listaNodos[i];
				
				listaConNodos.add(idOpcionNodo);
				
				idOpcionNodo += ".";
				
			}
		}
		return listaConNodos;
	}
	/*private List<String> getListaConNodos(String idOpcion) {
		
		System.out.println("El permiso rol: " + idOpcion);
		List<String> listaNodos = idOpcion.split("\\.");
		String part1 = listaNodos.get(0); // 123
		
		return listaNodos;
	}*/
	private RolUsuario obtenerUsuarioRol(Usuario usuario) {
		RolUsuario usuarioRol = null;
		for(RolUsuario usuarioRolI : usuario.getRoles()) {
			Rol rol = usuarioRolI.getRol();
			for(Permiso opcionI : rol.getPermisos()) {
				String idOpcion = opcionI.getOpcion().getIdOpcion();
				System.out.println("El permiso rol: " + idOpcion);
				String[] parts = idOpcion.split("\\.");
				String part1 = parts[0]; // 123
				
				Set<Opcion> unSet= new HashSet<Opcion>();
				for(int i = 0 ; i< parts.length; i++) {
					
					//String rama = Integer.valueOf(parts[i]+1);
					unSet = this.hacerArbol(unSet);
				}
				
			}
		}
		
		return usuarioRol;
	}
	private Set<Opcion> hacerArbol(Set<Opcion> unSet) {
		
		return null;
	}
	private Opcion buscarOpcionesHijas(String idOpcion, Set<Opcion> opcionesHijas) {
		Opcion opcion = this.gestorDatos.leerOpcionXidOpcion(idOpcion, false);
		//opcionesHijas.add(opcion);
		//opcion.setOpcionesMenu(opcionesHijas);
		return opcion;
	}
	@Override
	public List<Empresa> getEmpresas() {
		List<Empresa> empresas = null;
		try {
			empresas = this.gestorDatos.leerEmpresas();
			return empresas;
		} catch (NegocioException nEx) {
			
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getEmpresas()");
			detEx.put("msg", nEx.getMessage());
			NegocioException ne = ManejadorErrNegocio.crearEx(detEx, nEx);
			throw ne;
		}
	}
	@Override
	public Empresa getEmpresa(String idUname) {
		Empresa empresa = null;
		try {
			
			empresa = this.gestorDatos.leerEmpresa(idUname);
			return empresa;
			
		} catch (NegocioException nex) {
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getUsuario(idUsuario: " + idUname);
			detEx.put("msg", nex.getMessage());
			NegocioException nEx = ManejadorErrNegocio.crearEx(detEx, nex);
			throw nEx;
		}
	}
	
	
}
