//package ues.projekat.search.indexing;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import org.apache.lucene.document.Document;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.index.IndexableField;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.search.TopScoreDocCollector;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.SimpleFSDirectory;
//
//import ues.projekat.search.analyzers.SerbianAnalyzer;
//import ues.projekat.search.handlers.DocumentHandler;
//import ues.projekat.search.handlers.PDFHandler;
//import ues.projekat.search.handlers.TextDocHandler;
//
//public class Indexer {
//
//	private File indexDirPath;
//	private IndexWriter indexWriter;
//	private Directory indexDir;
//	
//	private static Indexer indexer = new Indexer(true);
//	
//	public static Indexer getInstance(){
//		return indexer;
//	}
//	
//
//	private Indexer(String path, boolean restart) {
//		System.out.println("PATH: " + path);
//		IndexWriterConfig iwc = new IndexWriterConfig(new SerbianAnalyzer());
//		if(restart){
//			iwc.setOpenMode(OpenMode.CREATE);
//		}else{
//			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
//		}
//		
//		try{
//			this.indexDir = new SimpleFSDirectory(FileSystems.getDefault().getPath(path));
//			this.indexWriter = new IndexWriter(this.indexDir, iwc);
//		}catch(IOException ioe){
//			throw new IllegalArgumentException("Path not correct");
//		}
//		
//	}
//	
//	private Indexer(boolean restart){
//		this(ResourceBundle.getBundle("application").getString("index"), restart);
//	}
//	
//	private Indexer(){
//		this(false);
//	}
//	
//	public IndexWriter getIndexWriter(){
//		return this.indexWriter;
//	}
//	
//	public Directory getIndexDir(){
//		return indexDir;
//	}
//	
//	public File getIndexDirPath(){
//		return indexDirPath;
//	}
//	
//	
//	public boolean delete(String filename){
//		Term delTerm = new Term("filename", filename);
//		try {
//			synchronized (this) {
//				this.indexWriter.deleteDocuments(delTerm);
//				this.indexWriter.commit();
//			}
//			return true;
//		} catch (IOException e) {
//			return false;
//		}
//	}
//	
//	public boolean add(Document doc){
//		try {
//			synchronized (this) {
//				this.indexWriter.addDocument(doc);
//				this.indexWriter.commit();
//			}
//			return true;
//		} catch (IOException e) {
//			return false;
//		}
//		
//	}
//	
//	public boolean updateDocument(String filename, List<IndexableField> fields){		
//		try {
//			DirectoryReader reader = DirectoryReader.open(this.indexDir);
//			IndexSearcher is = new IndexSearcher(reader);
//			Query query = new TermQuery(new Term("filename", filename));
//			TopScoreDocCollector collector = TopScoreDocCollector.create(10, 10	);
//			is.search(query, collector);
//			
//			ScoreDoc[] scoreDocs = collector.topDocs().scoreDocs;
//			if(scoreDocs.length > 0){
//				int docID = scoreDocs[0].doc;
//				Document doc = is.doc(docID);
//				if(doc != null){
//					for(IndexableField field : fields){
//						doc.removeFields(field.name());
//					}
//					for(IndexableField field : fields){
//						doc.add(field);
//					}
//					try{
//						synchronized (this) {
//							this.indexWriter.updateDocument(new Term("filename", filename), doc);
//							this.indexWriter.commit();
//							return true;
//						}
//					}catch(IOException e){
//					}
//				}
//			}
//			
//			return false;
//			
//		} catch (IOException e) {
//			throw new IllegalArgumentException("Indeksni direktorijum nije u redu");
//		} 
//	}
//	
//	public int index(File file){		
//		DocumentHandler handler = null;
//		String fileName = null;
//		try {
//			File[] files;
//			if(file.isDirectory()){
//				files = file.listFiles();
//			}else{
//				files = new File[1];
//				files[0] = file;
//			}
//			for(File newFile : files){
//				if(newFile.isFile()){
//					fileName = newFile.getName();
//					handler = getHandler(fileName);
//					if(handler == null){
//						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
//						continue;
//					}
//					this.indexWriter.addDocument(handler.getIndexUnit(newFile).getLuceneDocument());
//				} else if (newFile.isDirectory()){
//					index(newFile);
//				}
//			}
//			this.indexWriter.commit();
//			System.out.println("indexing done");
//		} catch (IOException e) {
//			System.out.println("indexing NOT done");
//		}
//		return this.indexWriter.numRamDocs();
//	}
//	
//	protected void finalize() throws Throwable {
//		this.indexWriter.close();
//	}
//	
//	public DocumentHandler getHandler(String fileName){
//		if(fileName.endsWith(".txt")){
//			return new TextDocHandler();
//		}else if(fileName.endsWith(".pdf")){
//			return new PDFHandler();
//		}else{
//			return null;
//		}
//	}
//
//
//}
