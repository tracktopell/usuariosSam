package mx.com.rc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jessica
 */

public class OpcionPermiso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idOpcion;

	private String nombreOpcion;

	private boolean parametros;

	private String url;

	private String idOpcionPadre;
	
	private List<String>      rutaMenu;
	
	private boolean           visibleByRol;
	
	private List<OpcionPermiso> subOpcionesList;

	public OpcionPermiso() {
		subOpcionesList = new ArrayList<OpcionPermiso>();
	}
	

	public OpcionPermiso(String idOpcion, String nombreOpcion, boolean parametros, String url) {
		super();
		this.idOpcion = idOpcion;
		this.nombreOpcion = nombreOpcion;
		this.parametros = parametros;
		this.url = url;
	}


	/**
	 * @return the idOpcion
	 */
	public String getIdOpcion() {
		return idOpcion;
	}

	/**
	 * @param idOpcion the idOpcion to set
	 */
	public void setIdOpcion(String idOpcion) {
		this.idOpcion = idOpcion;
	}

	/**
	 * @return the nombreOpcion
	 */
	public String getNombreOpcion() {
		return nombreOpcion;
	}

	/**
	 * @param nombreOpcion the nombreOpcion to set
	 */
	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}

	/**
	 * @return the parametros
	 */
	public boolean isParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(boolean parametros) {
		this.parametros = parametros;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the idOpcionPadre
	 */
	public String getIdOpcionPadre() {
		return idOpcionPadre;
	}

	/**
	 * @param idOpcionPadre the idOpcionPadre to set
	 */
	public void setIdOpcionPadre(String idOpcionPadre) {
		this.idOpcionPadre = idOpcionPadre;
	}

	public List<OpcionPermiso> getSubOpcionesList() {
		return subOpcionesList;
	}

	public void setSubOpcionesList(List<OpcionPermiso> subOpcionesList) {
		this.subOpcionesList = subOpcionesList;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ idOpcion=").append(idOpcion);
		sb.append(", nombreOpcion=").append(nombreOpcion);
		sb.append(", url=").append(url);
		sb.append(", parametros=").append(parametros);
		sb.append(", idOpcionPadre=").append(idOpcionPadre);
		if (subOpcionesList != null) {
			sb.append(", subOpcionesList[].size=").append(subOpcionesList.size());
		} else {
			sb.append(", subOpcionesList=").append(subOpcionesList);
		}

//			sb.append("{ idOpcion=").append(idOpcion);
//			sb.append(", nombreOpcion=").append(nombreOpcion);
//			sb.append(", parametros=").append(parametros);
//			sb.append(", url=").append(url);
//			sb.append(", fechaAlta=").append(fechaAlta);
//			sb.append(", idOpcionPadre=").append(idOpcionPadre);
//			if(subOpcionesList != null){
//				sb.append(", subOpcionesList[].size=").append(subOpcionesList.size());
//			} else {
//				sb.append(", subOpcionesList=").append(subOpcionesList);
//			}
		sb.append("}");
		return sb.toString();
	}

}
