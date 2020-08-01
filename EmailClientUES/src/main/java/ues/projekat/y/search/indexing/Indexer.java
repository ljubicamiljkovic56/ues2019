package ues.projekat.y.search.indexing;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import ues.projekat.app.model.Message;
import ues.projekat.y.search.misc.IndexableDocumentMessage;
import ues.projekat.y.search.misc.SerbianAnalyzer;

public class Indexer {
	
	private Directory indexDir;
	
	public static IndexableDocumentMessage index(Message message) {
		 IndexableDocumentMessage doc = new IndexableDocumentMessage();
	     System.out.println("Message.getContent()" + message.getContent());
	     System.out.println("message.getSubject()" + message.getSubject());
	     System.out.println("message.getBcc()" + message.getBcc());
	      
	      doc.setMessageFrom(message.getFrom());  
	      doc.setMessageBcc(message.getBcc());
	      doc.setMessageTo(message.getTo());
	      doc.setContent(message.getContent());
	      doc.setSubject(message.getSubject());
		
	      return doc;
	}
	
	
	 public Document createIndexDocument(IndexableDocumentMessage docToAdd)
	   {
	      Document retVal = new Document();
	      IndexableField messageFrom = new StringField("messageFrom", docToAdd.getMessageFrom(), Store.YES); 
	      IndexableField messageTo = new StringField("messageTo", docToAdd.getMessageTo(), Store.YES);
	      IndexableField messageBcc = new StringField("messageBcc", docToAdd.getMessageBcc(), Store.YES); 
	      IndexableField subject = new TextField("subject", docToAdd.getSubject(), Store.YES);
	      IndexableField content = new TextField("content", docToAdd.getContent(), Store.YES);
	      retVal.add(messageFrom);
	      retVal.add(messageTo);
	      retVal.add(messageBcc);
	      retVal.add(subject);
	      retVal.add(content);   
	      return retVal;
	   }
	 
	 
	 public void indexDocument(Document docToAdd) throws IOException {
		 
	      IndexWriter writer = null;
	      try
	      {
	    	 ResourceBundle rb = ResourceBundle.getBundle("application", Locale.getDefault());
	         Directory indexWriteToDir = FSDirectory.open(new File(rb.getString("indexDir")));
	         Analyzer analyzer = new SerbianAnalyzer();
	         IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	        // iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
	         writer = new IndexWriter(indexWriteToDir, iwc);
	         //writer.deleteAll();
	         writer.addDocument(docToAdd);
	         writer.commit();
	        // writer.close();
	      }finally {
	    	  if (writer != null)
	          {
	             writer.close();
	          }
	      }
	    
	   }

}
