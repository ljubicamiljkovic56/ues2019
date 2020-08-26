package ues.projekat.y.search.query;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import ues.projekat.y.search.indexing.Indexer;
import ues.projekat.y.search.model.ResultRetrieverContact;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;

public class BooleanSearcher {
	
	//boolean search: AND, OR, NOT
	public static void main(String[] args) throws Exception{
		//BufferedReader  uzima podatke preko InputStreamReader-a
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//novi fajl indexDir
		File indexDir;
		//prvi termin koji unosimo
		String termin1;
		//drugi termin koji unosimo
		String termin2;
		//naziv prvog polja
		String polje;
		//naziv drugog polja
		String polje2;
		//naziv boolean operacije
		String veznaOp="";
		if (args.length != 6) {
			try{
				//nadjemo indexDir
				ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
				indexDir = new File(rb.getString("indexDir"));
				//prvo polje i njegova vrednost
				System.out.println("unesite polje za pretragu:");
				polje = in.readLine();
				System.out.println("unesite prvi termin:");
				termin1 = in.readLine();
				//drugo polje i njegova vrednost
				System.out.println("unesite polje za pretragu:");
				polje2 = in.readLine();
				System.out.println("unesite drugi izraz:");
				termin2 = in.readLine();
				//naziv operacije
				System.out.println("Unesite naziv operacije");
				veznaOp = in.readLine();
			}catch(Exception e1){
				//exception ako ne postoji fajl
				for(String arg :args)
					System.out.println(arg);
				throw new Exception("Usage: java " + Indexer.class.getName()+ " <index dir> <polje1> <term1> <polje2> <term2> <operator> or properties file needed");
			}
		}else{
			indexDir = new File(args[0]);
			polje = args[1];
		 	termin1 = args[2];
		 	polje2 = args[3];
		 	termin2 = args[4];
		 	veznaOp = args[5];
		 	
		}
		if (!indexDir.exists() || !indexDir.isDirectory()) {
			throw new Exception(indexDir +" does not exist or is not a directory.");
		}
		
		
		//konstruisati TermQuery-je koji ce biti kombinovani u BooleanQuery
		
		
		TermQuery query1=new TermQuery(new Term(polje,termin1));
		TermQuery query2= new TermQuery(new Term(polje2,termin2));
		
		//konstruisanje BooleanQuery-a ovde se kombinuju 2 maksimum 1024
		//moze se promeniti pomocu BooleanQuery.setMaxClauseCount();
		BooleanQuery bq = new BooleanQuery();
		if(veznaOp.equalsIgnoreCase("AND")){
			//mora da sadrzi oba termina
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST);
		}else if(veznaOp.equalsIgnoreCase("OR")){
			//mora da sadrzi jedan od dva termina 
			bq.add(query1,BooleanClause.Occur.SHOULD);
			bq.add(query2,BooleanClause.Occur.SHOULD);
		}else if(veznaOp.equalsIgnoreCase("NOT")){
			//prvi termin sadrzi, ali ne sadrzi drugi termin
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST_NOT);
		}
		
		//poslacemo ga u nasu klasu za izvrsavanje pretrazivanja i print rezultata
		ResultRetrieverContact rr=new ResultRetrieverContact();
		rr.printSearchResults(bq, indexDir);		
	}

	
	public static void search() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//novi fajl indexDir
		File indexDir;
		//prvi termin koji unosimo
		String termin1;
		//drugi termin koji unosimo
		String termin2;
		//naziv prvog polja
		String polje;
		//naziv drugog polja
		String polje2;
		//naziv boolean operacije
		String veznaOp="";
		
			ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
			indexDir = new File(rb.getString("indexDir"));
			//prvo polje i njegova vrednost
			System.out.println("unesite polje za pretragu:");
			polje = in.readLine();
			System.out.println("unesite prvi termin:");
			termin1 = in.readLine();
			//drugo polje i njegova vrednost
			System.out.println("unesite polje za pretragu:");
			polje2 = in.readLine();
			System.out.println("unesite drugi izraz:");
			termin2 = in.readLine();
			//naziv operacije
			System.out.println("Unesite naziv operacije");
			veznaOp = in.readLine();
		
		
		TermQuery query1=new TermQuery(new Term(polje,termin1));
		TermQuery query2= new TermQuery(new Term(polje2,termin2));
		
		//konstruisanje BooleanQuery-a ovde se kombinuju 2 maksimum 1024
		//moze se promeniti pomocu BooleanQuery.setMaxClauseCount();
		BooleanQuery bq = new BooleanQuery();
		if(veznaOp.equalsIgnoreCase("AND")){
			//mora da sadrzi oba termina
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST);
		}else if(veznaOp.equalsIgnoreCase("OR")){
			//mora da sadrzi jedan od dva termina 
			bq.add(query1,BooleanClause.Occur.SHOULD);
			bq.add(query2,BooleanClause.Occur.SHOULD);
		}else if(veznaOp.equalsIgnoreCase("NOT")){
			//prvi termin sadrzi, ali ne sadrzi drugi termin
			bq.add(query1,BooleanClause.Occur.MUST);
			bq.add(query2,BooleanClause.Occur.MUST_NOT);
		}
		
		//poslacemo ga u nasu klasu za izvrsavanje pretrazivanja i print rezultata
		ResultRetrieverContact rr=new ResultRetrieverContact();
		rr.printSearchResults(bq, indexDir);	
		
	}
}