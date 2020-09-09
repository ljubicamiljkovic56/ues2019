package ues.projekat.y.search.query;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;

import ues.projekat.y.search.indexing.Indexer;
import ues.projekat.y.search.model.ResultRetrieverMessage;

public class FuzzySearcherMessage {
	

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		File indexDirMessages;
		String termin;
		String polje;
		if (args.length != 3) {
			try{
				ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
				indexDirMessages = new File(rb.getString("indexDirMessages"));
				System.out.println("unesite polje za pretragu:");
				polje = in.readLine();
				System.out.println("unesite izraz za pretragu:");
				termin = in.readLine();
			}catch(Exception e1){
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + Indexer.class.getName()+ " <index dir> <data dir> or properties file needed");
			}
		}else{
			indexDirMessages = new File(args[0]);
			polje = args[1];
		 	termin = args[2];
		}
		if (!indexDirMessages.exists() || !indexDirMessages.isDirectory()) {
			throw new Exception(indexDirMessages +" does not exist or is not a directory.");
		}
		
		//kreirati term za pretragu
		Term t = new Term(polje,termin);
		//postaviti meru slicnosti
		int editDis = 2;
		//kreirati FuzzyQuery
		FuzzyQuery query = new FuzzyQuery(t,editDis);
		
		//poslacemo ga u nasu klasu za izvrsavanje pretrazivanja i print rezultata
		ResultRetrieverMessage rr = new ResultRetrieverMessage();
		rr.printSearchResults(query, indexDirMessages);
		
	}
	
	public static void searchFuzzy() throws Exception {
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
		}
		
		//kreirati term za pretragu
		Term t = new Term(polje,termin);
		//postaviti meru slicnosti
		int editDis = 2;
		//kreirati FuzzyQuery
		FuzzyQuery query = new FuzzyQuery(t,editDis);
		
		//poslacemo ga u nasu klasu za izvrsavanje pretrazivanja i print rezultata
		ResultRetrieverMessage rr = new ResultRetrieverMessage();
		rr.printSearchResults(query, indexDirMessages);
	}

}
