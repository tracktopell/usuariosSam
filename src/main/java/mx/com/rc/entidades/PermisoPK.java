package mx.com.rc.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PermisoPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id_rol", nullable=false)
	private Integer idRolFK;
	
	@Column(name="id_opcion", nullable=false, length=20)
	private String idOpcionFK;

	
	public PermisoPK() {
		
	}

	

	public PermisoPK(Integer idRolFK, String idOpcionFK) {
		super();
		this.idRolFK = idRolFK;
		this.idOpcionFK = idOpcionFK;
	}



	public Integer getIdRolFK() {
		return idRolFK;
	}

	public void setIdRolFK(Integer idRolFK) {
		this.idRolFK = idRolFK;
	}

	

	public String getIdOpcionFK() {
		return idOpcionFK;
	}



	public void setIdOpcionFK(String idOpcionFK) {
		this.idOpcionFK = idOpcionFK;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "PermisoRolOpcionPK [idRolFK=" + idRolFK + ", idOpcionFK=" + idOpcionFK + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idOpcionFK == null) ? 0 : idOpcionFK.hashCode());
		result = prime * result + ((idRolFK == null) ? 0 : idRolFK.hashCode());
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
		PermisoPK other = (PermisoPK) obj;
		if (idOpcionFK == null) {
			if (other.idOpcionFK != null)
				return false;
		} else if (!idOpcionFK.equals(other.idOpcionFK))
			return false;
		if (idRolFK == null) {
			if (other.idRolFK != null)
				return false;
		} else if (!idRolFK.equals(other.idRolFK))
			return false;
		return true;
	}

	
}
