package mx.com.rc.persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

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

	private final static Logger LOGGER = Logger.getLogger(UsuarioRepositorio.class.getName());
	@PersistenceUnit
	private EntityManagerFactory fabricaEntityManager;

	@Override
	public List<Usuario> leerUsuarios(boolean conRoles) {
		LOGGER.info("Ejecutando leerUsuarios: conRoles="+conRoles);
		String consulta = "SELECT u FROM Usuario u";

		EntityManager em = this.fabricaEntityManager.createEntityManager();

		try {
			@SuppressWarnings("unchecked")
			List<Usuario> listaUsuarios = (List<Usuario>) em.createQuery(consulta).getResultList();

			if (listaUsuarios != null) {

				LOGGER.info("Lista Usuarios: listaUsuarios.size="+listaUsuarios.size());
				for (Usuario usuarioI : listaUsuarios) {
					if (conRoles) {
						usuarioI.getRoles().size();
						LOGGER.info("\tusuario: ->" + usuarioI.getUsuario() + " iterando en los roles: roles.size=" + usuarioI.getRoles().size());
						for (RolUsuario rolUsuario : usuarioI.getRoles()) {
							LOGGER.info("\t\tusuario: ->" + usuarioI.getUsuario() + " rol:" + rolUsuario.getRol().getDescripcion());
							rolUsuario.setUsuario(null);
							Rol rol = rolUsuario.getRol();
							rol.getPermisos().size();
							for (Permiso permisoRolOpcion : rol.getPermisos()) {
								permisoRolOpcion.setRol(null);
								Opcion opcion = permisoRolOpcion.getOpcion();
								opcion.getOpcionesMenu().size();
								opcion.setOpcionPadre(null);
							}
						}
						LOGGER.info("\tusuario: ->" + usuarioI.getUsuario() + " roles.size:" + usuarioI.getRoles().size());
					} else {
						LOGGER.info("\tusuario: ->" + usuarioI.getUsuario() + " sin roles");
						usuarioI.setRoles(null);
					}
				}
				LOGGER.info("Fin leerUsuarios:" + listaUsuarios.size());

				return listaUsuarios;
				//return null;
			} else {
				return null;
			}
		} catch (Exception ex) {
			//LOGGER.info("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Usuarios en la BD", consulta, "leerUsuarios(" + conRoles + ")");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if (em != null) {
				em.close();
			}

		}
		//return null;
	}

	@Override
	public List<Empresa> leerEmpresas() {
		LOGGER.info("Ejecutando leerUsuarios");
		String consulta = "SELECT e FROM Empresa e";

		EntityManager em = this.fabricaEntityManager.createEntityManager();

		try {
			@SuppressWarnings("unchecked")
			List<Empresa> listaEmpresas = (List<Empresa>) em.createQuery(consulta).getResultList();

			if (listaEmpresas != null) {
				LOGGER.info("Lista");

				LOGGER.info("Ejecutando leerEmpresas" + listaEmpresas.toString());

				return listaEmpresas;
				//return null;
			} else {
				return null;
			}
		} catch (Exception ex) {
			//LOGGER.info("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Empresas en la BD", null, "leerEmpresas()");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if (em != null) {
				em.close();
			}

		}
		//return null;
	}

	@Override
	public Usuario leerUsuario(String idUsuario, boolean conRoles) {
		EntityManager em = this.fabricaEntityManager.createEntityManager();

		String consulta = "SELECT u FROM Usuario u WHERE u.usuario = :idUsuario";
		Usuario usuarioI = null;
		LOGGER.info("leerUsuario: usuario: " + idUsuario);
		try {

			LOGGER.info("flush() ");
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = (List<Usuario>) em.createQuery(consulta).setParameter("idUsuario", idUsuario).getResultList();
			//LOGGER.info("usuarios: " + usuarios);
			if (!usuarios.isEmpty()) {
				usuarioI = usuarios.get(0);
				if (conRoles) {
					usuarioI.getRoles().size();
					LOGGER.info("\tusuario: ->" + usuarioI.getUsuario() + " iterando en los roles: roles.size=" + usuarioI.getRoles().size());
					for (RolUsuario rolUsuario : usuarioI.getRoles()) {
						LOGGER.info("\t\tusuario: ->" + usuarioI.getUsuario() + " rol:" + rolUsuario.getRol().getDescripcion());
						rolUsuario.setUsuario(null);
						Rol rol = rolUsuario.getRol();
						rol.getPermisos().size();
						for (Permiso permisoRolOpcion : rol.getPermisos()) {
							permisoRolOpcion.setRol(null);
							Opcion opcion = permisoRolOpcion.getOpcion();
							opcion.getOpcionesMenu().size();
							opcion.setOpcionPadre(null);
						}
					}
					LOGGER.info("\tusuario: ->" + usuarioI.getUsuario() + " roles.size:" + usuarioI.getRoles().size());
				} else {
					LOGGER.info("\tusuario: ->" + usuarioI.getUsuario() + " sin roles");
					usuarioI.setRoles(null);
				}
			}
			return usuarioI;

		} catch (Exception ex) {
			LOGGER.info("La excepcion" + ex);
			throw this.generarPersistenciaException(ex, "Falla al intentar obtener conexión a la BD", consulta, "leerUsuario(" + idUsuario + "," + conRoles + ")");
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	private PersistenciaException generarPersistenciaException(Exception ex, String mensaje, String consulta, String ubicacion) {
		Map<String, String> detEx = new HashMap<String, String>();
		detEx.put("mensaje", mensaje);
		detEx.put("ubicacion", this.getClass().getSimpleName() + "." + ubicacion);
		PersistenciaException pex = ManejadorErrPersistencia.crearEx(detEx, ex);
		return pex;
	}

	@Override
	public Empresa leerEmpresa(String idUname) {
		EntityManager em = this.fabricaEntityManager.createEntityManager();

		String consulta = "SELECT e FROM Empresa e WHERE e.idUname = :idUname";
		Empresa empresa = null;
		LOGGER.info("empresa: " + idUname);
		try {

			LOGGER.info("flush() ");
			@SuppressWarnings("unchecked")
			List<Empresa> empresas = (List<Empresa>) em.createQuery(consulta).setParameter("idUname", idUname).getResultList();
			LOGGER.info("idUname: " + idUname);
			if (!empresas.isEmpty()) {
				empresa = empresas.get(0);

			}
			return empresa;

		} catch (Exception ex) {
			LOGGER.info("La excepcion" + ex);

			throw this.generarPersistenciaException(ex, "Falla al intentar obtener conexión a la BD", consulta, "leerEmpresa(" + idUname);
		} finally {
			if (em != null) {
				em.close();
			}

		}
	}

	@Override
	public List<Empresa> leerEmpresasXtipoEmpresa(String tipoEmpresa) {
		LOGGER.info("Ejecutando leerUsuarios");

		String consulta = "SELECT e FROM Empresa e WHERE e.tipoEmpresa = :tipoEmpresa";

		EntityManager em = this.fabricaEntityManager.createEntityManager();

		try {
			@SuppressWarnings("unchecked")
			List<Empresa> listaEmpresas = (List<Empresa>) em.createQuery(consulta).getResultList();

			if (listaEmpresas != null) {
				LOGGER.info("Lista");

				LOGGER.info("Ejecutando leerEmpresasXtipoEmpresa" + listaEmpresas.toString());

				return listaEmpresas;
				//return null;
			} else {
				return null;
			}
		} catch (Exception ex) {
			//LOGGER.info("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Empresas en la BD", null, "leerEmpresasXtipoEmpresa()");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if (em != null) {
				em.close();
			}

		}
	}

	@Override
	public List<Permiso> leerPermisosXrol(String idRol) {
		String consulta = "SELECT pr FROM PermisoRolOpcion pr WHERE pr.idRol = :idRol";

		EntityManager em = this.fabricaEntityManager.createEntityManager();
		try {
			@SuppressWarnings("unchecked")
			List<Permiso> listaPermisosXrol = em.createQuery(consulta).getResultList();
			if (listaPermisosXrol != null) {
				LOGGER.info("Lista");

				LOGGER.info("Ejecutando leerEmpresasXtipoEmpresa" + listaPermisosXrol.toString());

				return listaPermisosXrol;
			} else {

				return null;
			}
		} catch (Exception ex) {
			//LOGGER.info("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Permisos por rol en la BD", null, "leerPermisosXrol(" + idRol + ")");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if (em != null) {
				em.close();
			}

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

			if (!listaOpciones.isEmpty()) {
				opcion = listaOpciones.get(0);
				if (conHijos) {
					opcion.getOpcionesMenu().size();
					opcion.setOpcionPadre(null);
					for (Opcion opcionI : opcion.getOpcionesMenu()) {
						opcionI.getOpcionesMenu().size();
						opcionI.setOpcionPadre(null);
						for (Opcion opcionII : opcionI.getOpcionesMenu()) {
							opcionII.getOpcionesMenu().size();
							opcionII.setOpcionPadre(null);
						}

					}

				} else {
					opcion.setOpcionesMenu(null);
				}

				return opcion;
			} else {
				return null;
			}
		} catch (Exception ex) {
			//LOGGER.info("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la opcion de la BD: ", null, "leerOpcionXidOpcion(" + idOpcion + ", conHijos: " + conHijos + ")");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if (em != null) {
				em.close();
			}

		}
	}

	@Override
	public List<Opcion> leerTodasLasOpciones() {
		String consulta = "SELECT o FROM Opcion o";

		EntityManager em = this.fabricaEntityManager.createEntityManager();
		List<Opcion> listaOpciones = null;
		try {			
			listaOpciones = em.createQuery(consulta).getResultList();
			return listaOpciones;
		} catch (Exception ex) {
			//LOGGER.info("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la opcion de la BD: ", null, "leerTodasLasOpciones()");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if (em != null) {
				em.close();
			}

		}
	}

	@Override
	public List<Rol> leerTodosLosRoles() {
		String consulta = "SELECT r FROM Rol r";

		EntityManager em = this.fabricaEntityManager.createEntityManager();		
		List<Rol> listaRoles = null;
		try {			
			listaRoles = em.createQuery(consulta).getResultList();
			for(Rol r:listaRoles){
				final Set<Permiso> permisos = r.getPermisos();
				for(Permiso p:permisos){
					final Opcion o = p.getOpcion();
					o.getIdOpcion();
				}
			}
			return listaRoles;
		} catch (Exception ex) {
			//LOGGER.info("Excepcion: "+ex.getMessage());
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la opcion de la BD: ", null, "leertodosLosRoles()");
			//bitacora.error(ManejadorErrPersistencia.getDescripcionDetallada(pex));
			throw pex;
		} finally {
			if (em != null) {
				em.close();
			}

		}
	}

}
