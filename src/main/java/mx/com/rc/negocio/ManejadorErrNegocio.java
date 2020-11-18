package mx.com.rc.negocio;

import java.util.HashMap;
import java.util.Map;

import mx.com.rc.persistencia.ManejadorErrPersistencia;
import mx.com.rc.persistencia.PersistenciaException;


public class ManejadorErrNegocio {
	public static NegocioException crearEx(Map<String,String> detEx, Exception ex){
		NegocioException nex = new NegocioException(detEx.get("msg"));
		nex.setServicio(detEx.get("servicio"));
		nex.initCause(ex);
		return nex;
	}
	public static Map<String,String> getMapaDescripcionDetallada(Exception ex){
		Throwable causa = ex.getCause();
		Map<String,String> detEx = new HashMap<String, String>();
		detEx.put("mensaje", ex.getMessage());
		//detEx.put("excepcion: ", ex.getClass().getName());
		detEx.put("sugerencia", ("Comunicate al área de sistemas mostrador"));
		if(ex instanceof NegocioException ){
			NegocioException nex = (NegocioException) ex;
			detEx.put("servicio: ", nex.getServicio());
		}
		if(causa != null){
			detEx.put("causa",causa.getClass().getName());
			//detEx.put("msgExcepcion", causa.getMessage());
			if(causa instanceof PersistenciaException){
				PersistenciaException pEx = (PersistenciaException) causa;
				detEx.put("persistencia", ManejadorErrPersistencia.getDescripcionDetallada(pEx));
			}
		}

		return detEx;
	}
	public static String getDescripcionDetallada(Exception ex){
		StringBuilder sb = new StringBuilder();
		Throwable causa = ex.getCause();
		sb.append("mensaje: "+ ex.getMessage() + "\n")
		  .append("Excepcion: " + ex.getClass().getName() + "\n" );
		if(ex instanceof NegocioException ){
			NegocioException nex = (NegocioException) ex;
			sb.append("servicio: " + nex.getServicio() + "\n");
		}
		if(causa != null){
			sb.append("\nExcepcion Causa : " + causa.getClass().getName() + "\n")
			  .append("Mensaje de excepciónn causa: " + causa.getMessage() + "\n");
			if(causa instanceof PersistenciaException){
				PersistenciaException pEx = (PersistenciaException) causa;
				sb.append(ManejadorErrPersistencia.getDescripcionDetallada(pEx));
			}
		}

		return sb.toString();
	}


}
