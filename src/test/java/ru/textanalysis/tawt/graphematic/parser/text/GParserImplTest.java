package ru.textanalysis.tawt.graphematic.parser.text;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GParserImplTest {

	private static HashMap<String, String[]> parserBasicsPhase = new HashMap<>();
	private static HashMap<String, String[][]> parserSentence = new HashMap<>();
	private static HashMap<String, String[][][]> parserParagrap = new HashMap<>();

	@BeforeAll
	public static void setParserBasicsPhase() {
		parserBasicsPhase.put("Привет как дела", new String[] {"Привет", "как", "дела"});
		parserBasicsPhase.put("Привет как дела!", new String[] {"Привет", "как", "дела"});
		parserBasicsPhase.put("Привет как дела! ", new String[] {"Привет", "как", "дела"});
		parserBasicsPhase.put(" Привет как дела! ", new String[] {"Привет", "как", "дела"});
		parserBasicsPhase.put("Привет как дела!!!!&", new String[] {"Привет", "как", "дела"});
		parserBasicsPhase.put("Привет как дела,&, как жизнь", new String[] {"Привет", "как", "дела", "как", "жизнь"});

		parserSentence.put("Привет как дела", new String[][] {{"Привет", "как", "дела"}});
		parserSentence.put("Привет как дела!", new String[][] {{"Привет", "как", "дела"}});
		parserSentence.put("Привет как дела!!!!", new String[][] {{"Привет", "как", "дела"}});
		parserSentence.put("Привет как дела, как жизнь?", new String[][] {{"Привет", "как", "дела"}, {"как", "жизнь"}});
		parserSentence.put("Привет как дела, как жизнь????", new String[][] {{"Привет", "как", "дела"}, {"как", "жизнь"}});
		parserSentence.put("Привет как дела, как жизнь????&", new String[][] {{"Привет", "как", "дела"}, {"как", "жизнь"}});
		parserSentence.put(" Привет как дела,,,, как жизнь?! Чем обычно занимаешь? ", new String[][] {{"Привет", "как", "дела"}, {"как", "жизнь"}, {"Чем", "обычно", "занимаешь"}});

		parserParagrap.put("Привет как дела", new String[][][] {{{"Привет", "как", "дела"}}});
		parserParagrap.put("Привет как дела!", new String[][][] {{{"Привет", "как", "дела"}}});
		parserParagrap.put("Привет как дела!!!!", new String[][][] {{{"Привет", "как", "дела"}}});
		parserParagrap.put("Привет как дела, как жизнь?", new String[][][] {{{"Привет", "как", "дела"}, {"как", "жизнь"}}});
		parserParagrap.put("Привет как дела, как жизнь????", new String[][][] {{{"Привет", "как", "дела"}, {"как", "жизнь"}}});
		parserParagrap.put("Привет как дела, как жизнь????&", new String[][][] {{{"Привет", "как", "дела"}, {"как", "жизнь"}}});
		parserParagrap.put(" Привет как дела,,,, как жизнь?! Чем обычно занимаешь?  Привет как дела,,,, как жизнь?! Чем обычно занимаешь?", new String[][][] {{{"Привет", "как", "дела"}, {"как", "жизнь"}}, {{"Чем", "обычно", "занимаешь"}}, {{"Привет", "как", "дела"}, {"как", "жизнь"}}, {{"Чем", "обычно", "занимаешь"}}});
		parserParagrap.put(" Привет как дела,,,, как жизнь?! Чем обычно занимаешь?  Привет как дела,,,, как жизнь?! Чем обычно занимаешь? ", new String[][][] {{{"Привет", "как", "дела"}, {"как", "жизнь"}}, {{"Чем", "обычно", "занимаешь"}}, {{"Привет", "как", "дела"}, {"как", "жизнь"}}, {{"Чем", "обычно", "занимаешь"}}});
	}

	@AfterEach
	public void tear() {
		parserBasicsPhase.clear();
		parserSentence.clear();
	}

	@Test
	public void testParserBasicsPhase() {
		GParserImpl parser = new GParserImpl();
		parserBasicsPhase.entrySet().forEach((entry) -> {
			final String[] expected = entry.getValue();
			final String testData = entry.getKey();
			final List<String> actual = parser.parserBasicsPhase(testData);
			testParserBasicsPhase(actual, expected);
		});
	}

	public void testParserBasicsPhase(List<String> actual, String[] expected) {
		assertEquals(expected.length, actual.size());
		int index = 0;
		for (String word : actual) {
			assertEquals(expected[index], word);
			index++;
		}
		;
	}

	@Test
	public void testParserSentence() {
		GParserImpl parser = new GParserImpl();
		parserSentence.entrySet().forEach((entry) -> {
			final String[][] expected = entry.getValue();
			final String testData = entry.getKey();
			final List<List<String>> actual = parser.parserSentence(testData);
//            testParserParagraph(actual, expected);todo
		});
	}

	public void testParserSentence(List<List<String>> actual, String[][] expected) {
		assertEquals(expected.length, actual.size());
		int indexX = 0;
		for (List<String> basicsPhase : actual) {
			testParserBasicsPhase(basicsPhase, expected[indexX]);
			indexX++;
		}
		;
	}

	@Test
	public void testParserParagraph() {
		GParserImpl parser = new GParserImpl();
		parserParagrap.entrySet().forEach((entry) -> {
			final String[][][] expected = entry.getValue();
			final String testData = entry.getKey();
			final List<List<List<String>>> actual = parser.parserParagraph(testData);
			testParserParagraph(actual, expected);
		});
	}

	public void testParserParagraph(List<List<List<String>>> actual, String[][][] expected) {
		assertEquals(expected.length, actual.size());
		int indexX = 0;
		for (List<List<String>> sentence : actual) {
			testParserSentence(sentence, expected[indexX]);
			indexX++;
		}
	}
}
