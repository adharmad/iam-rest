package oracle.iam.webservice.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserClient {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		System.out.println(service.path("rest").path("helloworld")
				.accept(MediaType.TEXT_PLAIN).get(String.class)
				.toString());
		
		// Get xml
		//ClientResponse r = service.path("rest").path("users/1")
		//		.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
		//System.out.println(r.toString());
		//String s = r.getEntity(String.class);
		//System.out.println(s);
		
		// Get json		
		ClientResponse r1 = service.path("rest").path("users")
			.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		System.out.println(r1.toString());
		String s1 = r1.getEntity(String.class);
		System.out.println(s1);
		
		// Get XML
		//System.out.println(service.path("rest").path("hello")
		//		.accept(MediaType.TEXT_XML).get(String.class));

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8888/oimrest").build();
	}
}