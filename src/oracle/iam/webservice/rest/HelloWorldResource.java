package oracle.iam.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class HelloWorldResource {
	
	@GET
	@Produces("text/plain")
	public String sayHello() {
		System.out.println("Hello RESTful world!");
		return "Hello RESTful world!";
	}
	
}
