package mx.com.rc.negocio;

import java.util.List;

import mx.com.rc.entidades.Empresa;
import mx.com.rc.entidades.Opcion;
import mx.com.rc.entidades.Permiso;
import mx.com.rc.entidades.Usuario;

public interface IGestorDatosUsuario {
	public List<Usuario> leerUsuarios(boolean conRoles);
	public Usuario leerUsuario(String ususario, boolean conRoles);
	public List<Empresa> leerEmpresas();
	public List<Empresa> leerEmpresasXtipoEmpresa(String tipoEmpresa);
	public Empresa leerEmpresa(String empresa);
	public List<Permiso> leerPermisosXrol(String idRol);
	public Opcion leerOpcionXidOpcion(String idOpcion, boolean conHijos);
}
