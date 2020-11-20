package mx.com.rc.rest;

import java.util.List;

import mx.com.rc.dto.UsuarioConMenu;
import mx.com.rc.entidades.Empresa;
import mx.com.rc.entidades.Usuario;


public interface IGestorNegocioUsuario {
	public List<Usuario> getUsuarios(boolean conRoles);
	public Usuario getUsuario(String idUsuario, boolean conRoles);
	public UsuarioConMenu getMenuUsuario(String idUsuario);
	public List<Empresa> getEmpresas();
	public Empresa getEmpresa(String idUname);
}
