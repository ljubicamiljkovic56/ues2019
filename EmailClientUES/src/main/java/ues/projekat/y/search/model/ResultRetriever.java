package ues.projekat.y.search.model;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

public class ResultRetriever {

private TopScoreDocCollector collector;
	
	public ResultRetriever(){
		collector=TopScoreDocCollector.create(10,true);
	}
	
	public void printSearchResults(Query query, File indexDir){
		try{
			Directory fsDir=new SimpleFSDirectory(indexDir);
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			IndexSearcher is = new IndexSearcher(ireader);
			is.search(query, collector);
			
			ScoreDoc[] hits=collector.topDocs().scoreDocs;
			System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId=hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + doc.get("contact_id"));
				System.out.println("\t" + doc.get("displayname"));
				System.out.println("\t" + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
}
