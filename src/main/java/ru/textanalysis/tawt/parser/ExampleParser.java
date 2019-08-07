package ru.textanalysis.tawt.parser;

import ru.textanalysis.tawt.parser.exception.NotParserTextException;
import ru.textanalysis.tawt.parser.text.ParserImpl;

import java.util.List;

public class ExampleParser {
    public static void main(String...args) throws NotParserTextException {
        String sentence;
        if (args == null || args.length == 0) {
            sentence = "Parser - это программа " +
                    "начального анализа естественного текста, представленного в виде цепочки ASCII " +
                    "символов, вырабатывающая информацию, необходимую для дальнейшей обработки";
        } else {
            sentence = args[0];
        }
        ParserImpl parser = new ParserImpl();
        List<List<String>> listBasicsPhase = parser.parserSentence(sentence);
        System.out.println(listBasicsPhase);
    }
}
