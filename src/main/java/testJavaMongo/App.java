package testJavaMongo;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("MAIN");
System.out.println("**********");
System.out.println(" ");
try {
	MongoClient mongo = new MongoClient("localhost", 27017);
	
	DB db = mongo.getDB("testdb");
	
	List<String> dbs = mongo.getDatabaseNames();
	for(String mdb : dbs){
		System.out.println(mdb);
	}
	
	DBCollection table = db.getCollection("user");
	
	/****Insert****/
	BasicDBObject document = new BasicDBObject();
	document.put("name", "guillermo");
	document.put("age", 40);
	document.put("created date", new Date());
	
	table.insert(document);
	
	/*******find & display *********/
	BasicDBObject searchQuery = new BasicDBObject();
	searchQuery.put("name", "guillermo");
	
	DBCursor cursor = table.find(searchQuery);
	while (cursor.hasNext()) {
		System.out.println(cursor.next());
	}
	
	/********update********************/
	BasicDBObject query = new BasicDBObject();
	query.put("name", "guillermo");
	
	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", "guillermoso");
	
	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);
	table.update(query, updateObj);
	
	/***********find & display**************/
	BasicDBObject searchQuery2 = new BasicDBObject().append("name", "guillermoso");
	DBCursor cursor2 = table.find(searchQuery2);
	while (cursor2.hasNext()) {
		System.out.println(cursor2.next());
	}
	/***************delete*******************/
	BasicDBObject doc = new BasicDBObject();
	document.put("name", "guillermoso");
	table.remove(doc);	
	
	System.out.println("DONE");
	
} catch (UnknownHostException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


	}

}
