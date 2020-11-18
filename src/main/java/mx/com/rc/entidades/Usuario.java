package mx.com.rc.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="usuario", schema = "usuarios")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usuario", length=20)
	private String usuario;
	
	@Column(name = "nombre", length=50)
	private String nombre;
	
	@Column(name = "a_materno", length=50)
	private String aMaterno;
	
	@Column(name = "a_paterno", length=50)
	private String aPaterno;
	
	@Column(name = "fecha_alta")
	private Calendar fechaAlta;
	
	@Column(name = "fecha_baja")
	private Calendar fechaBaja;
	
	@Column(name = "fecha_caducidad")
	private Calendar fechaCaducidad;
	
	@Column(name = "numero_empleado")
	private Integer numeroEmpleado;
	
	@Column(name = "password", length=32)
	private String password;
	
	@Column(name = "status", length=1)
	private Integer status;
	
	@OneToOne
	@JoinColumn(name="id_uname", referencedColumnName="id_uname")
	private Empresa empresa;
	
	@OneToMany(mappedBy="usuario")
	private Set<RolUsuario> roles;
	
	public Usuario() {
		super();
		
	}
	
	/*public Usuario(String usuario, String nombre, String aMaterno, String aPaterno, Calendar fechaAlta,
			Calendar fechaBaja, Calendar fechaCaducidad, Integer numeroEmpleado, String password, Integer status,
			Empresa empresa, Set<UsuarioRol> roles) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.aMaterno = aMaterno;
		this.aPaterno = aPaterno;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.fechaCaducidad = fechaCaducidad;
		this.numeroEmpleado = numeroEmpleado;
		this.password = password;
		this.status = status;
		this.empresa = empresa;
		this.roles = roles;
	}*/

	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}
	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Set<RolUsuario> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolUsuario> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", nombre=" + nombre + ", aMaterno=" + aMaterno + ", aPaterno="
				+ aPaterno + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", fechaCaducidad="
				+ fechaCaducidad + ", numeroEmpleado=" + numeroEmpleado + ", password=" + password + ", status="
				+ status + ", empresa=" + empresa + ", roles=" + roles + "]";
	}

	
	
}
