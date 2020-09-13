package ues.projekat.y.search.indexing;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

import ues.projekat.y.search.misc.SerbianAnalyzer;
import ues.projekat.y.search.model.ResultRetrieverMessage;

public class SearcherMessage {
	
	//sve isto kao u klasi Searcher
	public static void main(String[] args) throws Exception {
		InputStreamReader ir = new InputStreamReader(System.in, "UTF8");
		BufferedReader in = new BufferedReader(ir);
		File indexDirMessages;
		String q;
		if (args.length != 2) {
			try{
				ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
				indexDirMessages = new File(rb.getString("indexDirMessages"));
				System.out.println("unesite izraz za pretragu:");
				q = in.readLine();
			}catch(Exception e1){
				e1.printStackTrace();
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + Indexer.class.getName()+ " <index dir> <data dir> or properties file needed");
			}
		}else{
			indexDirMessages = new File(args[0]);
		 	q = args[1];
		}
		if (!indexDirMessages.exists() || !indexDirMessages.isDirectory()) {
			throw new Exception(indexDirMessages +" does not exist or is not a directory.");
		}
		search(indexDirMessages, q);
	}
	
	@SuppressWarnings("deprecation")
	public static void search(File indexDir, String q)throws Exception {
		
		QueryParser qp = new QueryParser(Version.LUCENE_40,"sadrzaj_fajla", new SerbianAnalyzer());
		
		Query query = qp.parse(q);
		System.out.println(query);
		//poslacemo ga u nasu klasu za izvrsavanje pretrazivanja i print rezultata
		ResultRetrieverMessage rr=new ResultRetrieverMessage();
		rr.printSearchResults(query, indexDir);
	}

}
