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
@Table(name="rol_usuario", schema = "usuarios")
public class RolUsuario {
	@EmbeddedId
	private RolUsuarioPK llave;
	
	@Column(name = "descripcion", length=50)
	private String descripcion;
	
	@Column(name = "fecha_alta")
	private Calendar fechaAlta;
	
	@Column(name = "fecha_baja")
	private Calendar fechaBaja;
	
	@Column(name = "status", length=1)
	private Integer status;
	
	@ManyToOne
	@MapsId("idRolFK")
	@JoinColumn(name="id_rol")
	private Rol rol;
	
	@ManyToOne
	@MapsId("usuarioFK")
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	public RolUsuario() {
		super();
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public RolUsuarioPK getLlave() {
		return llave;
	}
	public void setLlave(RolUsuarioPK llave) {
		this.llave = llave;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	@Override
	public String toString() {
		return "UsuarioRol [llave=" + llave + ", descripcion=" + descripcion + ", fechaAlta=" + fechaAlta
				+ ", fechaBaja=" + fechaBaja + ", status=" + status + ", rol=" + rol + ", usuario=" + usuario + "]";
	}
	
}
