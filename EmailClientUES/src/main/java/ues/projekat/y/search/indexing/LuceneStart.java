package ues.projekat.y.search.indexing;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;


import ues.projekat.y.search.misc.LuceneUtils;


public class LuceneStart {
	
	String indexDir = "C:\\Users\\Ljubica\\Downloads\\indexDir"; 
	String dataDir = "C:\\Users\\Ljubica\\Downloads\\dataDir"; 
	Indexer indexer; 
	Searcher searcher;
	public static void startPoint() 
	{ 
		LuceneStart start; 
		try 
		{ 
			start = new LuceneStart(); 
			start.createIndex(); 
		//	start.search("note3"); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		}
	} 
	private void createIndex() throws IOException { 
		indexer = new Indexer(indexDir); 
		int numIndexed; 
		long startTime = System.currentTimeMillis(); 
		numIndexed = indexer.createIndex(dataDir, new ues.projekat.y.search.misc.TextFileFilter()); 
		long endTime = System.currentTimeMillis(); 
		indexer.close(); 
		System.out.println(numIndexed + " File indexed, time taken: " + (endTime-startTime)+ " ms"); 
	} 
	
	private void search(String searchQuery) throws IOException, ParseException
	{ 
		searcher = new Searcher(indexDir); 
		long startTime = System.currentTimeMillis(); 
		TopDocs hits = searcher.search(searchQuery); 
		long endTime = System.currentTimeMillis(); 
		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime)); 
		for(ScoreDoc scoreDoc : hits.scoreDocs) 
		{ 
			Document doc = searcher.getDocument(scoreDoc); 
			System.out.println("File: " + doc.get(LuceneUtils.FILE_PATH)); 
		} 
		//searcher.close(); 
	} 

}
