package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.component.Chart;
import com.example.demo.component.EarleyParser;
import com.example.demo.component.Grammar;
import com.example.demo.component.SimpleGrammar;
import com.example.demo.component.State;
import com.example.demo.entity.Word;
import com.example.demo.repo.WordRepository;

@Controller
public class IndexController {

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	private SimpleGrammar simpleGrammar;

	@GetMapping("/parse")
	@ResponseBody
	public Chart[] parse(@RequestParam("sentences[]") String[] sentences) {
		Grammar grammar = new SimpleGrammar();
		EarleyParser parser = new EarleyParser(grammar);
		parser.parseSentence(sentences);
		return parser.getCharts();
	}

	@PostMapping("/parse1")
	@ResponseBody
	public Chart[] parse1(@RequestBody List<Word> words) {
		String[] sentences = new String[words.size()];
		int i = 0;
		for (Word word : words) {
			System.out.println(word.toString());
			sentences[i] = word.getValue();
			i++;
		}
		simpleGrammar.addRules(words);
		EarleyParser parser = new EarleyParser(simpleGrammar);
		test(sentences, parser);
		return parser.getCharts();
	}

	@GetMapping("/search_category")
	@ResponseBody
	public Set<String> search_category(@RequestParam("word") String word) {
		List<Word> wordObjs = wordRepository.findByValueIgnoreCase(word);
		Set<String> results = new HashSet<String>();
		for (Word wordObj : wordObjs) {
			results.add(wordObj.getCategory());
		}
		return results;
	}

	@GetMapping("/")
	public String index() {
		return "early-parser";
	}

	static void test(String[] sent, EarleyParser parser) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < sent.length - 1; i++) {
			out.append(sent[i] + " ");
		}
		out.append(sent[sent.length - 1] + ".");
		String sentence = out.toString();
		System.out.println("\nSentence: \"" + sentence + "\"");
		boolean successful = parser.parseSentence(sent);
		System.out.println("Parse Successful:" + successful);
		Chart[] charts = parser.getCharts();
		System.out.println("");
		System.out.println("Charts produced by the sentence\"" + sentence + "\"");
		for (int i = 0; i < charts.length; i++) {
			System.out.println("Chart " + i + ":");
			System.out.println("////////////////////////////");
			for (int j = 0; j < charts[i].size(); j++) {
				State st = charts[i].getState(j);
				String[] temp = st.getRhs().getTerms();
				String rhs = "";
				for (int z = 0; z < temp.length; z++) {
					rhs = rhs + temp[z] + " ";
				}
				System.out.println(st.getI() + " " + st.getLhs() + "->" + rhs);
			}
		}
	}
}
