package ues.projekat.y.search.indexing;

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
import org.apache.tomcat.jni.File;

import ues.projekat.app.model.Message;
import ues.projekat.y.search.misc.IndexableDocumentMessage;
import ues.projekat.y.search.misc.SerbianAnalyzer;

public class IndexerMessage {

	 public static IndexableDocumentMessage prepareDocForTesting(Message message) {
	      IndexableDocumentMessage doc = new IndexableDocumentMessage();
	      doc.setMessageFrom(message.getFrom());  
	      doc.setMessageBcc(message.getBcc());
	      doc.setMessageTo(message.getTo());
	      doc.setContent(message.getContent());
	      doc.setSubject(message.getSubject());
	      doc.setFolder(message.getFolder().getName());
	      doc.setId(message.getId().toString());
	      doc.setUnread(message.isUnread());     
	      
	      return doc;
	  }
	 
	 
	 public static Document createIndexDocument(IndexableDocumentMessage docToAdd)
	   {
	      Document retVal = new Document();
	      IndexableField messageTo = null;
	      IndexableField messageBcc = null;
	      IndexableField messageFrom = new StringField("messageFrom", docToAdd.getMessageFrom(), Store.YES);
	      
	      if(docToAdd.getMessageTo() != null) {
	    	  messageTo = new StringField("messageTo", docToAdd.getMessageTo(), Store.YES);
	    	  retVal.add(messageTo);
	      }
	      if(docToAdd.getMessageBcc() != null) {
	    	   messageBcc = new StringField("messageBcc", docToAdd.getMessageBcc(), Store.YES);
	    	   retVal.add(messageBcc);
	      }
	      
	     
	    
	      IndexableField subject = new TextField("subject", docToAdd.getSubject(), Store.YES);
	      IndexableField content = new TextField("content", docToAdd.getContent(), Store.YES);
	      IndexableField folder = new TextField("folder", docToAdd.getFolder(), Store.YES);
	      IndexableField id = new TextField("id", docToAdd.getId(), Store.YES);
	      IndexableField unread = new TextField("unread",docToAdd.getUnread().toString(),Store.YES);
	  
	      retVal.add(messageFrom);
	      retVal.add(subject);
	      retVal.add(content); 
	      retVal.add(folder);
	      retVal.add(unread);
	      retVal.add(id);
	      return retVal;
	   }
	 
	 
	 public static void indexDocument(Document docToAdd) throws IOException {
		 
	      IndexWriter writer = null;
	      try
	      {
	    	 ResourceBundle rb = ResourceBundle.getBundle("application",Locale.getDefault());
	         Directory indexWriteToDir = FSDirectory.open(new java.io.File(rb.getString("indexDir")));
	         Analyzer analyzer = new SerbianAnalyzer();
	         IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	         writer = new IndexWriter(indexWriteToDir, iwc);
	         writer.addDocument(docToAdd);
	         writer.commit();

	      }finally {
	    	  if (writer != null)
	          {
	             writer.close();
	          }
	      }
	    
	   }
	
}
