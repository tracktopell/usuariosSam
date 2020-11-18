package mx.com.rc.persistencia;

public class PersistenciaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String nombreTabla;
	private String entityManager;
	private String sentenciaSql;
	private String ubicacion;
	/**/private int code;
	private String message;
	private String description;
	private String url;
	
	public PersistenciaException(String msg) {
		super(msg);
	}
	public String getNombreTabla() {
		return nombreTabla;
	}
	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
	public String getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(String nombreBaseDatos) {
		this.entityManager = nombreBaseDatos;
	}
	public String getSentenciaSql() {
		return sentenciaSql;
	}
	public void setSentenciaSql(String sentenciaSql) {
		this.sentenciaSql = sentenciaSql;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
