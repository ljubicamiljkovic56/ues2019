package ues.projekat.y.search.indexing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import ues.projekat.y.search.misc.SerbianAnalyzer;

public class IndexerForPdf {
	
	private final String source = "C:\\Users\\Ljubica\\Downloads\\attachs";
	private final String indexFile = "C:\\Users\\Ljubica\\Downloads\\indexDirPdf";
	private IndexWriter writer = null;
	private File indexDirectory = null;
	private String fileContent;

  
    public IndexerForPdf() throws FileNotFoundException, CorruptIndexException, IOException {
        try {
            long start = System.currentTimeMillis();
            createIndexWriter();
            checkFileValidity();
            closeIndexWriter();
            long end = System.currentTimeMillis();
            System.out.println("Total Document Indexed : " + TotalDocumentsIndexed());
            System.out.println("Total time" + (end - start) / (100 * 60));
        } catch (Exception e) {
            System.out.println("Greska pri indeksiranju.");
        }
    }

    @SuppressWarnings("deprecation")
	private void createIndexWriter() {
        try {
            indexDirectory = new File(indexFile);
            if (!indexDirectory.exists()) {
                indexDirectory.mkdir();
            }
            FSDirectory dir = FSDirectory.open(indexDirectory);
            SerbianAnalyzer analyzer = new SerbianAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
            writer = new IndexWriter(dir, config);
        } catch (Exception ex) {
            System.out.println("Greska sa index writerom");
        }
    }

    private void checkFileValidity() {

        File[] filesToIndex = new File[100]; // suppose there are 100 files at max
        filesToIndex = new File(source).listFiles();
        for (File file : filesToIndex) {
            try {
                //to check whenther the file is a readable file or not.
                if (!file.isDirectory()
                        && !file.isHidden()
                        && file.exists()
                        && file.canRead()
                        && file.length() > 0.0
                        && file.isFile() ) {
                    if(file.getName().endsWith(".txt")){
                        indexTextFiles(file);//if the file text file no need to parse text. 
                    System.out.println("INDEXED FILE " + file.getAbsolutePath() + " :-) ");
                    }
                    else if(file.getName().endsWith(".doc") || file.getName().endsWith(".pdf")){
                        //different methof for indexing doc and pdf file.
                       StartIndex(file);                    
                    }
                }
            } catch (Exception e) {
                System.out.println("Greska, ne moze se indeksirati fajl " + file.getAbsolutePath());
            }
        }
    }
    

    @SuppressWarnings("deprecation")
	public void StartIndex(File file) throws FileNotFoundException, CorruptIndexException, IOException {
         fileContent = null;
        try {
            Document doc = new Document();
            if (file.getName().endsWith(".pdf")) {
                //call the pdf file parser and get the content of pdf file in txt format
                fileContent = new PdfFileParser().PdfFileParser(file.getAbsolutePath());
            }
            doc.add(new Field("content", fileContent, Field.Store.YES, Field.Index.ANALYZED,Field.TermVector.WITH_POSITIONS_OFFSETS));
            doc.add(new Field("filename", file.getName(),Field.Store.YES, Field.Index.ANALYZED));
            doc.add(new Field("fullpath", file.getAbsolutePath(),Field.Store.YES, Field.Index.ANALYZED));
            if (doc != null) {
                writer.addDocument(doc);
            }
            System.out.println("Indexed" + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Greska pri indeksiranju" + (file.getAbsolutePath()));
        }
    }

    @SuppressWarnings("deprecation")
	private void indexTextFiles(File file) throws CorruptIndexException, IOException {
        Document doc = new Document();
        doc.add(new Field("content", new FileReader(file)));
        doc.add(new Field("filename", file.getName(),Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("fullpath", file.getAbsolutePath(),Field.Store.YES, Field.Index.ANALYZED));
        if (doc != null) {
            writer.addDocument(doc);
        }
    }

    @SuppressWarnings("deprecation")
	private int TotalDocumentsIndexed() {
        try {
            IndexReader reader = IndexReader.open(FSDirectory.open(indexDirectory));
            return reader.maxDoc();
        } catch (Exception ex) {
            System.out.println("Greska, nema indeksa");
        }
        return 0;
    }
    private void closeIndexWriter() {
        try {
         //   writer.optimize();
            writer.close();
        } catch (Exception e) {
            System.out.println("Indexer se ne moze zatvoriti");
        }
    }

    public static void main(String arg[]) {
        try {
            new IndexerForPdf();
        } catch (Exception ex) {
            System.out.println("Greska, ne moze se pokrenuti metoda");
        }
    }
}
