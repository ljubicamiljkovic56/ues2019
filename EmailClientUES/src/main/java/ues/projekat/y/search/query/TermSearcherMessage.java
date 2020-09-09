package ues.projekat.y.search.query;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import ues.projekat.y.search.indexing.Indexer;
import ues.projekat.y.search.model.ResultRetrieverMessage;

public class TermSearcherMessage {

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		File indexDirMessages;
		String termin;
		String polje;
		if (args.length != 3) {
			try{
				ResourceBundle rb=ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
				indexDirMessages = new File(rb.getString("indexDirMessages"));
				System.out.println("unesite polje za pretragu:");
				polje = in.readLine();
				System.out.println("unesite izraz za pretragu:");
				termin = in.readLine();
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + Indexer.class.getName()+ " <indexDir> <naziv polja> <trazeni termin> or properties file needed");
			}
		}else{
			indexDirMessages = new File(args[0]);
			polje = args[1];
		 	termin = args[2];
		}
		if (!indexDirMessages.exists() || !indexDirMessages.isDirectory()) {
			throw new Exception(indexDirMessages +" does not exist or is not a directory.");
		}else{
			Term t = new Term(polje,termin);
			Query query = new TermQuery(t);
			ResultRetrieverMessage rr = new ResultRetrieverMessage();
			rr.printSearchResults(query, indexDirMessages);
		}
	}
	
	
	public static void searchTerm() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		File indexDirMessages;
		String termin;
		String polje;
		
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDirMessages = new File(rb.getString("indexDirMessages"));
		System.out.println("unesite polje za pretragu:");
		polje = in.readLine();
		System.out.println("unesite izraz za pretragu:");
		termin = in.readLine();
		
		if (!indexDirMessages.exists() || !indexDirMessages.isDirectory()) {
			throw new Exception(indexDirMessages +" does not exist or is not a directory.");
		}else{
			Term t = new Term(polje,termin);
			Query query = new TermQuery(t);
			ResultRetrieverMessage rr = new ResultRetrieverMessage();
			rr.printSearchResults(query, indexDirMessages);
		}
	}
}
