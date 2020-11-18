package mx.com.rc.entidades;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="empresa", schema = "usuarios")
public class Empresa {
	
	@Id
	@Column(name = "id_uname", nullable=false, length=3)
	private String idUname;
	
	@Column(name = "nombre", length=20)
	private String nombre;
	
	@Column(name = "rango", length=4)
	private Integer rango;
	
	@Column(name = "tipo_rango", length=3)
	private String tipoRango;
	
	@OneToOne
	@JoinColumn(name="id_tipo_empresa", referencedColumnName="id_tipo_empresa")
	private TipoEmpresa tipoEmpresa;
	
	@Column(name = "id_cliente", length=6)
	private Integer idCliente;

	@OneToOne
	@JoinColumn(name="id_zona_empresa", referencedColumnName="id_zona_empresa")
	private ZonaEmpresa zonaEmpresa;
	
	@OneToOne
	@JoinColumn(name="id_razon_social", referencedColumnName="id_razon_social")
	private RazonSocial razonSocial;
	
	
	@ManyToOne//(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="id_localidad", referencedColumnName="id_localidad"),
		@JoinColumn(name="id_estado", referencedColumnName="id_estado")
	})
	private Localidad localidad;
	
	@Column(name = "delegacion", length=50)
	private String delegacion;
	
	@Column(name = "calle", length=50)
	private String calle;
	
	@Column(name = "cp", length=6)
	private Integer cp;
	
	@Column(name = "colonia", length=50)
	private String colonia;
	
	@Column(name = "rfc", length=15)
	private String rfc;
	
	@Column(name = "lugar", length=6)
	private Integer lugar;
	
	@Column(name = "sucursal", length=11)
	private Integer sucursal;
	
	@Column(name = "status", nullable=false, length=1)
	private String status;
	
	@Column(name = "fecha_apertura")
	private Calendar fechaApertura;
	
	@Column(name = "subnet", length=15)
	private String subnet;
	
	@Column(name = "cdo", length=3)
	private String cdo;
	
	@Column(name = "tipo_almacen", length=1)
	private String tipoAlmacen;
	
	@Column(name = "correo", length=60)
	private String correo;

	public Empresa() {
		super();
	}

	public Empresa(String idUname, String nombre, Integer rango, String tipoRango, TipoEmpresa tipoEmpresa,
			Integer idCliente, ZonaEmpresa zonaEmpresa, RazonSocial razonSocial,
			mx.com.rc.entidades.Localidad localidad, String delegacion, String calle, Integer cp, String colonia,
			String rfc, Integer lugar, Integer sucursal, String status, Calendar fechaApertura, String subnet,
			String cdo, String tipoAlmacen, String correo) {
		super();
		this.idUname = idUname;
		this.nombre = nombre;
		this.rango = rango;
		this.tipoRango = tipoRango;
		this.tipoEmpresa = tipoEmpresa;
		this.idCliente = idCliente;
		this.zonaEmpresa = zonaEmpresa;
		this.razonSocial = razonSocial;
		this.localidad = localidad;
		this.delegacion = delegacion;
		this.calle = calle;
		this.cp = cp;
		this.colonia = colonia;
		this.rfc = rfc;
		this.lugar = lugar;
		this.sucursal = sucursal;
		this.status = status;
		this.fechaApertura = fechaApertura;
		this.subnet = subnet;
		this.cdo = cdo;
		this.tipoAlmacen = tipoAlmacen;
		this.correo = correo;
	}

	public String getIdUname() {
		return idUname;
	}

	public void setIdUname(String idUname) {
		this.idUname = idUname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getRango() {
		return rango;
	}

	public void setRango(Integer rango) {
		this.rango = rango;
	}

	public String getTipoRango() {
		return tipoRango;
	}

	public void setTipoRango(String tipoRango) {
		this.tipoRango = tipoRango;
	}

	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public ZonaEmpresa getZonaEmpresa() {
		return zonaEmpresa;
	}

	public void setZonaEmpresa(ZonaEmpresa zonaEmpresa) {
		this.zonaEmpresa = zonaEmpresa;
	}

	public RazonSocial getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(RazonSocial razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getDelegacion() {
		return delegacion;
	}

	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getLugar() {
		return lugar;
	}

	public void setLugar(Integer lugar) {
		this.lugar = lugar;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Calendar fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getCdo() {
		return cdo;
	}

	public void setCdo(String cdo) {
		this.cdo = cdo;
	}

	public String getTipoAlmacen() {
		return tipoAlmacen;
	}

	public void setTipoAlmacen(String tipoAlmacen) {
		this.tipoAlmacen = tipoAlmacen;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Empresa [idUname=" + idUname + ", nombre=" + nombre + ", rango=" + rango + ", tipoRango=" + tipoRango
				+ ", tipoEmpresa=" + tipoEmpresa + ", idCliente=" + idCliente + ", zonaEmpresa=" + zonaEmpresa
				+ ", razonSocial=" + razonSocial + ", localidad=" + localidad + ", delegacion=" + delegacion
				+ ", calle=" + calle + ", cp=" + cp + ", colonia=" + colonia + ", rfc=" + rfc + ", lugar=" + lugar
				+ ", sucursal=" + sucursal + ", status=" + status + ", fechaApertura=" + fechaApertura + ", subnet="
				+ subnet + ", cdo=" + cdo + ", tipoAlmacen=" + tipoAlmacen + ", correo=" + correo + "]";
	}

}
