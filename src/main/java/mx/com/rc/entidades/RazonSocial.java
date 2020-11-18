package mx.com.rc.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="c_razon_social", schema = "usuarios")
public class RazonSocial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_razon_social")
	private Integer idRazonSocial;
	@Column(name = "nombre", length=60)
	private String nombre;
	
	public RazonSocial() {
		super();
	}

	public RazonSocial(Integer idRazonSocial, String nombre) {
		this.idRazonSocial = idRazonSocial;
		this.nombre = nombre;
	}

	public Integer getIdRazonSocial() {
		return idRazonSocial;
	}

	public void setIdRazonSocial(Integer idRazonSocial) {
		this.idRazonSocial = idRazonSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "RazonSocial [idRazonSocial=" + idRazonSocial + ", nombre=" + nombre + "]";
	}
	
}
