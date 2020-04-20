package ues.projekat.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ues.projekat.search.analyzers.SerbianAnalyzer;
import ues.projekat.search.misc.QueryBuilder;
import ues.projekat.search.misc.ResultRetriever;
import ues.projekat.search.model.AdvancedQuery;
import ues.projekat.search.model.RequiredHighlight;
import ues.projekat.search.model.ResultData;
import ues.projekat.search.model.SearchType;
import ues.projekat.search.model.SimpleQuery;

@RestController
public class SearchController {
	
	@PostMapping(value="/search/term", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchTermQuery(@RequestBody SimpleQuery simpleQuery) throws Exception {		
		Query query= QueryBuilder.buildQuery(SearchType.regular, simpleQuery.getField(), simpleQuery.getValue());
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
		List<ResultData> results = ResultRetriever.getResults(query, rh);			
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	
	@PostMapping(value="/search/fuzzy", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchFuzzy(@RequestBody SimpleQuery simpleQuery) throws Exception {
		Query query= QueryBuilder.buildQuery(SearchType.fuzzy, simpleQuery.getField(), simpleQuery.getValue());
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
		List<ResultData> results = ResultRetriever.getResults(query, rh);			
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	
	@PostMapping(value="/search/prefix", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchPrefix(@RequestBody SimpleQuery simpleQuery) throws Exception {
		Query query= QueryBuilder.buildQuery(SearchType.prefix, simpleQuery.getField(), simpleQuery.getValue());
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
		List<ResultData> results = ResultRetriever.getResults(query, rh);			
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	
	@PostMapping(value="/search/range", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchRange(@RequestBody SimpleQuery simpleQuery) throws Exception {
		Query query= QueryBuilder.buildQuery(SearchType.range, simpleQuery.getField(), simpleQuery.getValue());
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
		List<ResultData> results = ResultRetriever.getResults(query, rh);			
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	
	@PostMapping(value="/search/phrase", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchPhrase(@RequestBody SimpleQuery simpleQuery) throws Exception {
		Query query= QueryBuilder.buildQuery(SearchType.phrase, simpleQuery.getField(), simpleQuery.getValue());
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
		List<ResultData> results = ResultRetriever.getResults(query, rh);			
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	
	@PostMapping(value="/search/boolean", consumes="application/json")
	public ResponseEntity<List<ResultData>> searchBoolean(@RequestBody AdvancedQuery advancedQuery) throws Exception {
		Query query1 = QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField1(), advancedQuery.getValue1());
		Query query2 = QueryBuilder.buildQuery(SearchType.regular, advancedQuery.getField2(), advancedQuery.getValue2());
		
		BooleanQuery.Builder builder=new BooleanQuery.Builder();
		if(advancedQuery.getOperation().equalsIgnoreCase("AND")){
			builder.add(query1,BooleanClause.Occur.MUST);
			builder.add(query2,BooleanClause.Occur.MUST);
		}else if(advancedQuery.getOperation().equalsIgnoreCase("OR")){
			builder.add(query1,BooleanClause.Occur.SHOULD);
			builder.add(query2,BooleanClause.Occur.SHOULD);
		}else if(advancedQuery.getOperation().equalsIgnoreCase("NOT")){
			builder.add(query1,BooleanClause.Occur.MUST);
			builder.add(query2,BooleanClause.Occur.MUST_NOT);
		}
		
		Query query = builder.build();
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		rh.add(new RequiredHighlight(advancedQuery.getField1(), advancedQuery.getValue1()));
		rh.add(new RequiredHighlight(advancedQuery.getField2(), advancedQuery.getValue2()));
		List<ResultData> results = ResultRetriever.getResults(query, rh);			
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}
	
	@PostMapping(value="/search/queryParser", consumes="application/json")
	public ResponseEntity<List<ResultData>> search(@RequestBody SimpleQuery simpleQuery) throws Exception {
		QueryParser qp=new QueryParser("title", new SerbianAnalyzer());			
		Query query=qp.parse(simpleQuery.getValue());			
		List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
		List<ResultData> results = ResultRetriever.getResults(query, rh);
		return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
	}


}
