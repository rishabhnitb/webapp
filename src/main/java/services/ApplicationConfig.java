package services;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

import pojos.UserData;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application{

	@Override
	public Set<Class<?>> getClasses(){
		
		Set<Class<?>> resources = new java.util.HashSet<>();
		System.out.println("REST configuration starting : getClasses");
		
		resources.add(pojos.UserData.class);
		resources.add(provider.JacksonProvider.class);
		resources.add(services.RequestHandler.class);
		
		return resources;
	}
    
      
}
