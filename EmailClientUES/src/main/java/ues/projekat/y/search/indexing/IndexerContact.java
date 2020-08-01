package ues.projekat.y.search.indexing;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import ues.projekat.app.model.Contact;
import ues.projekat.y.search.misc.IndexableDocumentContact;
import ues.projekat.y.search.misc.SerbianAnalyzer;

public class IndexerContact {
	public static IndexableDocumentContact prepareDocForTesting(Contact contact) {
		IndexableDocumentContact indexDoc = new IndexableDocumentContact();
		indexDoc.setFirstName(contact.getFirstname());
		indexDoc.setLastName(contact.getLastname());
		indexDoc.setNote(contact.getNote());
		indexDoc.setEmail(contact.getEmail());
		return indexDoc;
	}
	
	public static Document createIndexDocument(IndexableDocumentContact docToAdd) {
		 Document retVal = new Document();
	     IndexableField firstName = new StringField("firstname", docToAdd.getFirstName(), Store.YES); 
	     IndexableField lastName = new StringField("lastname", docToAdd.getLastName(), Store.YES);
	     IndexableField note = new StringField("note", docToAdd.getNote(), Store.YES);
	     IndexableField email = new StringField("email", docToAdd.getEmail(), Store.YES);
	     retVal.add(firstName);
	     retVal.add(lastName);
	     retVal.add(note);   
	     retVal.add(email);
	     return retVal;
	}
	
	 public static void indexDocument(Document docToAdd) throws IOException  {
		 
	      IndexWriter writer = null;
	      try
	      {
	    	 ResourceBundle rb = ResourceBundle.getBundle("application",Locale.getDefault());
	         Directory indexWriteToDir = FSDirectory.open(new File(rb.getString("dataDir")));
	         Analyzer analyzer = new SerbianAnalyzer();
	         IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	         iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
	         writer = new IndexWriter(indexWriteToDir, iwc);
	         writer.addDocument(docToAdd);
	         writer.commit();
	         
	      }
	      finally
	      {
	         if (writer != null)
	         {
	            writer.close();
	         }
	      }
	   }

}
