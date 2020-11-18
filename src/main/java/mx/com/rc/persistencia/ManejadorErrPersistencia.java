package mx.com.rc.persistencia;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class ManejadorErrPersistencia {
	
	public static PersistenciaException crearEx(Map<String,String> detEx, Exception ex){
		PersistenciaException pex = new PersistenciaException(detEx.get("mensaje"));
		//pex.setNombreTabla(detEx.get("tabla"));
		//pex.setEntityManager(detEx.get("em"));
		//pex.setSentenciaSql(detEx.get("consulta"));
		pex.setMessage(detEx.get("mensaje"));
		pex.setUbicacion(detEx.get("ubicacion"));
		pex.initCause(ex);
		return pex;
	}
	public static String getDescripcionDetallada(PersistenciaException pex) {
		StringBuilder sb = new StringBuilder();
		Throwable causa = pex.getCause().getCause();
		sb.append("\n" + "Mensaje: " + pex.getMessage() + "\n")
		  .append("Excepcion: " + pex.getClass().getName() + "\n" )
		  .append("Ubicacion: " + pex.getUbicacion() + "\n");
		if(causa != null){
			if(causa.getClass().getName() == "org.hibernate.exception.JDBCConnectionException"){
				
				sb.append("\nExcepcion Causa : " + causa.getCause() + "\n")
					.append("Mensaje de excepción causa: " + causa.getMessage() + "\n")
					.append("Falla de conexión");
				}else {
					sb.append("\nExcepcion Causa : " + causa.getClass().getName() + "\n")
					.append("Mensaje de excepción causa: " + causa.getMessage() + "\n")
					.append("Causa: "+ causa.getCause());
				}
			if(causa instanceof SQLException){
				SQLException sqlEx = (SQLException) causa;
				sb.append("Error code:" + sqlEx.getErrorCode() + "\n")
				  .append("SQLState: " + sqlEx.getSQLState() + "\n");
			}
		}

		return sb.toString();
	}
	
	public static Map<String,String> getMapaDescripcionDetallada(PersistenciaException pex) {
		Throwable causa = pex.getCause().getCause();
		Map<String,String> detEx = new HashMap<String, String>();
		detEx.put("mensaje", pex.getMessage());
		//detEx.put("tabla", pex.getNombreTabla());
		//detEx.put("em", pex.getEntityManager());
		//detEx.put("consulta", pex.getSentenciaSql());
		detEx.put("ubicacion", pex.getUbicacion());
		
		if(causa != null){
			detEx.put("causa", causa.getCause().toString());
			detEx.put("msgExcepcion", causa.getMessage());
			detEx.put("sugerencia", "Comunicate al área de infraestructura tecnológica");
			if(causa instanceof SQLException){
				SQLException sqlEx = (SQLException) causa;
				detEx.put("error", sqlEx.getSQLState());
				detEx.put("SQLState", sqlEx.getSQLState());
			}
		}

		return detEx;
	}

}
