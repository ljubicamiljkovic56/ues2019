package ues.projekat.y.search.indexing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class IndexerMessage {
	
	//main metoda odakle se poziva index metoda
		public static void main(String[] args) throws Exception {
			//indexDir folder gde se smestaju indeksi
			Directory indexDirMessages;
			//fajl dataDir koji treba da se indeksira
			File dataDirMessages;
			if (args.length != 2) {
				try{
					//odavde cita iz luceneindex fajla indexDir i dataDir lokacije i radi store text
					ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
					//new File indexDir
					indexDirMessages = new SimpleFSDirectory(new File(rb.getString("indexDirMessages")));
					//new File dataDir
					dataDirMessages = new File(rb.getString("dataDirMessages"));
				}catch(Exception e1){
					for(String arg :args)
						System.out.println(arg);
					//izbaci exception ako nismo naveli kako treba u ResourceBundle
					throw new Exception("Usage: java " + Indexer.class.getName()+ " <index dir> <data dir> or properties file needed");
				}
			}else{
				indexDirMessages = new SimpleFSDirectory(new File(args[0]));
				dataDirMessages = new File(args[1]);
			}
			//pocetak indeksiranja
			long start = new Date().getTime();
			//broji koliko je fajlova indeksirao
			int numIndexed = index(indexDirMessages, dataDirMessages);
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
					//String message_id = br.readLine();
					doc.add(new TextField("message_id", readLine, Store.YES));
					//String from = br.readLine();
					doc.add(new TextField("message_from", readLine, Store.YES));
					//String to = br.readLine();
					doc.add(new TextField("message_to", readLine, Store.YES));
					//String cc = readLine;
					doc.add(new TextField("cc", readLine, Store.YES));
					//String bcc = readLine;
					doc.add(new TextField("bcc", readLine, Store.YES));
					//String subject = readLine;
					doc.add(new TextField("message_subject", readLine, Store.YES));
					//String content = readLine;
					doc.add(new TextField("content", readLine, Store.YES));
				
				String modificationDate = DateTools.dateToString(new Date(f.lastModified()),DateTools.Resolution.DAY);
				doc.add(new StringField("filename", f.getCanonicalPath(), Store.YES));
				doc.add(new TextField("filedate",modificationDate,Store.YES));
				
			}			
			System.out.println("Zapisao u doc");
			writer.addDocument(doc);
			br.close();
		}
}