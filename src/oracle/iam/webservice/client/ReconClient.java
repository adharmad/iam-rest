package oracle.iam.webservice.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ReconClient {

	public static void main(String[] args) throws Exception {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client
		   .resource("http://localhost:8888/iamrest/rest/reconciliation");

		// post json
		String input = "{\"id\":\"\", \"objName\":\"Xellerate User\", \"data\": {\"login\":\"test2\", \"first\":\"test2first\", \"last\":\"test2Last\", \"org\":\"Xellerate Users\", \"role\":\"Full-Time\", \"xltype\":\"End-User\"}}";

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
 
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);		
	}
}
