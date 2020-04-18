package ues.projekat.search.analyzers;

import org.apache.lucene.analysis.Analyzer;

public class SerbianAnalyzer extends Analyzer {
	

    public static final String[] STOP_WORDS =
    {
        "i","a","ili","ali","pa","te","da","u","po","na"
    };

    
    public SerbianAnalyzer(){
    	
    }

	@Override
	protected TokenStreamComponents createComponents(String arg0) {
		return null;
	}

}
