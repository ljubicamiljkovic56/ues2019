package ues.projekat.search.filters;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class CyrilicToLatinFilter extends TokenFilter {
	
	private CharTermAttribute termAttribute;

	public CyrilicToLatinFilter(TokenStream input) {
		super(input);
		termAttribute=(CharTermAttribute)input.addAttribute(CharTermAttribute.class); 
	}
	

	@Override
	public boolean incrementToken() throws IOException {
		  if (input.incrementToken()) {
	        	String text=termAttribute.toString();
	        	termAttribute.setEmpty();
	        	termAttribute.append(CyrilicLatinConverter.cir2lat(text));
	        	return true;
	        }
	       return false;
	}

}