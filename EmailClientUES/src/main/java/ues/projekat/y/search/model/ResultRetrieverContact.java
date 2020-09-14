package ues.projekat.y.search.model;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

public class ResultRetrieverContact {

//kolektor nam treba za pogotke
private TopScoreDocCollector collector;
	
	public ResultRetrieverContact(){
		//pravimo novi kolektor na osnovu proja pogodaka
		collector = TopScoreDocCollector.create(10,true);
	}
	
	//parametri su upit i indexDir
	public void printSearchResults(Query query, File indexDir){
		try{
			//kreiramo novi direktorijum
			Directory fsDir = new SimpleFSDirectory(indexDir);
			//citamo indekse iz direktorijuma
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			//IndexSearcher radi search nad indexReaderom
			IndexSearcher is = new IndexSearcher(ireader);
			//search nad zadatim upitom i kolektorom
			is.search(query, collector);
			
			
			//lista pogodaka
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			//prikaz broja pronadjenih podataka
			System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");
			
			
			//prikaz svih prondajenih podataka
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Contact id: " + doc.get("contact_id"));
				System.out.println("\t" + "Displayname: " + doc.get("displayname"));
				System.out.println("\t" + "Email: " + doc.get("email"));
				System.out.println("\t" + "Firstname: " + doc.get("firstname"));
				System.out.println("\t" + "Lastname: " + doc.get("lastname"));
				System.out.println("\t" + "Note: " + doc.get("note"));
				System.out.println("\t" + "Datum:" + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
}
