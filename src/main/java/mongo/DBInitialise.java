package mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import pojos.UserData;

public class DBInitialise {

	
	
	public List<UserData> createDocument(UserData user) {
			

		return this.insertDocument(user);
	}

	
	private List<UserData> insertDocument(UserData user){
	
		MongoCollection<Document> collection = this.getCollection();
		 //insert new document
	    collection.insertOne(this.getDocument(user));
	     
	  // Performing a read operation on the collection.
	    FindIterable<Document> documents = collection.find();
	    return this.getUsers(documents);
	}
	
	private Document getDocument(UserData user) {
		 
        
        Document document = new Document("name", user.getName()) 
        	      .append("city", user.getCity())
        	      .append("state", user.getState());
        
        return document;
        
	}
	
	private List<UserData> getUsers(FindIterable<Document> documents){
		
		MongoCursor<Document> cursor = documents.iterator();
		  List<UserData> users = new ArrayList<UserData>();
		  while(cursor.hasNext()) {
			  Document doc = cursor.next();
			  UserData userData = new UserData();
			  	 ObjectId id = doc.getObjectId("_id");
			     userData.setObjectID(id.toString());
			  	 userData.setCity(doc.getString("city"));
		    	 userData.setName(doc.getString("name"));
		    	 userData.setState(doc.getString("state"));
		    	 users.add(userData);
		  }
		   
		  return users;
	}
	
	
	public MongoCollection<Document> getCollection(){
		 

		MongoClient mongoClient = MongoClients.create("mongodb+srv://demo:rishabh1991@democluster-cbuby."
				+ "mongodb.net/test?retryWrites=true&w=majority");
		
		
	     // Accessing the database 
	     MongoDatabase database = mongoClient.getDatabase("demo");
	     MongoCollection<Document> collection = database.getCollection("paperNotes");
	     
	     return collection;
	}
	
}
