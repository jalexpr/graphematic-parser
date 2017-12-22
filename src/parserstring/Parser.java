
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import parserstring.Parser;

public class TestParserBasicsPhase {

    HashMap<String, String[]> parserBasicsPhase = new HashMap<>();
    HashMap<String, String[][]> parserSentence = new HashMap<>();
    HashMap<String, String[][][]> parserParagrap = new HashMap<>();

    @Before
    public void setParserBasicsPhase() {
        parserBasicsPhase.put("Привет как дела", new String[]{"Привет", "как", "дела"});
        parserBasicsPhase.put("Привет как дела!", new String[]{"Привет", "как", "дела"});
        parserBasicsPhase.put("Привет как дела! ", new String[]{"Привет", "как", "дела"});
        parserBasicsPhase.put(" Привет как дела! ", new String[]{"Привет", "как", "дела"});
        parserBasicsPhase.put("Привет как дела!!!!&", new String[]{"Привет", "как", "дела"});
        parserBasicsPhase.put("Привет как дела,&, как жизнь", new String[]{"Привет", "как", "дела", "как", "жизнь"});

        parserSentence.put("Привет как дела", new String[][]{{"Привет", "как", "дела"}});
        parserSentence.put("Привет как дела!", new String[][]{{"Привет", "как", "дела"}});
        parserSentence.put("Привет как дела!!!!", new String[][]{{"Привет", "как", "дела"}});
        parserSentence.put("Привет как дела, как жизнь?", new String[][]{{"Привет", "как", "дела"}, {"как", "жизнь"}});
        parserSentence.put("Привет как дела, как жизнь????", new String[][]{{"Привет", "как", "дела"}, {"как", "жизнь"}});
        parserSentence.put("Привет как дела, как жизнь????&", new String[][]{{"Привет", "как", "дела"}, {"как", "жизнь"}});
        parserSentence.put(" Привет как дела,,,, как жизнь?! Чем обычно занимаешь? ", new String[][]{{"Привет", "как", "дела"}, {"как", "жизнь"}, {"Чем", "обычно", "занимаешь"}});

        parserParagrap.put("Привет как дела", new String[][][]{{{"Привет", "как", "дела"}}});
        parserParagrap.put("Привет как дела!", new String[][][]{{{"Привет", "как", "дела"}}});
        parserParagrap.put("Привет как дела!!!!", new String[][][]{{{"Привет", "как", "дела"}}});
        parserParagrap.put("Привет как дела, как жизнь?", new String[][][]{{{"Привет", "как", "дела"}, {"как", "жизнь"}}});
        parserParagrap.put("Привет как дела, как жизнь????", new String[][][]{{{"Привет", "как", "дела"}, {"как", "жизнь"}}});
        parserParagrap.put("Привет как дела, как жизнь????&", new String[][][]{{{"Привет", "как", "дела"}, {"как", "жизнь"}}});
        parserParagrap.put(" Привет как дела,,,, как жизнь?! Чем обычно занимаешь?  Привет как дела,,,, как жизнь?! Чем обычно занимаешь?", new String[][][]{{{"Привет", "как", "дела"}, {"как", "жизнь"}}, {{"Чем", "обычно", "занимаешь"}}, {{"Привет", "как", "дела"}, {"как", "жизнь"}}, {{"Чем", "обычно", "занимаешь"}}});
        parserParagrap.put(" Привет как дела,,,, как жизнь?! Чем обычно занимаешь?  Привет как дела,,,, как жизнь?! Чем обычно занимаешь? ", new String[][][]{{{"Привет", "как", "дела"}, {"как", "жизнь"}}, {{"Чем", "обычно", "занимаешь"}}, {{"Привет", "как", "дела"}, {"как", "жизнь"}}, {{"Чем", "обычно", "занимаешь"}}});
    }

    @After
    public void tear() {
        parserBasicsPhase.clear();
        parserSentence.clear();
    }

    @Test
    public void testParserBasicsPhase() {
        parserBasicsPhase.entrySet().forEach((entry) -> {
            final String[] expected = entry.getValue();
            final String testData = entry.getKey();
            final List<String> actual = Parser.parserBasicsPhase(testData);
            testParserBasicsPhase(actual, expected);
        });
    }

    public void testParserBasicsPhase(List<String> actual, String[] expected) {
        assertEquals(expected.length, actual.size());
        int index = 0;
        for (String word : actual) {
            assertEquals(expected[index], word);
            index++;
        };
    }

    @Test
    public void testParserSentence() {
        parserSentence.entrySet().forEach((entry) -> {
            final String[][] expected = entry.getValue();
            final String testData = entry.getKey();
            final List<List<String>> actual = Parser.parserSentence(testData);

        });
    }

    public void testParserSentence(List<List<String>> actual, String[][] expected) {
        assertEquals(expected.length, actual.size());
        int indexX = 0;
        for (List<String> basicsPhase : actual) {
            testParserBasicsPhase(basicsPhase, expected[indexX]);
            indexX++;
        };
    }

    @Test
    public void testParserParagraph() {
        parserParagrap.entrySet().forEach((entry) -> {
            final String[][][] expected = entry.getValue();
            final String testData = entry.getKey();
            final List<List<List<String>>> actual = Parser.parserParagraph(testData);
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
