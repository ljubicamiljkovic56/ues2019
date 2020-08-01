package ues.projekat.y.search.misc;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class CyrillicToLatinFilter extends TokenFilter {


	private CharTermAttribute termAttribute;

	public CyrillicToLatinFilter(TokenStream input) {
		super(input);
		termAttribute=(CharTermAttribute)input.addAttribute(CharTermAttribute.class); 
	}
	
	public boolean incrementToken()throws IOException {
        if (input.incrementToken()) {
        	String text=termAttribute.toString();
        	System.out.println("Sta je text u ovom slucaju " + text);
        	termAttribute.setEmpty();
        	termAttribute.append(CyrillicLatinConverter.cir2lat(text));
        	
        	return true;
        }
        return false;
    }

}
