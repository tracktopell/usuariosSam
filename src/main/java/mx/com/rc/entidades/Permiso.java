package mx.com.rc.entidades;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="permiso", schema = "usuarios")
public class Permiso {
	
	@EmbeddedId
	private PermisoPK llave;
		
	@Column(name = "descripcion", length=100)
	private String descripcion;
	
	@Column(name = "fecha_alta")	
	private Calendar fechaAlta;
	
	@Column(name = "fecha_baja")
	private Calendar fechaBaja;
	
	@Column(name = "fecha_caducidad")
	private Calendar fechaCaducidad;
	
	@Column(name = "status", length=1)
	private Integer status;
	
	@ManyToOne
	@MapsId("idRolFK")
	@JoinColumn(name="id_rol")
	private Rol rol;
	
	@ManyToOne
	@MapsId("idOpcionFK")
	@JoinColumn(name="id_opcion")
	private Opcion opcion;
	
	
	public Permiso() {
		super();
	}

	
	public Permiso(PermisoPK llave, String descripcion, Calendar fechaAlta, Calendar fechaBaja,
			Calendar fechaCaducidad, Integer status, Rol rol, Opcion opcion) {
		super();
		this.llave = llave;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.fechaCaducidad = fechaCaducidad;
		this.status = status;
		this.rol = rol;
		this.opcion = opcion;
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
	public Calendar getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Calendar fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Opcion getOpcion() {
		return opcion;
	}
	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}


	@Override
	public String toString() {
		return "PermisoRolOpcion [llave=" + llave + ", descripcion=" + descripcion + ", fechaAlta=" + fechaAlta
				+ ", fechaBaja=" + fechaBaja + ", fechaCaducidad=" + fechaCaducidad + ", status=" + status + ", rol="
				+ rol + ", opcion=" + opcion + "]";
	}
	
}
