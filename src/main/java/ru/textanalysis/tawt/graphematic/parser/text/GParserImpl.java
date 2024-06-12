package ru.textanalysis.tawt.graphematic.parser.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.textanalysis.tawt.graphematic.parser.exception.NotParserTextException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GParserImpl implements GraphematicParser {

	private Logger log = LoggerFactory.getLogger(getClass());

	public GParserImpl() {
		log.debug("TAWT Parser is inited!");
	}

	@Override
	public List<String> parserBasicsPhase(String basicsPhase) throws NotParserTextException {
		basicsPhase = basicsPhase.replaceAll("[^а-яА-Я\\-0-9ёЁa-zA-Z]", " ");

		LinkedList<String> strList = new LinkedList<>();

		for (String str : basicsPhase.split(" ")) {
			if (!str.isBlank()) {
				strList.add(str);
			}
		}

		if (strList.isEmpty()) {
//            throw new NotParserTextException("Передана пустая строка");
		}
		return strList;
	}

	public List<String> parserBasicsPhaseWithPunctuation(String basicsPhase) {
		return new LinkedList<>(Arrays.asList(basicsPhase.split("(?<=(\n))|((?<![\\p{L}\\p{N}_-])|(?![\\p{L}\\p{N}_-]))((?<![,./])|(?!(\\p{N})))")));
	}

	public List<List<String>> parserSentence(String sentence) throws NotParserTextException {
		sentence = sentence.replaceAll("[@\"№#;$%^:&*()!?.]", ",");

		List<List<String>> sentenceList = new LinkedList<>();

		for (String basicsPhase : sentence.split(",")) {
			basicsPhase = basicsPhase.trim();
			if (!basicsPhase.isBlank()) {
				try {
					sentenceList.add(parserBasicsPhase(basicsPhase));
				} catch (NotParserTextException ex) {
//                    log.debug("Sentence = {}, exception: {}", sentence, ex.getMessage());
				}
			}
		}

		if (sentenceList.isEmpty()) {
//            throw new NotParserTextException("Передана пустая строка");
		}

		return sentenceList;
	}

	public List<List<String>> parserSentenceWithPunctuation(String sentence) {
		List<List<String>> phraseList = new LinkedList<>();

		for (String basicsPhase : sentence.split("(?=(\n)|(?<=[,.!?–;:])(?!(\\p{N}))|(?=[,.!?–;:\n])(?<!(\\p{N})))")) {
			phraseList.add(parserBasicsPhaseWithPunctuation(basicsPhase));
		}

		return phraseList;
	}

	@Override
	public List<List<List<String>>> parserParagraph(String paragraph) throws NotParserTextException {
		List<List<List<String>>> paragraphList = new LinkedList<>();

		for (String sentence : paragraph.split("[@\"№#;$%^:&*()!?.]")) {
			if (!sentence.isBlank()) {
				try {
					paragraphList.add(parserSentence(sentence));
				} catch (NotParserTextException ex) {
//                    log.debug("Paragraph = {}, exception: {}", sentence, ex.getMessage());
				}
			}
		}

		if (paragraphList.isEmpty()) {
//            throw new NotParserTextException("Передана пустая строка");
		}

		return paragraphList;
	}

	public List<List<List<String>>> parserParagraphWithPunctuation(String paragraph) {
		List<List<List<String>>> sentenceList = new LinkedList<>();

		for (String sentence : paragraph.split("(?=(\n)|(?<=[.!?])(?!(\\p{N}))|(?=[.!?\n])(?<!(\\p{N})))")) {
			sentenceList.add(parserSentenceWithPunctuation(sentence));
		}

		return sentenceList;
	}

	@Override
	public List<List<List<List<String>>>> parserText(String text) throws NotParserTextException {
		List<List<List<List<String>>>> textList = new LinkedList<>();

		for (String paragraph : text.split("[\\r\\n]")) {
			if (!paragraph.isBlank()) {
				try {
					textList.add(parserParagraph(paragraph));
				} catch (NotParserTextException ex) {
//                    log.debug("Text = {}, exception: {}", text, ex.getMessage());
				}
			}
		}

		if (textList.isEmpty()) {
//            throw new NotParserTextException("Передана пустая строка");
		}

		return textList;
	}

	public List<List<List<List<String>>>> parserTextWithPunctuation(String text) {
		List<List<List<List<String>>>> paragraphList = new LinkedList<>();

		for (String paragraph : text.split("(?=(\n))")) {
			paragraphList.add(parserParagraphWithPunctuation(paragraph));
		}

		return paragraphList;
	}
}