package ues.projekat.y.search.indexing;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import ues.projekat.y.search.misc.LuceneUtils;
import ues.projekat.y.search.misc.SerbianAnalyzer;

public class Searcher {
	
	private IndexSearcher indexSearcher;
	private QueryParser queryParser; 
	private Query query;
	
	@SuppressWarnings("deprecation")
	public Searcher(String indexDirectoryPath) throws IOException
	{ 
		Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath)); 
		DirectoryReader ireader = DirectoryReader.open(indexDirectory);
		IndexSearcher is = new IndexSearcher(ireader);
		queryParser = new QueryParser(Version.LUCENE_40, LuceneUtils.CONTENTS, new SerbianAnalyzer());
		
	} 
	
	
	public TopDocs search( String searchQuery) throws IOException, org.apache.lucene.queryParser.ParseException
	{ 
		query = queryParser.parse(searchQuery); 
		return indexSearcher.search(query, LuceneUtils.MAX_SEARCH); 
	} 
	public Document getDocument(ScoreDoc scoreDoc) throws CorruptIndexException, IOException
	{ 
		return indexSearcher.doc(scoreDoc.doc); 
	} 


}
