package mx.com.rc.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.com.rc.dto.Arbol;
import mx.com.rc.entidades.RolUsuario;
import mx.com.rc.negocio.ManejadorErrNegocio;
import mx.com.rc.negocio.NegocioException;
import mx.com.rc.negocio.Persistor;
import mx.com.rc.persistencia.ManejadorErrPersistencia;
import mx.com.rc.persistencia.PersistenciaException;

@Path("/menu")
@RequestScoped
public class MenuRest {
	@Inject @Persistor
	private IGestorNegocioMenu gestorNegocioMenu;
	
	private final static Logger LOGGER = Logger.getLogger(UsuarioRest.class.getName());
	private Response.ResponseBuilder responseBuilder = null;
	private Map<String, String> response = new HashMap<>();
	
	public MenuRest() {
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMenu(List<RolUsuario> roles) {
		Arbol menu = null;
		LOGGER.info("-->> getMenu:");
		try {
			menu = this.gestorNegocioMenu.getArbolMenu(roles);
			if(menu == null) {
				response.put("mensaje", "No tienes un menu configurado");
				responseBuilder = Response.status(Response.Status.OK).entity(response);
			}else {
				responseBuilder = Response.status(Response.Status.OK).entity(menu);
			}
			
		} catch (PersistenciaException pex) {
			responseBuilder = Response.status(Response.Status.BAD_REQUEST)
					.entity(ManejadorErrPersistencia.getMapaDescripcionDetallada(pex));
		} catch (NegocioException nex) {
			responseBuilder = Response.status(Response.Status.BAD_REQUEST)
					.entity(ManejadorErrNegocio.getMapaDescripcionDetallada(nex));	
		} catch (Exception e) {
			response.put("sugerencia", "Comunicarse al área de sistemas mostradores");
			response.put("mensaje", e.getMessage());
			response.put("Excepcion", e.getCause().toString());
			//GestorBitacora.getBitacoraGeneral();
			LOGGER.log(Level.WARNING, response.toString()); 
			//GestorBitacora.cerrarBitacoraGeneral();
			responseBuilder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return responseBuilder.build();
	}
}
