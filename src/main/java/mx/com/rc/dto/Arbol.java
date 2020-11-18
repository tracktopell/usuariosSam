package mx.com.rc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Arbol implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3344757635285302139L;
	public List<OpcionPermiso> menu = new ArrayList<OpcionPermiso>();

	
	
	public Arbol() {
		super();
	}


	public List<OpcionPermiso> getMenu() {
		return menu;
	}


	public void setMenu(List<OpcionPermiso> menu) {
		this.menu = menu;
	}


	public void getRamas(List<OpcionPermiso> listaOpcionesPermitidas) {
		
		for(int i = 0; i < listaOpcionesPermitidas.size(); i++) {
			System.out.println("ramas: " + listaOpcionesPermitidas.get(i).toString());
			if(listaOpcionesPermitidas.get(i).getIdOpcionPadre() == null) {
				menu.add(listaOpcionesPermitidas.get(i));
				listaOpcionesPermitidas.remove(i);
			}
		}
		System.out.println("ListaOpcionesPermitidasfiltradas: " + listaOpcionesPermitidas.toString());
		
		for(int i = 0; i < this.menu.size(); i++) {
			this.getHijos(listaOpcionesPermitidas, this.menu.get(i));
		}
		
	}


	private void getHijos(List<OpcionPermiso> listaOpcionesPermitidas, OpcionPermiso opcionPadre) {
		List<OpcionPermiso> opcionesHijas = new ArrayList<OpcionPermiso>();
		
		for(int i = 0; i < listaOpcionesPermitidas.size(); i++) {
			
			if(listaOpcionesPermitidas.get(i).getIdOpcionPadre() != null) {
				if(listaOpcionesPermitidas.get(i).getIdOpcionPadre()==opcionPadre.getIdOpcion()) {
					
					
					opcionesHijas.add(opcionPadre);
					listaOpcionesPermitidas.remove(i);
					
				}
			}
			
		}
		opcionPadre.setSubOpcionesList(opcionesHijas);
		
	}

}
