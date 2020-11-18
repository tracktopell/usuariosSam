package mx.com.rc.entidades;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="c_localidad", schema = "usuarios")
public class Localidad {
	
	@EmbeddedId
	private LocalidadPK llave;
	
	@ManyToOne
	@MapsId("idEstadoPK")
	@JoinColumn(name="id_estado")
	private Estado estado;
	
	@Column(name = "nombre", length=100)
	private String nombre;
	
	public Localidad() {
		super();
	}

	public Localidad(LocalidadPK llave, Estado estado, String nombre) {
		super();
		this.llave = llave;
		this.estado = estado;
		this.nombre = nombre;
	}

	public LocalidadPK getLlave() {
		return llave;
	}

	public void setLlave(LocalidadPK llave) {
		this.llave = llave;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Localidad [llave=" + llave + ", estado=" + estado + ", nombre=" + nombre + "]";
	}
	

}
