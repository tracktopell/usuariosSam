package mx.com.rc.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RolUsuarioPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id_rol", nullable=false)
	private Integer idRolFK;
	
	@Column(name="usuario", nullable=false)
	private String usuarioFK;

	public RolUsuarioPK() {
		super();
	}

	public RolUsuarioPK(Integer idRolFK, String usuarioFK) {
		super();
		this.idRolFK = idRolFK;
		this.usuarioFK = usuarioFK;
	}

	public Integer getIdRolFK() {
		return idRolFK;
	}

	public void setIdRolFK(Integer idRolFK) {
		this.idRolFK = idRolFK;
	}

	public String getUsuarioFK() {
		return usuarioFK;
	}

	public void setUsuarioFK(String usuarioFK) {
		this.usuarioFK = usuarioFK;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UsuarioRolPK [idRolFK=" + idRolFK + ", usuarioFK=" + usuarioFK + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRolFK == null) ? 0 : idRolFK.hashCode());
		result = prime * result + ((usuarioFK == null) ? 0 : usuarioFK.hashCode());
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
		RolUsuarioPK other = (RolUsuarioPK) obj;
		if (idRolFK == null) {
			if (other.idRolFK != null)
				return false;
		} else if (!idRolFK.equals(other.idRolFK))
			return false;
		if (usuarioFK == null) {
			if (other.usuarioFK != null)
				return false;
		} else if (!usuarioFK.equals(other.usuarioFK))
			return false;
		return true;
	}
	
	

}
