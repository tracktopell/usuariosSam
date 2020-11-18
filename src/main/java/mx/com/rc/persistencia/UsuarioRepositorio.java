package mx.com.rc.persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import mx.com.rc.entidades.Empresa;
import mx.com.rc.entidades.Opcion;
import mx.com.rc.entidades.Permiso;
import mx.com.rc.entidades.Rol;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.wildfly.common.context.ContextManager;

import mx.com.rc.entidades.Usuario;
import mx.com.rc.entidades.RolUsuario;
import mx.com.rc.negocio.IGestorDatosUsuario;

public class UsuarioRepositorio implements IGestorDatosUsuario {
	
	@PersistenceUnit
	private EntityManagerFactory fabricaEntityManager;
		
	@Override
	public List<Usuario> leerUsuarios(boolean conRoles) {
		System.out.println("Ejecutando leerUsuarios");
        String consulta = "SELECT u FROM Usuario u";
		
        EntityManager em = this.fabricaEntityManager.createEntityManager();
        
		try {
			    @SuppressWarnings("unchecked")
				List<Usuario> listaUsuarios = (List<Usuario>) em.createQuery(consulta).getResultList();
			    
				if(listaUsuarios != null) {
					System.out.println("Lista");
					for(Usuario usuarioI: listaUsuarios) {
						if(conRoles) {
							usuarioI.getRoles().size();
							
						} else {
							usuarioI.setRoles(null);
						}
					}
					System.out.println("Ejecutando leerUsuarios" + listaUsuarios.toString());
					
					return listaUsuarios;
					//return null;
				} else {
					return null;
				}
		} catch (Exception ex) {
			//System.out.println("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Usuarios en la BD", consulta, "leerUsuarios("+conRoles+")");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if(em != null)
				em.close();
			
		}
		//return null;
	}
	@Override
	public List<Empresa> leerEmpresas() {
		System.out.println("Ejecutando leerUsuarios");
        String consulta = "SELECT e FROM Empresa e";
		
        EntityManager em = this.fabricaEntityManager.createEntityManager();
        
		try {
			    @SuppressWarnings("unchecked")
				List<Empresa> listaEmpresas = (List<Empresa>) em.createQuery(consulta).getResultList();
			    
				if(listaEmpresas != null) {
					System.out.println("Lista");
					
					System.out.println("Ejecutando leerEmpresas" + listaEmpresas.toString());
					
					return listaEmpresas;
					//return null;
				} else {
					return null;
				}
		} catch (Exception ex) {
			//System.out.println("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Empresas en la BD", null, "leerEmpresas()");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if(em != null)
				em.close();
			
		}
		//return null;
	}
	
