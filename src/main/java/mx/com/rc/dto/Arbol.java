package mx.com.rc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Arbol implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3344757635285302139L;
	public List<OpcionMenu> menu = new ArrayList<OpcionMenu>();

	
	
	public Arbol() {
		super();
	}


	public List<OpcionMenu> getMenu() {
		return menu;
	}


	public void setMenu(List<OpcionMenu> menu) {
		this.menu = menu;
	}


	public void getRamas(List<OpcionMenu> listaOpcionesPermitidas) {
		
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


	private void getHijos(List<OpcionMenu> listaOpcionesPermitidas, OpcionMenu opcionPadre) {
		List<OpcionMenu> opcionesHijas = new ArrayList<OpcionMenu>();
		
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
