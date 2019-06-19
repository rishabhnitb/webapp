package persist;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import pojos.PaperNote;

public class NotePersist {

	
	public List<PaperNote> createNote(PaperNote note, MongoCollection<Document> collection) {
		
		
		collection.insertOne(this.setNote(note));
		
		
		
		
		return getAllNotes(collection);
	}
	
	private Document setNote(PaperNote note) {
		
		Document document = new Document("paper", note.getPaper()) 
      	      .append("shortDescription", note.getShortDescription())
      	      .append("longDescription", note.getLongDescription())
      	      .append("likesCount", note.getLikesCount());
      
      return document;
	}
	
	public List<PaperNote> getAllNotes(MongoCollection<Document> collection){
		
		// Performing a read operation on the collection.
	    FindIterable<Document> documents = collection.find();
		
		MongoCursor<Document> cursor = documents.iterator();
		  List<PaperNote> notes = new ArrayList<PaperNote>();
		  while(cursor.hasNext()) {
			  Document doc = cursor.next();
			  PaperNote note = new PaperNote();
			  	 ObjectId id = doc.getObjectId("_id");
			     note.setObjectID(id.toString());
			  	 note.setPaper(doc.getString("paper"));
			  	 note.setShortDescription(doc.getString("shortDescription"));
			  	 note.setLongDescription(doc.getString("longDescription"));
			  	 note.setLikesCount(doc.getInteger("likesCount", 0));
		    	 notes.add(note);
		  }
		   
		  return notes;
	}
}
