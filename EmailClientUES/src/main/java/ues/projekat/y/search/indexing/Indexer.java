package ues.projekat.y.search.indexing;

import java.io.File;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Date;
import java.util.ResourceBundle;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
public class Indexer {
	
	//main metoda odakle se poziva index metoda
	public static void main(String[] args) throws Exception {
		//indexDir folder gde se smestaju indeksi
		Directory indexDir;
		//fajl dataDir koji treba da se indeksira
		File dataDir;
		if (args.length != 2) {
			try{
				//odavde cita iz luceneindex fajla indexDir i dataDir lokacije i radi store text
				ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
				//new File indexDir
				indexDir = new SimpleFSDirectory(new File(rb.getString("indexDir")));
				//new File dataDir
				dataDir = new File(rb.getString("dataDir"));
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				//izbaci exception ako nismo naveli kako treba u ResourceBundle
				throw new Exception("Usage: java " + Indexer.class.getName()+ " <index dir> <data dir> or properties file needed");
			}
		}else{
			indexDir = new SimpleFSDirectory(new File(args[0]));
			dataDir = new File(args[1]);
		}
		//pocetak indeksiranja
		long start = new Date().getTime();
		//broji koliko je fajlova indeksirao
		int numIndexed = index(indexDir, dataDir);
		//kraj indeksiranja
		long end = new Date().getTime();
		//ispis sta je indeksirao i koliko mu je trebalo
		System.out.println("Indexing " + numIndexed + " files took "
		+ (end - start) + " milliseconds");
	}
	
	// open an index and start file directory traversal
	
	@SuppressWarnings("deprecation")
	//metoda koja radi indeksiranje, uzima indexDir i dataDir kao parametre
	public static int index(Directory indexDir, File dataDir) throws IOException {
		if (!dataDir.exists() || !dataDir.isDirectory()) {
			//izbaci ovaj exception ako ne postoji directory
			throw new IOException(dataDir + " does not exist or is not a directory");
		}
		//IndexWriterConfig objekat kreiramo, i postavimo verziju lucene i analyzer
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, new ues.projekat.y.search.misc.SerbianAnalyzer()); // pozvati 
		//postavljamo openMode na CREATE_OR_APPEND (ako ne postoji index, kreira ga, ako postoji, koristi ga)
		iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
		//IndexWriter kreira i upravlja indexom
		IndexWriter writer = new IndexWriter(indexDir, iwc);
		indexDirectory(writer, dataDir);
		int numIndexed = writer.numDocs();
		writer.close();
		return numIndexed;
	}
	// recursive method that calls itself when it finds a directory
	private static void indexDirectory(IndexWriter writer, File dir) throws IOException {
		//prolazi kroz listu fajlova
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				//pozivamo indexDirectory
				indexDirectory(writer, f);
			} else if (f.getName().endsWith(".txt")) {
				//ako je u pitanju txt fajl radi indeksiranje tog fajla
				indexFile(writer, f);
			}
		}
	}
	
	// method to actually index a file using Lucene
	//parametri su indexWriter i File
	private static void indexFile(IndexWriter writer, File f)throws IOException {
		if (f.isHidden() || !f.exists() || !f.canRead()) {
			return;
		}
		
		//Ispis sta indeksuje
		System.out.println("Indexing....");
		
		//poziv metode koja broji koliko ima paragrafa u contacts.txt
		//int numOfparagraph = CountParagraphContacts.getParagraphCount();
		
		//System.out.println("Number of paragraphs in contacts.txt: " + numOfparagraph);
		
		try {
			Connection con = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ues", "root", "root");
			Statement stmt = con.createStatement();
			String sql = "select contact_id, displayname, email, firstname, lastname, note from contacts";
			ResultSet rs = stmt.executeQuery(sql);
        
			while (rs.next()) {
				Document doc = new Document();
				doc.add(new TextField("contact_id", rs.getString("contact_id"), Store.YES));
				doc.add(new TextField("displayname", rs.getString("displayname"), Store.YES));
				doc.add(new TextField("email", rs.getString("email"), Store.YES));
				doc.add(new TextField("firstname", rs.getString("firstname"), Store.YES)); 
				doc.add(new TextField("lastname", rs.getString("lastname"), Store.YES));
				doc.add(new TextField("note", rs.getString("note"), Store.YES));
				String modificationDate = DateTools.dateToString(new Date(f.lastModified()), DateTools.Resolution.DAY);
				doc.add(new StringField("filename", f.getCanonicalPath(), Store.YES));
				doc.add(new TextField("filedate", modificationDate,Store.YES));
				
				writer.addDocument(doc);
           }	
		} catch(Exception e) {
			e.printStackTrace();
	}
		
	}
	
}