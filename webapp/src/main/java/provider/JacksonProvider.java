package provider;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class JacksonProvider implements ContextResolver<ObjectMapper>{

	private static final ObjectMapper mapper = new ObjectMapper(); 
	
	static {
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
		
	}
	
	public JacksonProvider() {
		// TODO Auto-generated constructor stub
		System.out.println("initializing my jackson provider!");
	}
	
	@Override
	public ObjectMapper getContext(Class<?> type) {
		// TODO Auto-generated method stub
		System.out.println("My jackson provider was called with type "+type);
		return mapper;
	}

}
