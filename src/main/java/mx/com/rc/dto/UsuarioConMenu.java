package mx.com.rc.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import mx.com.rc.entidades.Usuario;

public class UsuarioConMenu implements Serializable{
	private Usuario usuario;
	private List<OpcionPermiso> opcionMenuRaizList;
	
	public UsuarioConMenu() {}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the opcionMenuRaizList
	 */
	public List<OpcionPermiso> getOpcionMenuRaizList() {
		return opcionMenuRaizList;
	}

	/**
	 * @param opcionMenuRaizList the opcionMenuRaizList to set
	 */
	public void setOpcionMenuRaizList(List<OpcionPermiso> opcionMenuRaizList) {
		this.opcionMenuRaizList = opcionMenuRaizList;
	}
	
}

