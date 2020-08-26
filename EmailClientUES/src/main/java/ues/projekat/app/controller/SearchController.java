package ues.projekat.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.y.search.indexing.Searcher;
import ues.projekat.y.search.query.BooleanSearcher;
import ues.projekat.y.search.query.FuzzySearcher;
import ues.projekat.y.search.query.PhraseSearcher;
import ues.projekat.y.search.query.TermSearcher;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {
	
	@PostMapping(value = "/regular/contact")
	public ResponseEntity<Void> regularSearchContact(@RequestParam String regularSearch) throws Exception{
		File indexDir;
		ResourceBundle rb = ResourceBundle.getBundle("ues.projekat.y.search.indexing.luceneindex");
		indexDir = new File(rb.getString("indexDir"));
		Searcher.search(indexDir, regularSearch);
	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/boolean/contact")
	public ResponseEntity<Void> booleanSearchContact(@RequestParam String field1, @RequestParam String term1, 
			@RequestParam String field2, @RequestParam String term2) throws IOException {
		BooleanSearcher.search();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/term/contact")
	public ResponseEntity<Void> termSearchContact(@RequestParam String field1, @RequestParam String term1) throws Exception{
		
		TermSearcher.searchTerm();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/fuzzy/contact")
	public ResponseEntity<Void> fuzzySearchContact(@RequestParam String field1, @RequestParam String term1) throws Exception {
		
		FuzzySearcher.searchFuzzy();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/phrase/contact")
	public ResponseEntity<Void> phraseSearchContact(@RequestParam String field1, @RequestParam String term1) throws Exception{
		
		PhraseSearcher.searchPhrase();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
