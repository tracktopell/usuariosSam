package mx.com.rc.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.com.rc.dto.UsuarioConMenu;
import mx.com.rc.entidades.Usuario;
import mx.com.rc.negocio.ManejadorErrNegocio;
import mx.com.rc.negocio.NegocioException;
import mx.com.rc.negocio.Persistor;
import mx.com.rc.persistencia.ManejadorErrPersistencia;
import mx.com.rc.persistencia.PersistenciaException;

@Path("/usuarios")
@RequestScoped
public class UsuarioRest {
	
	@Inject @Persistor
	private IGestorNegocioUsuario gestorNegocioUsuario;
	
	private final static Logger LOGGER = Logger.getLogger(UsuarioRest.class.getName());
	
	private Response.ResponseBuilder builderResponse = null;
	private Map<String, String> response = new HashMap<>();
	
	public UsuarioRest() {
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarios() {
		List<Usuario> listaUsuarios = null;		
		try {
			listaUsuarios = this.gestorNegocioUsuario.getUsuarios(true);
			if(listaUsuarios != null) {
				builderResponse = Response.status(Response.Status.OK).entity(listaUsuarios);
			}else {
				response.put("mensaje", "No hay usuarios en existencia");
				builderResponse = Response.status(Response.Status.OK).entity(response);
			}
			
		} catch (PersistenciaException pex) {
			builderResponse = Response.status(Response.Status.BAD_REQUEST)
					.entity(ManejadorErrPersistencia.getMapaDescripcionDetallada(pex));
		} catch (NegocioException nex) {
			builderResponse = Response.status(Response.Status.BAD_REQUEST)
					.entity(ManejadorErrNegocio.getMapaDescripcionDetallada(nex));	
		} catch (Exception e) {
			response.put("sugerencia", "Comunicarse al área de sistemas mostradores");
			response.put("mensaje", e.getMessage());
			response.put("Excepcion", e.getCause().toString());
			//GestorBitacora.getBitacoraGeneral();
			LOGGER.log(Level.WARNING, response.toString()); 
			//GestorBitacora.cerrarBitacoraGeneral();
			builderResponse = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builderResponse.build();
		
	}

	@Path("/{idUsuario}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAutenticacionUsuario(
			@PathParam("idUsuario")
			String idUsuario) {
		Usuario usuarioConMenu=null;
		
		try {
			usuarioConMenu = this.gestorNegocioUsuario.getUsuario(idUsuario, true);
			if(usuarioConMenu == null) {
				response.put("mensaje", "No Existe el usuario con clave: "+idUsuario);
				builderResponse = Response.status(Response.Status.OK).entity(response);
			}else {
				builderResponse = Response.status(Response.Status.OK).entity(usuarioConMenu);
			}
			
		} catch (NegocioException nEx) {
			
			builderResponse = Response.status(Response.Status.CONFLICT).entity(ManejadorErrNegocio.getMapaDescripcionDetallada(nEx));
			
		} catch (PersistenciaException pex) {
				
			builderResponse = Response.status(Response.Status.CONFLICT).entity(ManejadorErrPersistencia.getMapaDescripcionDetallada(pex));
			
		} catch (Exception e) {
			response.put("error", "No es posible obtener un usuario");
			response.put("mensaje", e.getMessage());
			response.put("causa", e.getCause().toString());
			builderResponse = Response.status(Response.Status.BAD_REQUEST).entity(response);
		} 
		return builderResponse.build();
	}
	
	@Path("/menu/{idUsuario}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMenuUsuario(
			@PathParam("idUsuario")
			String idUsuario) {
		UsuarioConMenu usuarioConMenu=null;
		
		try {
			usuarioConMenu = this.gestorNegocioUsuario.getMenuUsuario(idUsuario);
			
			if(usuarioConMenu == null) {
				response.put("mensaje", "No Existe el usuario con clave: "+idUsuario);
				builderResponse = Response.status(Response.Status.OK).entity(response);
			}else {
				builderResponse = Response.status(Response.Status.OK).entity(usuarioConMenu);
			}
			
		} catch (NegocioException nEx) {
			
			builderResponse = Response.status(Response.Status.CONFLICT).entity(ManejadorErrNegocio.getMapaDescripcionDetallada(nEx));
			
		} catch (PersistenciaException pex) {
				
			builderResponse = Response.status(Response.Status.CONFLICT).entity(ManejadorErrPersistencia.getMapaDescripcionDetallada(pex));
			
		} catch (Exception e) {
			response.put("error", "No es posible obtener un usuario");
			response.put("mensaje", e.getMessage());
			response.put("causa", e.getCause().toString());
			builderResponse = Response.status(Response.Status.BAD_REQUEST).entity(response);
		} 
		return builderResponse.build();
	}	
}