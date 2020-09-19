package ues.projekat.y.search.indexing;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearcherForPdf {

	@SuppressWarnings("deprecation")
	public SearcherForPdf(String searchString) {
        File indexDirPdf;
		try {
        	ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
            indexDirPdf = new File(rb.getString("indexDirPdf"));
            Directory index = FSDirectory.open(indexDirPdf);
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer1 = new StandardAnalyzer(Version.LUCENE_40);
            QueryParser queryParser = new QueryParser(Version.LUCENE_40, "content", analyzer1);
            QueryParser queryParserfilename = new QueryParser(Version.LUCENE_40, "fullpath", analyzer1);
            Query query = queryParser.parse(searchString);//to search in the content
            Query queryfilename = queryParserfilename.parse(searchString);//to search the file name only        
            TopDocs hits = searcher.search(query, 1000); //for 
            ScoreDoc[] document = hits.scoreDocs;
            System.out.println("Total no of hits for content: " + hits.totalHits);

            for (int i = 0; i < document.length; i++) {
                Document doc = searcher.doc(document[i].doc);
                String filePath = doc.get("fullpath");
                System.out.println(filePath);
            }

            TopDocs hitfilename = searcher.search(queryfilename, 100);//for filename      
            System.out.println("Total no of hits according to file name: " + hitfilename.totalHits);
            ScoreDoc[] documentfilename = hitfilename.scoreDocs;
            for (int i = 0; i < documentfilename.length; i++) {
                Document doc = searcher.doc(documentfilename[i].doc);
                String filePath = doc.get("fullpath");
                System.out.println(filePath);
            }
        } catch (Exception e) {
        }

    }
    
    public static void main(String args[])
    {
        new SearcherForPdf("prijavljeni");
    }
}
