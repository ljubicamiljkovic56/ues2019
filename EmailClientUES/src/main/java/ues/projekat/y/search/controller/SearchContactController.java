package ues.projekat.y.search.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.y.search.misc.CyrillicLatinConverter;
import ues.projekat.y.search.misc.FoundDocumentContact;
import ues.projekat.y.search.model.ResultDataBoolean;
import ues.projekat.y.search.model.ResultDataFuzzyContact;
import ues.projekat.y.search.model.ResultDataPhrase;
import org.apache.lucene.search.BooleanClause;



@RestController
@RequestMapping(value = "api/searchcontacts")
@CrossOrigin
public class SearchContactController {
	
	@PostMapping("/contacts/fuzzy")
	public ResponseEntity<List<FoundDocumentContact>> searchforDocumentusingFuzzy(
			@RequestBody ResultDataFuzzyContact search) {
		List<FoundDocumentContact> retVal = new ArrayList<FoundDocumentContact>();
		try {
			 ResourceBundle rb = ResourceBundle.getBundle("application",Locale.getDefault());
			 Directory dirOfIndexes = FSDirectory.open(new File(rb.getString("dataDir"))); 
			 CyrillicLatinConverter converter = new CyrillicLatinConverter();
			 Term t= new Term(search.getTerm1(),converter.cyrilicToLatin(search.getSearchField1().toLowerCase()));
			 int editDis=2;
			 FuzzyQuery query=new FuzzyQuery(t,editDis);
			 DirectoryReader ireader = DirectoryReader.open(dirOfIndexes);
			 IndexSearcher is = new IndexSearcher(ireader);
			 TopDocs allFound = is.search(query, 100);
			 if(allFound.scoreDocs != null) {
				 for(ScoreDoc doc : allFound.scoreDocs) {
					 int docidx = doc.doc;
			         Document docRetrieved = is.doc(docidx);
			         if(docRetrieved != null) {
			        	 FoundDocumentContact docToAdd = new FoundDocumentContact();
			        	 IndexableField field = docRetrieved.getField("firstName");
			               if(field!=null) {
			            	   docToAdd.setFirstName(field.stringValue());
			               }
			               field = docRetrieved.getField("lastName");
			               if(field != null) {
			            	   docToAdd.setLastName(field.stringValue());
			               }
			               field = docRetrieved.getField("note");
			               if(field != null) {
			            	   docToAdd.setNote(field.stringValue());
			               }
			               field = docRetrieved.getField("email"); 
			               if(field != null) {
			            	   docToAdd.setEmail(field.stringValue());
			               }
			               
			               //if (docToAdd.validate())
			                // {
			                     retVal.add(docToAdd);
			                 //}
			               
			         }
			           
				 }
			 }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<FoundDocumentContact>>(retVal, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/contacts/boolean")
	public ResponseEntity<List<FoundDocumentContact>> searchForDocumentusingBoolean(
			@RequestBody ResultDataBoolean result)  {
		
		List<FoundDocumentContact> retVal = new ArrayList<FoundDocumentContact>();
		try {
				 ResourceBundle rb = ResourceBundle.getBundle("application",Locale.getDefault());
				 Directory dirOfIndexes = FSDirectory.open(new File(rb.getString("dataDir")));
				 CyrillicLatinConverter converter = new CyrillicLatinConverter();
				 Term t1 = new Term(result.getTerm1(),converter.cyrilicToLatin(result.getSearchField1()));
				 Term t2 = new Term(result.getTerm2(),converter.cyrilicToLatin(result.getSearchField2()));
				 TermQuery termQuery1 = new TermQuery(t1);
				 TermQuery termQuery2 = new TermQuery(t2);
				 System.out.println("Koliko je termQuery1: " + termQuery1);
				 System.out.println("Koliko je termQuery2: " + termQuery2);
				 
				 BooleanQuery bq = new BooleanQuery();
					if(result.getOperation().equalsIgnoreCase("AND")){
						bq.add(termQuery1, BooleanClause.Occur.MUST);
						bq.add(termQuery2, BooleanClause.Occur.MUST);
					}else if(result.getOperation().equalsIgnoreCase("OR")){
						bq.add(termQuery1, BooleanClause.Occur.SHOULD);
						bq.add(termQuery2, BooleanClause.Occur.SHOULD);
					}
					
					DirectoryReader ireader = DirectoryReader.open(dirOfIndexes);
					IndexSearcher is = new IndexSearcher(ireader);
					TopDocs allFound = is.search(bq, 100);
					
					System.out.println("Sta je allFound " + allFound.totalHits);
				
					System.out.println("Sta je allFound " + allFound.totalHits);
					if(allFound.scoreDocs != null) {
						for(ScoreDoc doc : allFound.scoreDocs) {		
						System.out.println("Score: " + doc.score);
			               
				           int docidx = doc.doc;
				           Document docRetrieved = is.doc(docidx); 
				           if (docRetrieved != null) {
				        	   System.out.println("Is it null ");
				        	   FoundDocumentContact docToAdd = new FoundDocumentContact();
				               IndexableField field = docRetrieved.getField("firstName");
				               if(field!=null) {
				            	   docToAdd.setFirstName(field.stringValue());
				               }
				               field = docRetrieved.getField("lastName");
				               if(field != null) {
				            	   docToAdd.setLastName(field.stringValue());
				               }
				               field = docRetrieved.getField("note");
				               if(field != null) {
				            	   docToAdd.setNote(field.stringValue());
				               }
				               
				               field = docRetrieved.getField("email");
				               if(field != null) {
				            	   docToAdd.setEmail(field.stringValue());
				               }
				               
				             // (docToAdd.validate())
				                 
				                retVal.add(docToAdd);
				                    
				           	}
					}
				}
			 
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<FoundDocumentContact>>(retVal,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/contacts/phrase")
	public ResponseEntity<List<FoundDocumentContact>> searchDocumentusingPhrase(
			@RequestBody ResultDataPhrase search) {
		
		List<FoundDocumentContact> retVal = new ArrayList<FoundDocumentContact>();
		try {
			 ResourceBundle rb = ResourceBundle.getBundle("application",Locale.getDefault());
			 Directory dirOfIndexes = FSDirectory.open(new File(rb.getString("dataDir")));
			 CyrillicLatinConverter converter = new CyrillicLatinConverter();
			 PhraseQuery query=new PhraseQuery();
			 query.setSlop(1);
			 StringTokenizer st = new StringTokenizer(converter.cyrilicToLatin(search.getSearchField1()));
			 while(st.hasMoreTokens()){
				query.add(new Term(search.getTerm1(),st.nextToken()));
			}
			 DirectoryReader ireader = DirectoryReader.open(dirOfIndexes);
			 IndexSearcher is = new IndexSearcher(ireader);
			 TopDocs allFound = is.search(query, 100);
				
			 System.out.println("Sta je allFound " + allFound.totalHits);
			 if(allFound.scoreDocs != null) {
					for(ScoreDoc doc : allFound.scoreDocs) {		
						System.out.println("Score: " + doc.score);
			           int docidx = doc.doc;
			           Document docRetrieved = is.doc(docidx);
			           if (docRetrieved != null) {
			        	   FoundDocumentContact docToAdd = new FoundDocumentContact();
			               IndexableField field = docRetrieved.getField("firstName");
			               if(field!=null) {
			            	   docToAdd.setFirstName(field.stringValue());
			               }
			               field = docRetrieved.getField("lastName");
			               if(field != null) {
			            	   docToAdd.setLastName(field.stringValue());
			               }
			               field = docRetrieved.getField("note");
			               if(field != null) {
			            	   docToAdd.setNote(field.stringValue());
			               }
			               field = docRetrieved.getField("email"); 
			               if(field != null) {
			            	   docToAdd.setEmail(field.stringValue());
			               }
			               
			             //  if (docToAdd.validate())
			              //    {
			                     retVal.add(docToAdd);
			                //  }
			           	}
					}
				}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<FoundDocumentContact>>(retVal,HttpStatus.OK);
	}
	

}