	@Override
	public Usuario leerUsuario(String idUsuario, boolean conRoles) {
		EntityManager em = this.fabricaEntityManager.createEntityManager();
		
		String consulta = "SELECT u FROM Usuario u WHERE u.usuario = :idUsuario";
        Usuario usuario=null;
        System.out.println("usuario: " + idUsuario);
		try {
			
			System.out.println("flush() ");
            @SuppressWarnings("unchecked")
            List<Usuario> usuarios = (List<Usuario>) em.createQuery(consulta).setParameter("idUsuario", idUsuario).getResultList();
            //System.out.println("usuarios: " + usuarios);
            if(!usuarios.isEmpty()) {
            	usuario = usuarios.get(0);
            	if(conRoles) {
        			usuario.getRoles().size();
        			for(RolUsuario usuarioRol : usuario.getRoles()) {
        				usuarioRol.setUsuario(null);
        				Rol rol = usuarioRol.getRol();
        				rol.getPermisos().size();
        				for(Permiso permisoRolOpcion : rol.getPermisos()) {
        					permisoRolOpcion.setRol(null);
        					Opcion opcion = permisoRolOpcion.getOpcion();
        					opcion.getOpcionesMenu().size();
        					opcion.setOpcionPadre(null);
        				}
        			}
        			
        		} else {
        			usuario.setRoles(null);
        		}
            	
	        }
            return usuario;
            
		} catch (Exception ex) {
			System.out.println("La excepcion" + ex);
			throw this.generarPersistenciaException(ex, "Falla al intentar obtener conexión a la BD", consulta, "leerUsuario("+idUsuario+","+conRoles+")");
		} finally {
			if(em != null)
				em.close();
		}
		
	}
	private PersistenciaException generarPersistenciaException(Exception ex, String mensaje, String consulta, String ubicacion) {
		Map<String,String> detEx = new HashMap<String, String>();
		detEx.put("mensaje", mensaje);
		detEx.put("ubicacion", this.getClass().getSimpleName() + "." + ubicacion);
		PersistenciaException pex = ManejadorErrPersistencia.crearEx(detEx, ex);
		return pex;
	}
	@Override
	public Empresa leerEmpresa(String idUname) {
		EntityManager em = this.fabricaEntityManager.createEntityManager();
		
		String consulta = "SELECT e FROM Empresa e WHERE e.idUname = :idUname";
        Empresa empresa=null;
        System.out.println("empresa: " + idUname);
		try {
			
			System.out.println("flush() ");
            @SuppressWarnings("unchecked")
            List<Empresa> empresas = (List<Empresa>) em.createQuery(consulta).setParameter("idUname", idUname).getResultList();
            System.out.println("idUname: " + idUname);
            if(!empresas.isEmpty()) {
            	empresa = empresas.get(0);
            	
	        }
             
            
            return empresa;
            
		} catch (Exception ex) {
			System.out.println("La excepcion" + ex);
			
			
			throw this.generarPersistenciaException(ex, "Falla al intentar obtener conexión a la BD", consulta, "leerEmpresa("+idUname);
		} finally {
			if(em != null)
				em.close();
			
		}
	}
	@Override
	public List<Empresa> leerEmpresasXtipoEmpresa(String tipoEmpresa) {
		System.out.println("Ejecutando leerUsuarios");
        
		String consulta = "SELECT e FROM Empresa e WHERE e.tipoEmpresa = :tipoEmpresa";
		
        EntityManager em = this.fabricaEntityManager.createEntityManager();
        
		try {
			    @SuppressWarnings("unchecked")
				List<Empresa> listaEmpresas = (List<Empresa>) em.createQuery(consulta).getResultList();
			    
				if(listaEmpresas != null) {
					System.out.println("Lista");
					
					System.out.println("Ejecutando leerEmpresasXtipoEmpresa" + listaEmpresas.toString());
					
					return listaEmpresas;
					//return null;
				} else {
					return null;
				}
		} catch (Exception ex) {
			//System.out.println("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Empresas en la BD", null, "leerEmpresasXtipoEmpresa()");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if(em != null)
				em.close();
			
		}
	}
	@Override
	public List<Permiso> leerPermisosXrol(String idRol) {
		String consulta = "SELECT pr FROM PermisoRolOpcion pr WHERE pr.idRol = :idRol";
		
		EntityManager em = this.fabricaEntityManager.createEntityManager();
		try {
			@SuppressWarnings("unchecked")
			List<Permiso> listaPermisosXrol = em.createQuery(consulta).getResultList();
			if(listaPermisosXrol != null) {
				System.out.println("Lista");
				
				System.out.println("Ejecutando leerEmpresasXtipoEmpresa" + listaPermisosXrol.toString());
				
				return listaPermisosXrol;
			} else {
			
				return null;
			}
		} catch (Exception ex) {
			//System.out.println("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Permisos por rol en la BD", null, "leerPermisosXrol("+idRol+")");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if(em != null)
				em.close();
			
		}
	}
	@Override
	public Opcion leerOpcionXidOpcion(String idOpcion, boolean conHijos) {
		
		String consulta = "SELECT o FROM Opcion o WHERE o.idOpcion = :idOpcion";
		
        EntityManager em = this.fabricaEntityManager.createEntityManager();
        Opcion opcion = null;
		try {
			    @SuppressWarnings("unchecked")
			    List<Opcion> listaOpciones = (List<Opcion>) em.createQuery(consulta).setParameter("idOpcion", idOpcion).getResultList();
			    
			    if(!listaOpciones.isEmpty()) {
	            	opcion = listaOpciones.get(0);
	            	if(conHijos) {
	            		opcion.getOpcionesMenu().size();
	            		opcion.setOpcionPadre(null);
	            		for(Opcion opcionI: opcion.getOpcionesMenu()) {
	            			opcionI.getOpcionesMenu().size();
	            			opcionI.setOpcionPadre(null);
	            			for(Opcion opcionII:opcionI.getOpcionesMenu()) {
	            				opcionII.getOpcionesMenu().size();
		            			opcionII.setOpcionPadre(null);
	            			}
	            			
	            		}
	            		
	            	}else {
	            		opcion.setOpcionesMenu(null);
	            	}
	            	
	            	return opcion;
		        }else {
					return null;
				}
		} catch (Exception ex) {
			//System.out.println("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la opcion de la BD: ", null, "leerOpcionXidOpcion("+idOpcion+", conHijos: "+conHijos+")");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if(em != null)
				em.close();
			
		}
	}

	

	
}
