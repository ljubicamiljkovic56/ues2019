package ues.projekat.y.search.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;


public class IndexingDatabase {
	
	
	@SuppressWarnings({ "deprecation", "resource" })
	public static void dtabase() throws IOException {
		Directory indexDir = null;
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, new ues.projekat.y.search.misc.SerbianAnalyzer());
		IndexWriter writer = new IndexWriter(indexDir, iwc);
		try {
			Connection con = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ues", "root", "root");
			Statement stmt = con.createStatement();
			String sql = "select contact_id, displayname, email, firstname, lastname, note from contacts";
			ResultSet rs = stmt.executeQuery(sql);
        
			while (rs.next()) {
				Document doc = new Document();
				doc.add(new TextField("contact_id", rs.getString("contact_id"), Store.YES));
				doc.add(new TextField("displayname", rs.getString("displayname"), Store.YES));
				doc.add(new TextField("email", rs.getString("email"), Store.YES));
				doc.add(new TextField("firstname", rs.getString("firstname"), Store.YES)); 
				doc.add(new TextField("lastname", rs.getString("lastname"), Store.YES));
				doc.add(new TextField("note", rs.getString("note"), Store.YES));
				writer.addDocument(doc);
           }	
		} catch(Exception e) {
			e.printStackTrace();
	}
	}
	
	
}
