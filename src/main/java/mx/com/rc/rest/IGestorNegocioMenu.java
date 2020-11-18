package mx.com.rc.rest;

import java.util.List;

import mx.com.rc.dto.Arbol;
import mx.com.rc.entidades.RolUsuario;

public interface IGestorNegocioMenu {
	public Arbol getArbolMenu(List<RolUsuario> roles);
}
