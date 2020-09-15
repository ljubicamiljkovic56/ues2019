package ues.projekat.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.y.search.misc.SerbianAnalyzer;
import ues.projekat.y.search.model.FoundMessage;

@RestController
@RequestMapping("/searchm")
@CrossOrigin
public class SearchControllerMessages {
	
	private TopScoreDocCollector collector;

	//obicna pretraga (ime polja: rec)
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/regular/message")
	public ResponseEntity<List<FoundMessage>> regularSearchMessage(@RequestParam String regularSearch) throws Exception {
		
		List<FoundMessage> foundMessage = new ArrayList<FoundMessage>();
		
		File indexDirMessages;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirMessages = new File(rb.getString("indexDirMessages"));
		
		QueryParser qp = new QueryParser(Version.LUCENE_40,"sadrzaj_fajla", new SerbianAnalyzer());
		
		Query query = qp.parse(regularSearch);
		
		System.out.println(query);
		
		collector = TopScoreDocCollector.create(10,true);
		
		try{
			Directory fsDir = new SimpleFSDirectory(indexDirMessages);
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			IndexSearcher is = new IndexSearcher(ireader);
			is.search(query, collector);
			
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");
			
			FoundMessage foundMessageobj = new FoundMessage();
			
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Message id: " + doc.get("message_id"));
				foundMessageobj.setId(doc.get("message_id"));
				
				System.out.println("\t" + "From: " + doc.get("from"));
				foundMessageobj.setFrom(doc.get("from"));
				
				System.out.println("\t" + "To: " + doc.get("to"));
				foundMessageobj.setTo(doc.get("to"));
				
				System.out.println("\t" + "CC: " + doc.get("cc"));
				foundMessageobj.setCc(doc.get("cc"));
				
				System.out.println("\t" + "BCC: " + doc.get("bcc"));
				foundMessageobj.setBcc(doc.get("bcc"));
				
				System.out.println("\t" + "Subject: " + doc.get("subject"));
				foundMessageobj.setSubject(doc.get("subject"));
				
				System.out.println("\t" + "Content: " + doc.get("content"));
				foundMessageobj.setContent(doc.get("content"));
				
				System.out.println("\t" + "Datum: " + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundMessage.add(foundMessageobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		return new ResponseEntity<List<FoundMessage>>(foundMessage, HttpStatus.OK);
		
		
	}
	
	//boolean pretraga (and, or, not)	
	@PostMapping(value = "/boolean/message")
	public ResponseEntity<List<FoundMessage>> booleanSearchMessage(@RequestParam String field1, @RequestParam String term1,
			@RequestParam String field2, @RequestParam String term2, @RequestParam String op) throws IOException {
		
		List<FoundMessage> foundMessage = new ArrayList<FoundMessage>();
		
		File indexDirMessages;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirMessages = new File(rb.getString("indexDirMessages"));
		
		TermQuery query1 = new TermQuery(new Term(field1,term1));
		TermQuery query2 = new TermQuery(new Term(field2,term2));

		BooleanQuery bq = new BooleanQuery();
		if(op.equalsIgnoreCase("AND")){
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST);
		}else if(op.equalsIgnoreCase("OR")){ 
			bq.add(query1,BooleanClause.Occur.SHOULD);
			bq.add(query2,BooleanClause.Occur.SHOULD);
		}else if(op.equalsIgnoreCase("NOT")){
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST_NOT);
		}
		
		System.out.println(query1);
		System.out.println(query2);
		System.out.println(bq);
		
		collector = TopScoreDocCollector.create(10,true);
		
		try{
			Directory fsDir = new SimpleFSDirectory(indexDirMessages);
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			IndexSearcher is = new IndexSearcher(ireader);
			is.search(bq, collector);
			
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.err.println("Found " + hits.length + " document(s) that matched query '" + bq + "':");
			
			FoundMessage foundMessageobj = new FoundMessage();
			
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Message id: " + doc.get("message_id"));
				foundMessageobj.setId(doc.get("message_id"));
				
				System.out.println("\t" + "From: " + doc.get("from"));
				foundMessageobj.setFrom(doc.get("from"));
				
				System.out.println("\t" + "To: " + doc.get("to"));
				foundMessageobj.setTo(doc.get("to"));
				
				System.out.println("\t" + "CC: " + doc.get("cc"));
				foundMessageobj.setCc(doc.get("cc"));
				
				System.out.println("\t" + "BCC: " + doc.get("bcc"));
				foundMessageobj.setBcc(doc.get("bcc"));
				
				System.out.println("\t" + "Subject: " + doc.get("subject"));
				foundMessageobj.setSubject(doc.get("subject"));
				
				System.out.println("\t" + "Content: " + doc.get("content"));
				foundMessageobj.setContent(doc.get("content"));
				
				System.out.println("\t" + "Datum: " + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundMessage.add(foundMessageobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		return new ResponseEntity<List<FoundMessage>>(foundMessage, HttpStatus.OK);
	}
	
	//term pretraga
	@PostMapping(value = "/term/message")
	public ResponseEntity<List<FoundMessage>> termSearchMessage(@RequestParam String field1, @RequestParam String term1)  throws Exception {
		
		List<FoundMessage> foundMessage = new ArrayList<FoundMessage>();
		
		File indexDirMessages;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirMessages = new File(rb.getString("indexDirMessages"));
		
		Term t = new Term(field1,term1);
		Query query = new TermQuery(t);
		
		System.out.println(query);
		
		collector = TopScoreDocCollector.create(10,true);
		
		try{
			Directory fsDir = new SimpleFSDirectory(indexDirMessages);
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			IndexSearcher is = new IndexSearcher(ireader);
			is.search(query, collector);
			
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");
			
			FoundMessage foundMessageobj = new FoundMessage();
			
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Message id: " + doc.get("message_id"));
				foundMessageobj.setId(doc.get("message_id"));
				
				System.out.println("\t" + "From: " + doc.get("from"));
				foundMessageobj.setFrom(doc.get("from"));
				
				System.out.println("\t" + "To: " + doc.get("to"));
				foundMessageobj.setTo(doc.get("to"));
				
				System.out.println("\t" + "CC: " + doc.get("cc"));
				foundMessageobj.setCc(doc.get("cc"));
				
				System.out.println("\t" + "BCC: " + doc.get("bcc"));
				foundMessageobj.setBcc(doc.get("bcc"));
				
				System.out.println("\t" + "Subject: " + doc.get("subject"));
				foundMessageobj.setSubject(doc.get("subject"));
				
				System.out.println("\t" + "Content: " + doc.get("content"));
				foundMessageobj.setContent(doc.get("content"));
				
				System.out.println("\t" + "Datum: " + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundMessage.add(foundMessageobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		return new ResponseEntity<List<FoundMessage>>(foundMessage, HttpStatus.OK);
	}
	
	//fuzzy pretraga
	@PostMapping(value = "/fuzzy/message")
	public ResponseEntity<List<FoundMessage>> fuzzySearchMessage(@RequestParam String field, @RequestParam String word) throws Exception {
		
		List<FoundMessage> foundMessage = new ArrayList<FoundMessage>();
		
		File indexDirMessages;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirMessages = new File(rb.getString("indexDirMessages"));
		
		Term t = new Term(field, word);
		int editDis = 2;
		FuzzyQuery query = new FuzzyQuery(t,editDis);
		
		System.out.println(query);
		
		collector = TopScoreDocCollector.create(10,true);
		
		try{
			Directory fsDir = new SimpleFSDirectory(indexDirMessages);
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			IndexSearcher is = new IndexSearcher(ireader);
			is.search(query, collector);
			
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");
			
			FoundMessage foundMessageobj = new FoundMessage();
			
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Message id: " + doc.get("message_id"));
				foundMessageobj.setId(doc.get("message_id"));
				
				System.out.println("\t" + "From: " + doc.get("from"));
				foundMessageobj.setFrom(doc.get("from"));
				
				System.out.println("\t" + "To: " + doc.get("to"));
				foundMessageobj.setTo(doc.get("to"));
				
				System.out.println("\t" + "CC: " + doc.get("cc"));
				foundMessageobj.setCc(doc.get("cc"));
				
				System.out.println("\t" + "BCC: " + doc.get("bcc"));
				foundMessageobj.setBcc(doc.get("bcc"));
				
				System.out.println("\t" + "Subject: " + doc.get("subject"));
				foundMessageobj.setSubject(doc.get("subject"));
				
				System.out.println("\t" + "Content: " + doc.get("content"));
				foundMessageobj.setContent(doc.get("content"));
				
				System.out.println("\t" + "Datum: " + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundMessage.add(foundMessageobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		return new ResponseEntity<List<FoundMessage>>(foundMessage,HttpStatus.OK);
		
	}
	
	//phrase pretraga
	@PostMapping(value = "/phrase/message")
	public ResponseEntity<List<FoundMessage>> phraseSearchMessage(@RequestParam String field1, @RequestParam String term1) throws Exception {
		
		List<FoundMessage> foundMessage = new ArrayList<FoundMessage>();
		
		File indexDirMessages;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirMessages = new File(rb.getString("indexDirMessages"));
		
		PhraseQuery query = new PhraseQuery();
		
		query.setSlop(1);
		
		StringTokenizer st = new StringTokenizer(term1);
		while(st.hasMoreTokens()){
			query.add(new Term(field1,st.nextToken()));
		}
		
		System.out.println(query);
		
		collector = TopScoreDocCollector.create(10,true);
		
		try{
			Directory fsDir = new SimpleFSDirectory(indexDirMessages);
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			IndexSearcher is = new IndexSearcher(ireader);
			is.search(query, collector);
			
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");
			
			FoundMessage foundMessageobj = new FoundMessage();
			
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Message id: " + doc.get("message_id"));
				foundMessageobj.setId(doc.get("message_id"));
				
				System.out.println("\t" + "From: " + doc.get("from"));
				foundMessageobj.setFrom(doc.get("from"));
				
				System.out.println("\t" + "To: " + doc.get("to"));
				foundMessageobj.setTo(doc.get("to"));
				
				System.out.println("\t" + "CC: " + doc.get("cc"));
				foundMessageobj.setCc(doc.get("cc"));
				
				System.out.println("\t" + "BCC: " + doc.get("bcc"));
				foundMessageobj.setBcc(doc.get("bcc"));
				
				System.out.println("\t" + "Subject: " + doc.get("subject"));
				foundMessageobj.setSubject(doc.get("subject"));
				
				System.out.println("\t" + "Content: " + doc.get("content"));
				foundMessageobj.setContent(doc.get("content"));
				
				System.out.println("\t" + "Datum: " + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundMessage.add(foundMessageobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		return new ResponseEntity<List<FoundMessage>>(foundMessage,HttpStatus.OK);
	}
	
}