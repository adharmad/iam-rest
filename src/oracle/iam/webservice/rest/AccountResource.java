package oracle.iam.webservice.rest;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import oracle.iam.webservice.domain.AccountVO;
import oracle.iam.webservice.provider.UserProvider;

@Path("/accounts")
public class AccountResource {
	/*

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AccountVO getAccountDetails(@PathParam("id") String id) {
		System.out.println("get account details");
		//UserVO u = UserProvider.getProvider().findUser(id);
		return null;
	}
	
	@GET
	@Path("/{userlogin}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public AccountVO getAccountsForUser(@PathParam("userlogin") String userLogin) {
		System.out.println("get account details for user");
		//UserVO u = UserProvider.getProvider().findUser(id);
		return null;
	}	
		
	
	@POST
	@Produces({MediaType.TEXT_HTML})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createAccount(AccountVO account) {
		System.out.println("create account");
		String id = "";
		//String id = UserProvider.getProvider().createUser(login, first, last, org);
		//return users;
		return Response.created(URI.create("/" + id)).build();
	}	
	
	@DELETE
	@Produces({MediaType.TEXT_HTML})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String revokeAccount(AccountVO account) {
		System.out.println("revoke account");
		String id = "";
		//String id = UserProvider.getProvider().createUser(login, first, last, org);
		//return users;
		return "";
	}		
	*/
}
