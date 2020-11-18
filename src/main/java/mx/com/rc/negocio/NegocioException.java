package mx.com.rc.negocio;

public class NegocioException extends RuntimeException {
	private String servicio;

	public NegocioException(String msj) {
		super(msj);
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
}
