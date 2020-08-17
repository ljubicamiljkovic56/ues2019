package ues.projekat.y.search.indexing;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, new ues.projekat.y.search.misc.SerbianAnalyzer()); // pozvati 
		iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
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
				//
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
		System.out.println("Indexing " + f.getCanonicalPath());
		//Kreiranje novog Document objekta
		Document doc = new Document();
		//Kreiranje InputStreamReader-a (za decode karaktera), zato kao parametar prosledjuemo 
		//novi FileInputStream (input bytes for a file) za taj fajl i charset
		InputStreamReader ir = new InputStreamReader(new FileInputStream(f),"UTF-8");
		//BufferedReader cita redove txt fajla
		BufferedReader br= new BufferedReader(ir);

		
		String readLine = null;
		while ((readLine = br.readLine()) != null) {
				readLine = readLine.trim();
				System.out.println(readLine);
				System.out.println("Usao u funkciju");
				String contact_id = br.readLine();
				doc.add(new TextField("contact_id", readLine, Store.YES));
				String displayname = br.readLine();
				doc.add(new TextField("displayname", readLine, Store.YES));
				String email = br.readLine();
				doc.add(new TextField("email", readLine, Store.YES));
				String firstname = readLine;
				doc.add(new TextField("firstname", readLine, Store.YES));
				String lastname = readLine;
				doc.add(new TextField("lastname", readLine, Store.YES));
				String note = readLine;
				doc.add(new TextField("note", readLine, Store.YES));
				String photo = readLine;
				doc.add(new TextField("photo", readLine, Store.YES));
				String user = readLine;
				doc.add(new TextField("user", readLine, Store.YES));
			
			String modificationDate = DateTools.dateToString(new Date(f.lastModified()),DateTools.Resolution.DAY);
			doc.add(new StringField("filename", f.getCanonicalPath(), Store.YES));
			doc.add(new TextField("filedate",modificationDate,Store.YES));
			
		}
//		for(int i = 0; i < f.length(); i ++) {
//			String contact_id = br.readLine();
//			System.out.println("Contact id: "+ contact_id);
//			doc.add(new TextField("contact_id", contact_id, Store.YES));
//			String displayname = br.readLine();
//			System.out.println("Displayname: " + displayname);
//			doc.add(new TextField("displayname", displayname, Store.YES));
//			String email = br.readLine();
//			doc.add(new TextField("email", email, Store.YES));
//			String firstname = br.readLine();
//			doc.add(new TextField("firstname", firstname, Store.YES));
//			String lastname = br.readLine();
//			doc.add(new TextField("lastname", lastname, Store.YES));
//			String note = br.readLine();
//			doc.add(new TextField("note", note, Store.YES));
//			String photo = br.readLine();
//			doc.add(new TextField("photo", photo, Store.YES));
//			String user = br.readLine();
//			doc.add(new TextField("user", user, Store.YES));
//			String modificationDate = DateTools.dateToString(new Date(f.lastModified()),DateTools.Resolution.DAY);
//			doc.add(new StringField("filename", f.getCanonicalPath(), Store.YES));
//			doc.add(new TextField("filedate",modificationDate,Store.YES));
//			writer.addDocument(doc);
//		}

		


		
		System.out.println("Zapisao u doc");
		writer.addDocument(doc);
		br.close();
	}
}