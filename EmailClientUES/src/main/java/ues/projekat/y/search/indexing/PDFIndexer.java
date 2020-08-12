package ues.projekat.y.search.indexing;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.pdfbox.lucene.LucenePDFDocument;

import ues.projekat.y.search.misc.SerbianAnalyzer;


public class PDFIndexer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Directory indexDir;
		File dataDir;
		
		try{
			if (args.length != 2) {
				ResourceBundle rb=ResourceBundle.getBundle("lucene.test.luceneindex");
				indexDir=new SimpleFSDirectory(new File(rb.getString("indexDir")));
				dataDir=new File(rb.getString("dataDir"));
			}else{
				indexDir = new SimpleFSDirectory(new File(args[0]));
				dataDir = new File(args[1]);
			}
			long start = new Date().getTime();
			int numIndexed = index(indexDir, dataDir);
			long end = new Date().getTime();
			System.out.println("Indexing " + numIndexed + " files took "+ (end - start) + " milliseconds");
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println("Problem u pristupu direktorijumima");
		}
	}
	
	// open an index and start file directory traversal
	
	public static int index(Directory indexDir, File dataDir) throws IOException {
		if (!dataDir.exists() || !dataDir.isDirectory()) {
			throw new IOException(dataDir + " does not exist or is not a directory");
		}
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, new SerbianAnalyzer());
		iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
		IndexWriter writer = new IndexWriter(indexDir, iwc);
		indexDirectory(writer, dataDir);
		int numIndexed = writer.numDocs();
		writer.close();
		return numIndexed;
	}
	// recursive method that calls itself when it finds a directory
	private static void indexDirectory(IndexWriter writer, File dir) throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(writer, f);
			} else if (f.getName().endsWith(".pdf")) {
				indexFile(writer, f);
			}
		}
	}
	
	public static void indexFile(IndexWriter writer, File f)throws IOException {
		if (f.isHidden() || !f.exists() || !f.canRead()) {
			return;
		}
		System.out.println("Indexing PDF file: " + f.getCanonicalPath());
		LucenePDFDocument lpdf = new LucenePDFDocument();
		Document doc = lpdf.convertDocument(f);
		writer.addDocument(doc);
		
		//pozvati PDFHandler  koji sami pravimo
	}
}