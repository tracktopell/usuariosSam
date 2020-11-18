package mx.com.rc.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="c_tipo_empresa", schema = "usuarios")
public class TipoEmpresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_empresa")
	private Integer idTipoEmpresa;
	
	@Column(name = "descripcion", length=50)
	private String descripcion;

	public TipoEmpresa() {
		super();
	}

	public TipoEmpresa(Integer idTipoEmpresa, String descripcion) {
		super();
		this.idTipoEmpresa = idTipoEmpresa;
		this.descripcion = descripcion;
	}

	public Integer getIdTipoEmpresa() {
		return idTipoEmpresa;
	}

	public void setIdTipoEmpresa(Integer idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoEmpresa [idTipoEmpresa=" + idTipoEmpresa + ", descripcion=" + descripcion + "]";
	}
	
}
