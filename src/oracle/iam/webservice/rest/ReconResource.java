package oracle.iam.webservice.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import oracle.iam.webservice.domain.ReconEvent;
import oracle.iam.webservice.provider.ReconProvider;

@Path("/reconciliation")
public class ReconResource {

	@POST
	@Produces({MediaType.TEXT_HTML})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createReconEvent(ReconEvent re) {
		System.out.println("create recon event");
		String id = "";
		id = ReconProvider.getProvider().createReconEvent(re);
		return Response.status(201).entity(id).build();
	}
	
	@DELETE
	@Produces({MediaType.TEXT_HTML})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDeleteReconEvent(ReconEvent re) {
		System.out.println("create delete recon event");
		String id = "";
		
		id = ReconProvider.getProvider().createDeleteReconEvent(re);
		return Response.status(201).entity(id).build();
	}		
}
