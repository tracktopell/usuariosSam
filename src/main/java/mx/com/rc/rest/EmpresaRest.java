package mx.com.rc.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.com.rc.entidades.Empresa;
import mx.com.rc.negocio.ManejadorErrNegocio;
import mx.com.rc.negocio.NegocioException;
import mx.com.rc.negocio.Persistor;
import mx.com.rc.persistencia.ManejadorErrPersistencia;
import mx.com.rc.persistencia.PersistenciaException;

@Path("/empresas")
@RequestScoped
public class EmpresaRest {
	@Inject @Persistor
	private IGestorNegocioUsuario gestorNegocio;
	
	private final static Logger LOGGER = Logger.getLogger(UsuarioRest.class.getName());
	
	private Response.ResponseBuilder builderResponse = null;
	private Map<String, String> response = new HashMap<>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpresas() {
		List<Empresa> listaEmpresas = null;
		
		try {
			
			listaEmpresas = this.gestorNegocio.getEmpresas();
			if(listaEmpresas != null) {
				builderResponse = Response.status(Response.Status.OK).entity(listaEmpresas);
			}else {
				response.put("mensaje", "No hay empresas en existencia");
				builderResponse = Response.status(Response.Status.OK).entity(response);
			}
			
		} catch (Exception e) {
			response.put("error", "No es posible obtener la lista de empresas");
			response.put("mensaje", e.getMessage());
			response.put("causa", e.getCause().toString());
			
			builderResponse = Response.status(Response.Status.BAD_REQUEST).entity(response);
			
		}
		return builderResponse.build();
		
	}
	@Path("{idUname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpresaXidUname(
			@PathParam("idUname")
			String idUname) {
		Empresa empresa=null;
		
		try {
			empresa = this.gestorNegocio.getEmpresa(idUname);
			if(empresa == null) {
				response.put("mensaje", "No Existe la empresa con uname: "+idUname);
				builderResponse = Response.status(Response.Status.OK).entity(response);
			}else {
				builderResponse = Response.status(Response.Status.OK).entity(empresa);
			}
			
		} catch (NegocioException nEx) {
			
			builderResponse = Response.status(Response.Status.CONFLICT).entity(ManejadorErrNegocio.getMapaDescripcionDetallada(nEx));
			
		} catch (PersistenciaException pex) {
				
			builderResponse = Response.status(Response.Status.CONFLICT).entity(ManejadorErrPersistencia.getMapaDescripcionDetallada(pex));
			
		} catch (Exception e) {
			response.put("error", "No es posible obtener la empresa");
			response.put("mensaje", e.getMessage());
			response.put("causa", e.getCause().toString());
			builderResponse = Response.status(Response.Status.BAD_REQUEST).entity(response);
		} 
		return builderResponse.build();
	}
}
