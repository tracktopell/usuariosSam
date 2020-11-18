package mx.com.rc.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="c_estado", schema = "usuarios")
public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado")
	private Integer idEstado;
	
	@Column(name = "nombre", length=30)
	private String nombre;
	
	public Estado() {
		super();
	}
	public Estado(Integer idEstado, String nombre) {
		super();
		this.idEstado = idEstado;
		this.nombre = nombre;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Estado [idEstado=" + idEstado + ", nombre=" + nombre + "]";
	}

}
