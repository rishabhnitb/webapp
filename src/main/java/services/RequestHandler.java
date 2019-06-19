package services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mongo.DBInitialise;
import persist.NotePersist;
import pojos.PaperNote;
import pojos.UserData;

@Path("/data")
public class RequestHandler {

	DBInitialise mongo = new DBInitialise();
	NotePersist notePersist = new NotePersist();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String checkJersey() {
		return "warking fine!";
	}
	
	
	@GET
	@Path("note")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaperNote> getAllNotes(){
		
		return notePersist.getAllNotes(mongo.getCollection());
	}
	
	@POST
	@Path("note")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaperNote> createNote(PaperNote note) {
		
		//creating a note
		return notePersist.createNote(note, mongo.getCollection());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserData> createDocument(UserData user) {
		
		
		return mongo.createDocument(user);
		
	}
}
