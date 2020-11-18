package mx.com.rc.persistencia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import mx.com.rc.entidades.Opcion;
import mx.com.rc.negocio.IGestorDatosMenu;

public class MenuRepositorio implements IGestorDatosMenu {
	
	@PersistenceUnit
	private EntityManagerFactory fabricaEntityManager;

	@Override
	public List<Opcion> leerOpciones(List<String> listadoDeidOpcion) {
		String idsBusqueda = "";
		int limite = listadoDeidOpcion.size();
		for(int i = 0 ; i< listadoDeidOpcion.size(); i++) {
			if(i < listadoDeidOpcion.size()) {
				
				idsBusqueda += "'"+listadoDeidOpcion.get(i)+"'";
			}
			if(i != listadoDeidOpcion.size()-1) {
				idsBusqueda += ",";
			}
		}
		System.out.println("idsBusqueda" + idsBusqueda);
		EntityManager em = this.fabricaEntityManager.createEntityManager();
		
		String consulta = "SELECT o FROM Opcion o WHERE o.idOpcion IN ("+idsBusqueda+")";
		
		try {
			@SuppressWarnings("unchecked")
			List<Opcion> listaOpciones = (List<Opcion>) em.createQuery(consulta).getResultList();
			
			if(listaOpciones != null ) {
				
				for(Opcion opcionI: listaOpciones) {
					opcionI.setOpcionesMenu(null);
				}
				
				return listaOpciones;
				
			}else {
				return null;
			}
		} catch (Exception ex) {
			PersistenciaException pex = this.generarPersistenciaException(ex, "Falla al leer la lista de Opciones en la BD", null, "leerOpciones(List<String> listadoDeidOpcion)");
			throw pex;
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

}
