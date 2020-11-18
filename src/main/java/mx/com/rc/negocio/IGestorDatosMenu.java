package mx.com.rc.negocio;

import java.util.List;

import mx.com.rc.entidades.Opcion;

public interface IGestorDatosMenu {
	public List<Opcion> leerOpciones(List<String> listadoDeidOpcion);
	
}
