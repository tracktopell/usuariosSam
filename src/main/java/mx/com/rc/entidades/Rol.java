package mx.com.rc.entidades;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="rol", schema = "usuarios")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer idRol;
	
	@Column(name = "descripcion", length=50)
	private String descripcion;
	
	@Column(name = "fecha_alta")
	private Calendar fechaAlta;
	
	@Column(name = "fecha_baja")
	private Calendar fechaBaja;
	
	@Column(name = "status", length=1)
	private Integer status;
	
	@OneToMany(mappedBy="rol")
	private Set<Permiso> permisos;
	
	/*@ManyToOne//(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="id_rol", referencedColumnName="id_rol"),
		@JoinColumn(name="id_opcion", referencedColumnName="id_opcion")
	})
	private PermisoRolOpcion permisoRolOpcion;*/
	
	public Rol() {
		super();
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Calendar getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Calendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Calendar getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Calendar fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", descripcion=" + descripcion + ", fechaAlta=" + fechaAlta + ", fechaBaja="
				+ fechaBaja + ", status=" + status + ", permisos=" + permisos + "]";
	}

	


}
