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
import org.apache.lucene.search.BooleanClause;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ues.projekat.y.search.misc.CyrillicLatinConverter;
import ues.projekat.y.search.misc.FoundDocumentMessage;
import ues.projekat.y.search.model.ResultDataBoolean;
import ues.projekat.y.search.model.ResultDataFuzzyContact;
import ues.projekat.y.search.model.ResultDataPhrase;

public class SearchMessageController {

	

	@PostMapping("/messages/fuzzy")
	public ResponseEntity<List<FoundDocumentMessage>> searchForDocumentusingFuzzy(
			@RequestBody ResultDataFuzzyContact search) {
		
			List<FoundDocumentMessage> retVal = new ArrayList<FoundDocumentMessage>();
			try {
				ResourceBundle rb = ResourceBundle.getBundle("application",Locale.getDefault());
				Directory dir = FSDirectory.open(new File(rb.getString("indexDir")));
				CyrillicLatinConverter converter = new CyrillicLatinConverter();
				Term t= new Term(search.getTerm1(),converter.cyrilicToLatin(search.getSearchField1().toLowerCase()));
				int editDis=2;
				FuzzyQuery query=new FuzzyQuery(t,editDis);
				DirectoryReader ireader = DirectoryReader.open(dir);
				IndexSearcher is = new IndexSearcher(ireader);
				TopDocs allFound = is.search(query, 100); 
				  if(allFound.scoreDocs != null) {
					for(ScoreDoc doc : allFound.scoreDocs) {		
						System.out.println("Score: " + doc.score);
			           int docidx = doc.doc;
			           Document docRetrieved = is.doc(docidx);
			           if (docRetrieved != null) {
			        	   FoundDocumentMessage docToAdd = new FoundDocumentMessage();
			               IndexableField field = docRetrieved.getField("messageFrom");
			               if(field!=null) {
			            	   docToAdd.setMessageFrom(field.stringValue());
			               }
			               
			               field = docRetrieved.getField("messageBcc");
			               if(field != null) {
			            	   docToAdd.setMessageBcc(field.stringValue());
			               }
			               field = docRetrieved.getField("messageTo");
			               if(field != null) {
			            	   docToAdd.setMessageTo(field.stringValue());
			               }
			               
			               field = docRetrieved.getField("subject");
			               if(field != null) {
			            	   docToAdd.setSubject(field.stringValue());
			               }
			               field = docRetrieved.getField("content");
			               if(field != null) {
			            	   docToAdd.setContent(field.stringValue());
			               }
			               field = docRetrieved.getField("folder");
			               if(field != null) {
			            	   docToAdd.setFolder(field.stringValue());
			               }
			               field = docRetrieved.getField("id");
			               if(field != null) {
			            	   docToAdd.setId(field.stringValue());
			               }
			               field = docRetrieved.getField("unread");
			               if(field != null) {
			            	   docToAdd.setUnread(field.stringValue());
			               }
			               retVal.add(docToAdd);           
			           	}
					}
				} 
				
				 
				
				 
				 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<FoundDocumentMessage>>(retVal,HttpStatus.OK);
	}
	
	
	@PostMapping("/messages/boolean")
	public ResponseEntity<List<FoundDocumentMessage>> searchForDocumentusingBoolean(
			@RequestBody ResultDataBoolean result) {
		
		List<FoundDocumentMessage> retVal = new ArrayList<FoundDocumentMessage>();
		try {
			 ResourceBundle rb = ResourceBundle.getBundle("application",Locale.getDefault());
			 Directory dirOfIndexes = FSDirectory.open(new File(rb.getString("indexDir")));
			 CyrillicLatinConverter converter = new CyrillicLatinConverter();
			 TermQuery termQuery1 = new TermQuery(new Term(result.getTerm1(), converter.cyrilicToLatin(result.getSearchField1().toLowerCase())));
			 TermQuery termQuery2 = new TermQuery(new Term(result.getTerm2(), converter.cyrilicToLatin(result.getSearchField2().toLowerCase())));
			 System.out.println("Koliko je termQuery1: " + termQuery1);
			 System.out.println("Koliko je termQuery2: " + termQuery2);
			 
			 BooleanQuery bq=new BooleanQuery();
				if(result.getOperation().equalsIgnoreCase("AND")){
					bq.add(termQuery1,BooleanClause.Occur.MUST);
					bq.add(termQuery2,BooleanClause.Occur.MUST);
				}else if(result.getOperation().equalsIgnoreCase("OR")){
					bq.add(termQuery1,BooleanClause.Occur.SHOULD);
					bq.add(termQuery2,BooleanClause.Occur.SHOULD);
				}
				
				DirectoryReader ireader = DirectoryReader.open(dirOfIndexes);
				IndexSearcher is = new IndexSearcher(ireader);
				TopDocs allFound = is.search(bq, 100);
				
				System.out.println("Sta je allFound " + allFound.totalHits);
				
				if(allFound.scoreDocs != null) {
					for(ScoreDoc doc : allFound.scoreDocs) {		
						System.out.println("Score: " + doc.score);
			               
			           int docidx = doc.doc;
			           Document docRetrieved = is.doc(docidx);
			           
			           if (docRetrieved != null) {
			        	   FoundDocumentMessage docToAdd = new FoundDocumentMessage();
			               IndexableField field = docRetrieved.getField("messageFrom");
			               if(field!=null) {
			            	   docToAdd.setMessageFrom(field.stringValue());
			               }
			               field = docRetrieved.getField("subject");
			               if(field != null) {
			            	   docToAdd.setSubject(field.stringValue());
			               }
			               field = docRetrieved.getField("content");
			               if(field != null) {
			            	   docToAdd.setContent(field.stringValue());
			               }
			               field = docRetrieved.getField("folder");
			               if(field != null) {
			            	   docToAdd.setFolder(field.stringValue());
			               }
			               field = docRetrieved.getField("id");
			               if(field != null) {
			            	   docToAdd.setId(field.stringValue());
			               }
			               field = docRetrieved.getField("unread");
			               if(field != null) {
			            	   docToAdd.setUnread(field.stringValue());
			               }
			             
			               retVal.add(docToAdd);
			                  
			           	}
					}
				}
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return new ResponseEntity<List<FoundDocumentMessage>>(retVal,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/messages/phrase")
	public ResponseEntity<List<FoundDocumentMessage>> searchDocumentusingPhrase(
			@RequestBody ResultDataPhrase search) {
			List<FoundDocumentMessage> retVal = new ArrayList<FoundDocumentMessage>();
			try {
				 ResourceBundle rb = ResourceBundle.getBundle("application",Locale.getDefault());
				 Directory dirOfIndexes = FSDirectory.open(new File(rb.getString("indexDir")));
				 CyrillicLatinConverter converter = new CyrillicLatinConverter();
				 PhraseQuery query=new PhraseQuery();
				 query.setSlop(1);
				 StringTokenizer st = new StringTokenizer(converter.cyrilicToLatin(search.getSearchField1().toLowerCase()));
				 while(st.hasMoreTokens()){
					query.add(new Term(search.getTerm1(),st.nextToken()));
				}
				 System.out.println("God damn,what is term1 " + search.getTerm1());
				 System.out.println("God damn,what is searchField1 " + search.getSearchField1());
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
			        	   FoundDocumentMessage docToAdd = new FoundDocumentMessage();
			               IndexableField field = docRetrieved.getField("messageFrom");
			               if(field!=null) {
			            	   docToAdd.setMessageFrom(field.stringValue());
			               }
			               field = docRetrieved.getField("subject");
			               if(field != null) {
			            	   docToAdd.setSubject(field.stringValue());
			               }
			               field = docRetrieved.getField("content");
			               if(field != null) {
			            	   docToAdd.setContent(field.stringValue());
			               }
			               field = docRetrieved.getField("folder");
			               if(field != null) {
			            	   docToAdd.setFolder(field.stringValue());
			               }
			               field = docRetrieved.getField("accounts");
			               if(field != null) {
			            	   docToAdd.setAccounts(field.stringValue());
			               }
			               field = docRetrieved.getField("id");
			               if(field != null) {
			            	   docToAdd.setId(field.stringValue());
			               }
			               field = docRetrieved.getField("unread");
			               if(field != null) {
			            	   docToAdd.setUnread(field.stringValue());
			               }
			            
			              
			              retVal.add(docToAdd);
			                 
			           	}
					}
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<FoundDocumentMessage>>(retVal,HttpStatus.OK);
	}

}
