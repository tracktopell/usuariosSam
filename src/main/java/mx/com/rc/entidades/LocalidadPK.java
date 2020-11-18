package mx.com.rc.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class LocalidadPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="id_estado", nullable=false)
	private Integer idEstadoPK;
	@Column(name="id_localidad", nullable=false)
	private Integer idLocalidadPK;
	public LocalidadPK() {
		super();
	}
	public LocalidadPK(Integer idEstado, Integer idLocalidad) {
		super();
		this.idEstadoPK = idEstado;
		this.idLocalidadPK = idLocalidad;
	}
	@Override
	public String toString() {
		return "LocalidadPK [idEstado=" + idEstadoPK + ", idLocalidad=" + idLocalidadPK + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstadoPK == null) ? 0 : idEstadoPK.hashCode());
		result = prime * result + ((idLocalidadPK == null) ? 0 : idLocalidadPK.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalidadPK other = (LocalidadPK) obj;
		if (idEstadoPK == null) {
			if (other.idEstadoPK != null)
				return false;
		} else if (!idEstadoPK.equals(other.idEstadoPK))
			return false;
		if (idLocalidadPK == null) {
			if (other.idLocalidadPK != null)
				return false;
		} else if (!idLocalidadPK.equals(other.idLocalidadPK))
			return false;
		return true;
	}
	
}
