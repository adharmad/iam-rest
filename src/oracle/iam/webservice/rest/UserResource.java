package oracle.iam.webservice.rest;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import oracle.iam.webservice.domain.UserVO;
import oracle.iam.webservice.provider.UserProvider;

@Path("/users")
public class UserResource {
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public UserVO findUser(@PathParam("id") String id) {
		System.out.println("findUser");
		UserVO u = UserProvider.getProvider().findUser(id);
		return u;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<UserVO> findUsers() {
		System.out.println("findUsers");
		List<UserVO> users = UserProvider.getProvider().findUsers();
		return users;
	}
	
	@POST
	@Produces({MediaType.TEXT_HTML})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createUser(@FormParam("login") String login,
		@FormParam("first") String first,
		@FormParam("last") String last,
		@FormParam("org") String org
	) {
		System.out.println("create user");
		String id = UserProvider.getProvider().createUser(login, first, last, org);
		//return users;
		return Response.created(URI.create("/" + id)).build();
	}			
}
