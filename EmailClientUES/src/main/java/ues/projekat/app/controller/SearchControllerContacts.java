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
import org.apache.lucene.queryparser.classic.ParseException;
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
import ues.projekat.y.search.model.FoundContact;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchControllerContacts {
	
	private TopScoreDocCollector collector;
	
	//obicna pretraga (ime polja: rec)
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/regular/contact")
	public ResponseEntity<List<FoundContact>> regularSearchContact(@RequestParam String regularSearch) throws Exception{
		
		List<FoundContact> foundContact = new ArrayList<FoundContact>();
		
		File indexDir;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDir = new File(rb.getString("indexDir"));

		QueryParser qp = new QueryParser(Version.LUCENE_40,"sadrzaj_fajla", new SerbianAnalyzer());
		
		Query query = qp.parse(regularSearch);
		
		System.out.println(query);
		
		collector = TopScoreDocCollector.create(10,true);
		
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
			
			FoundContact foundContactobj = new FoundContact(); 
			
			//prikaz svih prondajenih podataka
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Contact id: " + doc.get("contact_id"));
				foundContactobj.setId(doc.get("contact_id"));
				
				System.out.println("\t" + "Displayname: " + doc.get("displayname"));
				foundContactobj.setDisplayname(doc.get("displayname"));
				
				System.out.println("\t" + "Email: " + doc.get("email"));
				foundContactobj.setEmail(doc.get("email"));
				
				System.out.println("\t" + "Firstname: " + doc.get("firstname"));
				foundContactobj.setFirstname(doc.get("firstname"));
				
				System.out.println("\t" + "Lastname: " + doc.get("lastname"));
				foundContactobj.setLastname(doc.get("lastname"));
				
				System.out.println("\t" + "Note: " + doc.get("note"));
				foundContactobj.setNote(doc.get("note"));
				
				System.out.println("\t" + "Datum:" + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundContact.add(foundContactobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	
		return new ResponseEntity<List<FoundContact>>(foundContact,HttpStatus.OK);
	}
	
	//boolean pretraga (and, or, not)
	@PostMapping(value = "/boolean/contact")
	public ResponseEntity<List<FoundContact>> booleanSearchContact(@RequestParam String field1, @RequestParam String term1, 
			@RequestParam String field2, @RequestParam String term2, @RequestParam String op) throws IOException, ParseException {
		
		List<FoundContact> foundContact = new ArrayList<FoundContact>();
		
		File indexDir;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDir = new File(rb.getString("indexDir"));
		
		//CyrillicLatinConverter converter = new CyrillicLatinConverter();
		
		
		TermQuery query1 = new TermQuery(new Term(field1,term1));
		TermQuery query2 =  new TermQuery(new Term(field2,term2));
		
		BooleanQuery bq = new BooleanQuery();
		if(op.equalsIgnoreCase("AND")){
			//mora da sadrzi oba termina
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST);
		}else if(op.equalsIgnoreCase("OR")){
			//mora da sadrzi jedan od dva termina 
			bq.add(query1,BooleanClause.Occur.SHOULD);
			bq.add(query2,BooleanClause.Occur.SHOULD);
		}else if(op.equalsIgnoreCase("NOT")){
			//prvi termin sadrzi, ali ne sadrzi drugi termin
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST_NOT);
		}
		
		
		System.out.println(query1);
		System.out.println(query2);
		System.out.println(bq);
		
		collector = TopScoreDocCollector.create(10,true);
		
		try{
			//kreiramo novi direktorijum
			Directory fsDir = new SimpleFSDirectory(indexDir);
			//citamo indekse iz direktorijuma
			DirectoryReader ireader = DirectoryReader.open(fsDir);
			//IndexSearcher radi search nad indexReaderom
			IndexSearcher is = new IndexSearcher(ireader);
			//search nad zadatim upitom i kolektorom
			is.search(bq, collector);
			
			
			//lista pogodaka
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			//prikaz broja pronadjenih podataka
			System.err.println("Found " + hits.length + " document(s) that matched query '" + bq + "':");
			
			FoundContact foundContactobj = new FoundContact(); 
			
			//prikaz svih prondajenih podataka
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				
				System.out.println("\t" + "Contact id: " + doc.get("contact_id"));
				foundContactobj.setId(doc.get("contact_id"));
				
				System.out.println("\t" + "Displayname: " + doc.get("displayname"));
				foundContactobj.setDisplayname(doc.get("displayname"));
				
				System.out.println("\t" + "Email: " + doc.get("email"));
				foundContactobj.setEmail(doc.get("email"));
				
				System.out.println("\t" + "Firstname: " + doc.get("firstname"));
				foundContactobj.setFirstname(doc.get("firstname"));
				
				System.out.println("\t" + "Lastname: " + doc.get("lastname"));
				foundContactobj.setLastname(doc.get("lastname"));
				
				System.out.println("\t" + "Note: " + doc.get("note"));
				foundContactobj.setNote(doc.get("note"));
				
				System.out.println("\t" + "Datum:" + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundContact.add(foundContactobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	
		
		return new ResponseEntity<List<FoundContact>>(foundContact, HttpStatus.OK);
	}
	
	//term pretraga
	@PostMapping(value = "/term/contact")
	public ResponseEntity<List<FoundContact>> termSearchContact(@RequestParam String field1, @RequestParam String term1) throws Exception{
		
		List<FoundContact> foundContact = new ArrayList<FoundContact>();
		
		File indexDir;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDir = new File(rb.getString("indexDir"));
		
		Term t = new Term(field1, term1);
		Query query = new TermQuery(t);
		
		System.out.println(query);
		
		collector = TopScoreDocCollector.create(10,true);
		
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
			
			FoundContact foundContactobj = new FoundContact(); 
			
			//prikaz svih prondajenih podataka
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Contact id: " + doc.get("contact_id"));
				foundContactobj.setId(doc.get("contact_id"));
				
				System.out.println("\t" + "Displayname: " + doc.get("displayname"));
				foundContactobj.setDisplayname(doc.get("displayname"));
				
				System.out.println("\t" + "Email: " + doc.get("email"));
				foundContactobj.setEmail(doc.get("email"));
				
				System.out.println("\t" + "Firstname: " + doc.get("firstname"));
				foundContactobj.setFirstname(doc.get("firstname"));
				
				System.out.println("\t" + "Lastname: " + doc.get("lastname"));
				foundContactobj.setLastname(doc.get("lastname"));
				
				System.out.println("\t" + "Note: " + doc.get("note"));
				foundContactobj.setNote(doc.get("note"));
				
				System.out.println("\t" + "Datum:" + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundContact.add(foundContactobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}

		
		return new ResponseEntity<List<FoundContact>>(foundContact,HttpStatus.OK);
	}
	
	//fuzzy pretraga
	@PostMapping(value = "/fuzzy/contact")
	public ResponseEntity<List<FoundContact>> fuzzySearchContact(@RequestParam String field, @RequestParam String word) throws Exception {
		
		List<FoundContact> foundContact = new ArrayList<FoundContact>();
		
		File indexDir;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDir = new File(rb.getString("indexDir"));
		
		Term t = new Term(field, word);
		
		int editDis = 2;
		
		FuzzyQuery query = new FuzzyQuery(t,editDis);
		
		System.out.println(query);
		
		collector = TopScoreDocCollector.create(10,true);
		
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
			
			FoundContact foundContactobj = new FoundContact(); 
			
			//prikaz svih prondajenih podataka
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Contact id: " + doc.get("contact_id"));
				foundContactobj.setId(doc.get("contact_id"));
				
				System.out.println("\t" + "Displayname: " + doc.get("displayname"));
				foundContactobj.setDisplayname(doc.get("displayname"));
				
				System.out.println("\t" + "Email: " + doc.get("email"));
				foundContactobj.setEmail(doc.get("email"));
				
				System.out.println("\t" + "Firstname: " + doc.get("firstname"));
				foundContactobj.setFirstname(doc.get("firstname"));
				
				System.out.println("\t" + "Lastname: " + doc.get("lastname"));
				foundContactobj.setLastname(doc.get("lastname"));
				
				System.out.println("\t" + "Note: " + doc.get("note"));
				foundContactobj.setNote(doc.get("note"));
				
				System.out.println("\t" + "Datum:" + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundContact.add(foundContactobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		
		return new ResponseEntity<List<FoundContact>>(foundContact, HttpStatus.OK);
	}
	
	//phrase pretraga
	@PostMapping(value = "/phrase/contact")
	public ResponseEntity<List<FoundContact>> phraseSearchContact(@RequestParam String field1, @RequestParam String term1) throws Exception{
	
		List<FoundContact> foundContact = new ArrayList<FoundContact>();
		
		File indexDir;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDir = new File(rb.getString("indexDir"));
		

		PhraseQuery query = new PhraseQuery();
				
		query.setSlop(1);
				
		StringTokenizer st = new StringTokenizer(term1);
			while(st.hasMoreTokens()){
				query.add(new Term(field1,st.nextToken()));
		}
				
		
		collector = TopScoreDocCollector.create(10,true);
		
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
			
			FoundContact foundContactobj = new FoundContact(); 
			
			//prikaz svih prondajenih podataka
			for (int i = 0; i < collector.getTotalHits(); i++) {
				int docId = hits[i].doc;
				Document doc = is.doc(docId);
				System.out.println("\t" + "Contact id: " + doc.get("contact_id"));
				foundContactobj.setId(doc.get("contact_id"));
				
				System.out.println("\t" + "Displayname: " + doc.get("displayname"));
				foundContactobj.setDisplayname(doc.get("displayname"));
				
				System.out.println("\t" + "Email: " + doc.get("email"));
				foundContactobj.setEmail(doc.get("email"));
				
				System.out.println("\t" + "Firstname: " + doc.get("firstname"));
				foundContactobj.setFirstname(doc.get("firstname"));
				
				System.out.println("\t" + "Lastname: " + doc.get("lastname"));
				foundContactobj.setLastname(doc.get("lastname"));
				
				System.out.println("\t" + "Note: " + doc.get("note"));
				foundContactobj.setNote(doc.get("note"));
				
				System.out.println("\t" + "Datum:" + " (" + doc.get("filedate") + ")");
				System.out.println("\t" + doc.get("filename") + "\n");
				foundContact.add(foundContactobj);
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	
		return new ResponseEntity<List<FoundContact>>(foundContact,HttpStatus.OK);
	}
}