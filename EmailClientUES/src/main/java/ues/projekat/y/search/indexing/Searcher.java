package ues.projekat.y.search.indexing;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

import ues.projekat.y.search.misc.SerbianAnalyzer;
import ues.projekat.y.search.model.ResultRetrieverContact;



public class Searcher {
	
	public static void main(String[] args) throws Exception {
		//Kreiranje InputStreamReader-a (za decode karaktera)
		InputStreamReader ir = new InputStreamReader(System.in, "UTF8");
		//BufferedReader cita redove 
		BufferedReader in = new BufferedReader(ir);
		//indexDir folder
		File indexDir;
		//string koji mi prosledjujemo
		String q;
		if (args.length != 2) {
			try{
				//ResourseBundle trazi gde se nalazi putanja indexDir
				ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
				indexDir = new File(rb.getString("indexDir"));
				System.out.println("unesite izraz za pretragu:");
				q = in.readLine();
				
				//ako taj folder ne postoji, ide exception
			}catch(Exception e1){
				e1.printStackTrace();
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + Indexer.class.getName()+ " <index dir> <data dir> or properties file needed");
			}
		}else{
			indexDir = new File(args[0]);
		 	q = args[1];
		}
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new Exception(indexDir +" does not exist or is not a directory.");
		}
		search(indexDir, q);
	}
	
	//pretraga, indexDir i prosledjeni string
	@SuppressWarnings("deprecation")
	public static void search(File indexDir, String q)throws Exception {
		
		//novi QueryParser objekat, dodeljujemo mu verziju lucene, sadrzaj i analyzer
		QueryParser qp = new QueryParser(Version.LUCENE_40,"sadrzaj_fajla", new SerbianAnalyzer());
		
		//napravimo objekat query i krece sa parsiranjem prosledjenog stringa
		Query query = qp.parse(q);
		
		//ispis upita
		System.out.println(query);
		
		//poslacemo ga u nasu klasu za izvrsavanje pretrazivanja i print rezultata
		ResultRetrieverContact rr=new ResultRetrieverContact();
		//metoda koju pozivamo iz ResultRetrieverContact nad upitom i indexDir folderom
		rr.printSearchResults(query, indexDir);
	}
}