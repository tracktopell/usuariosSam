package mx.com.rc.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="c_zona_empresa", schema = "usuarios")
public class ZonaEmpresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_zona_empresa")
	private Integer idZonaEmpresa;
	@Column(name = "nombre")
	private String nombre;
	
	public ZonaEmpresa() {
		super();
	}
	public ZonaEmpresa(Integer idZonaEmpresa, String nombre) {
		super();
		this.idZonaEmpresa = idZonaEmpresa;
		this.nombre = nombre;
	}
	public Integer getIdZonaEmpresa() {
		return idZonaEmpresa;
	}
	public void setIdZonaEmpresa(Integer idZonaEmpresa) {
		this.idZonaEmpresa = idZonaEmpresa;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "ZonaEmpresa [idZonaEmpresa=" + idZonaEmpresa + ", nombre=" + nombre + "]";
	}
	
}
