package saasWatchAgent;

import java.net.URI;
import java.util.Date;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RESTfulClient {
	public static void main(String[] args) {
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());

	    String input = "{\"userTag\":\"Saas 1 For testing\",\"agentID\":\"1\",\"agentID\":\"1\",\"saasPID\":\"1\",\"traceTime\":\"" + (new Date()).toString() + "\",\"traceType\":\"Connectivity\",\"saasFD\":\"Sample application\" }";
	    System.out.println(input);
		ClientResponse response = service.type("application/json")
		   .post(ClientResponse.class, input);
//		System.out.println(service.toString());
//		if (response.getStatus() != 201) {
//			throw new RuntimeException("Failed : HTTP error code : "
//			     + response.getStatus());
//		}
 
		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	  }

	  private static URI getBaseURI() {
	    //return UriBuilder.fromUri("http://localhost:8080/SaaSWatch_Masters_Project").build();
		  return UriBuilder.fromUri("http://127.0.0.1:8082/rest/Trace/?$method=update").build();
	  }
}
