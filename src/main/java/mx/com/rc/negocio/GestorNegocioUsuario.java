package mx.com.rc.negocio;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import mx.com.rc.dto.OpcionMenu;
import mx.com.rc.dto.UsuarioConMenu;

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
	
	private final static Logger LOGGER = Logger.getLogger(GestorNegocioUsuario.class.getName());
	
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
		
		try {
			usuario = this.gestorDatos.leerUsuario(idUsuario, conRoles);
			
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
			
			LOGGER.info("ListaNuevosNodos: " + listaNuevosNodos.toString());
			for(Permiso opcionI : rol.getPermisos()) {
				String idOpcion = opcionI.getOpcion().getIdOpcion();
				
				List<String> listaConNodosXopcion = this.getListaConNodosXopcion(idOpcion);
				
				LOGGER.info("La lista de nodos: " + listaConNodosXopcion.toString());
				setSinNodosRepetidos = this.agregarNodos(listaConNodosXopcion, listaNuevosNodos);
				//listaOpcionesNodo = this.crearRama(listaNodos);
				//LOGGER.info("El nodo: " + listaConNodos.toString());	
			}
		}
		LOGGER.info("El set de nodos: " + setSinNodosRepetidos.toString());
		arbolOpciones = this.construirArbol(setSinNodosRepetidos);
		return arbolOpciones;
	}
	
	private Set<Opcion> construirArbol(Set<String> sinNodosRepetidos) {
		
		Opcion nodoRaiz = new Opcion("nodoRaiz", null);
		
		LOGGER.info("Estoy aquí");
		Set<Opcion> opcionesPermitidas = new HashSet<Opcion>();
		Set<Opcion> arbolConHojas = new HashSet<Opcion>();
		Opcion rama = new Opcion("Padre", null);
		for(String nodo : sinNodosRepetidos) {
			Opcion opcionPermiso = this.gestorDatos.leerOpcionXidOpcion(nodo, false);
			//LOGGER.info("Opcion:" + opcionPermiso.getIdOpcion());
			opcionesPermitidas.add(opcionPermiso);
		}
		for(Opcion opcionI: opcionesPermitidas) {
			LOGGER.info("Las opciones en el set: " + opcionI.getIdOpcion());
			if(opcionI.getOpcionPadre() != null) {
				LOGGER.info("soy hijo de:" + opcionI.getOpcionPadre().getIdOpcion());
			}else {
				LOGGER.info("Soy padre:" + opcionI.getIdOpcion());
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
				LOGGER.info("soy el papa: " +rama.getOpcionesMenu());
				
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
			/*LOGGER.info("La lista de opciones: " + listaNodos.get(i));
			//String rama = Integer.valueOf(parts[i]+1);
			LOGGER.info("Hoja: " + nodo.getIdOpcion());
			
			int indiceNodo = copiaRama.contieneNodo(nodo);
			LOGGER.info("el indice: " + indiceNodo);
			if(indiceNodo != -1) {
				LOGGER.info("indiceNodo: " + copiaRama.getOpcionesMenu().size());
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
		LOGGER.info("El permiso rol: " + idOpcion);
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
		
		LOGGER.info("El permiso rol: " + idOpcion);
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
				LOGGER.info("El permiso rol: " + idOpcion);
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

	@Override
	public UsuarioConMenu getMenuUsuario(String idUsuario) {
		UsuarioConMenu usuarioConMenu=new UsuarioConMenu();
		Usuario usuario = null;

		List<Rol> todosLosRoles = null;
		List<Opcion> todasLasOpciones = null;
		
		LinkedHashMap<String,Opcion>             opcionAllMap = new LinkedHashMap<>();
		LinkedHashMap<String,Opcion>         opcionUsuarioMap = new LinkedHashMap<>();
		
		LinkedHashMap<String,OpcionMenu>     opcionmenuAllMap = new LinkedHashMap<>();
		LinkedHashMap<String,OpcionMenu> opcionmenuUsuarioMap = new LinkedHashMap<>();
		
		try {
			usuario = this.gestorDatos.leerUsuario(idUsuario, true);
			
			todosLosRoles    = this.gestorDatos.leerTodosLosRoles();
			
			for(Rol r:todosLosRoles){
				LOGGER.info("-> todosLosRoles: \trol:"+r.getDescripcion());
				final Set<Permiso> permisosByRol = r.getPermisos();
				for(Permiso p: permisosByRol){
					final Opcion o = p.getOpcion();
					opcionUsuarioMap.put(o.getIdOpcion(),o);
				}
			}
			
			for(Opcion o:opcionUsuarioMap.values()){
				LOGGER.info("-> usuarioOpciones: \topcion:"+o.getIdOpcion());
			}
			
			todasLasOpciones = this.gestorDatos.leerTodasLasOpciones();
			for(Opcion o:todasLasOpciones){
				LOGGER.info("-> todasLasOpciones: \topcion:"+o.getIdOpcion());
				opcionAllMap.put(o.getIdOpcion(),o);
				final OpcionMenu om = new OpcionMenu();
				om.setIdOpcion(o.getIdOpcion());
				om.setIdOpcionPadre(o.getOpcionPadre()!=null?o.getOpcionPadre().getIdOpcion():null);
				om.setNombreOpcion(o.getNombreOpcion());
				om.setParametros(false);
				om.setUrl(o.getUrl());
				opcionmenuAllMap.put(o.getIdOpcion(), om);
			}
			//------------------------------------------------------------------
			LOGGER.info("->getMenuUsuario: poblando cada opcionDTO de sus hijos: ");
			for(Opcion o: todasLasOpciones){
				final OpcionMenu om= opcionmenuAllMap.get(o.getIdOpcion());
				final List<OpcionMenu> somList =om.getSubOpcionesList();
				for(Opcion o2: todasLasOpciones){
					if(o2.getOpcionPadre()!=null && o.getIdOpcion().equals(o2.getOpcionPadre().getIdOpcion())){
						somList.add(opcionmenuAllMap.get(o2.getIdOpcion()));
					}
				}
			}
			LOGGER.info("->getMenuUsuario: discrimininando nodos mediante rutas a cada hoja: ");
			for(OpcionMenu om: opcionmenuAllMap.values()){
				List<String> path=new ArrayList<>();
				boolean pathEnd=false;
				OpcionMenu diggUpper=om;
				path.add(diggUpper.getIdOpcion());
				while(!pathEnd){
					String idOpcionPadre = diggUpper.getIdOpcionPadre();
					if(idOpcionPadre != null){
						path.add(idOpcionPadre);
						diggUpper = opcionmenuAllMap.get(idOpcionPadre);
					} else {
						pathEnd = true;
					}
				}
				Collections.reverse(path);
				om.setRutaMenu(path);
				
				if(om.getSubOpcionesList().size()==0 && opcionUsuarioMap.containsKey(om.getIdOpcion())){
					om.setVisibleByRol(true);
					final List<String> rutaMenu = om.getRutaMenu();
					for(String idP: rutaMenu){
						opcionmenuAllMap.get(idP).setVisibleByRol(true);
					}
				}
			}
			LOGGER.info("->getMenuUsuario: recorrido en profundidad: los * seran las ramas: omAllMap{");
			for(OpcionMenu om: opcionmenuAllMap.values()){
				final List<OpcionMenu> subOpcionesList = om.getSubOpcionesList();
				if(om.isVisibleByRol() && om.getIdOpcionPadre() == null ){
					opcionmenuUsuarioMap.put(om.getIdOpcion(), om);
				}
				List<OpcionMenu> omRL=new ArrayList<>();
				for(OpcionMenu sm: subOpcionesList){
					if(sm.isVisibleByRol()){
						omRL.add(sm);
					}
				}
				om.setSubOpcionesList(omRL);
				LOGGER.info("getMenuUsuario: \tid=("+(om.isVisibleByRol()?"*":" ")+")"+om.getIdOpcion()+", pid="+om.getIdOpcionPadre()+", path:"+om.getRutaMenu()+", sumMenulist="+omRL.size());
			}
			LOGGER.info("getMenuUsuario: }");
			
			usuarioConMenu.setOpcionMenuRaizList(new ArrayList<>());
			usuarioConMenu.getOpcionMenuRaizList().addAll(opcionmenuUsuarioMap.values());
			usuarioConMenu.setUsuario(usuario);
			return usuarioConMenu;			
		} catch (NegocioException nex) {
			nex.printStackTrace(System.err);
			Map<String,String> detEx = new HashMap<String,String>();
			detEx.put("servicio",this.getClass()+".getMenuUsuario(idUsuario: " + idUsuario + ")");
			detEx.put("msg", nex.getMessage());
			NegocioException nEx = ManejadorErrNegocio.crearEx(detEx, nex);
			throw nEx;
		}
	}	
}
