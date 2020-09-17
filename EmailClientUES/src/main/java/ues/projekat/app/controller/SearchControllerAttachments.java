package ues.projekat.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.y.search.model.FoundAttachment;

@RestController
@RequestMapping("/searchattach")
@CrossOrigin
public class SearchControllerAttachments {
	
	private TopScoreDocCollector collector;
	
	@PostMapping(value = "/term/attach")
	public ResponseEntity<List<FoundAttachment>> termSearchAttachment(@RequestParam String field1, @RequestParam String term1) throws Exception {
		
		List<FoundAttachment> foundAttachment = new ArrayList<FoundAttachment>();
		
		File indexDirAttach;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirAttach = new File(rb.getString("indexDirAttach"));
		
		Term t = new Term(field1, term1);
		Query query = new TermQuery(t);
		
		System.out.println(query);
		
		collector = TopScoreDocCollector.create(10,true);
		
		try{
			//kreiramo novi direktorijum
			Directory fsDir = new SimpleFSDirectory(indexDirAttach);
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
			
			FoundAttachment foundAttachmentobj = new FoundAttachment(); 
			
			//prikaz svih prondajenih podataka
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Attachment id: " + doc.get("attachment_id"));
				foundAttachmentobj.setId(doc.get("attachment_id"));
				
				System.out.println("\t" + "Mime type: " + doc.get("mimetype"));
				foundAttachmentobj.setMimetype(doc.get("mimetype"));
				
				System.out.println("\t" + "Name: " + doc.get("name"));
				foundAttachmentobj.setName(doc.get("name"));
				
				System.out.println("\t" + "Path: " + doc.get("path"));
				foundAttachmentobj.setPath(doc.get("path"));
				
				System.out.println("\t" + "Datum:" + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundAttachment.add(foundAttachmentobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	
		return new ResponseEntity<List<FoundAttachment>>(foundAttachment,HttpStatus.OK);
	}

}