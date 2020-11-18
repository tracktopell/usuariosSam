package mx.com.rc.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="opcion", schema = "usuarios")
public class Opcion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_opcion", nullable=false, length=20)
	private String idOpcion;

	@Column(name = "nombre_opcion", length=100)
	private String nombreOpcion;
	
	@Column(name = "parametros")
	private boolean parametros;
	
	@Column(name = "url", length=250)
	private String url;
	
	@Column(name = "fecha_alta")
	private Calendar fechaAlta;
	
	@ManyToOne
	@JoinColumn(name="id_opcion_padre")
	private Opcion opcionPadre;
	
	@OneToMany(mappedBy="opcionPadre")
	private Set<Opcion> opcionesMenu;
	
	public Opcion() {
		super();
	}
	
	public Opcion(String idOpcion, Opcion padre) {
		this.idOpcion = idOpcion;
		this.opcionPadre = padre;
		this.opcionesMenu = new HashSet<Opcion>();
	}
	public String getNombreOpcion() {
		return nombreOpcion;
	}
	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}
	public boolean isParametros() {
		return parametros;
	}
	public void setParametros(boolean parametros) {
		this.parametros = parametros;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(String idOpcion) {
		this.idOpcion = idOpcion;
	}

	public Opcion getOpcionPadre() {
		return opcionPadre;
	}

	public void setOpcionPadre(Opcion opcionPadre) {
		this.opcionPadre = opcionPadre;
	}

	public Set<Opcion> getOpcionesMenu() {
		return opcionesMenu;
	}

	public void setOpcionesMenu(Set<Opcion> opcionesMenu) {
		this.opcionesMenu = opcionesMenu;
	}

	public Calendar getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Calendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "Opcion [idOpcion=" + idOpcion + ", nombreOpcion=" + nombreOpcion + ", parametros=" + parametros
				+ ", url=" + url + ", fechaAlta=" + fechaAlta + ", opcionPadre=" + opcionPadre + ", opcionesMenu="
				+ opcionesMenu + "]";
	}
	
	
}
