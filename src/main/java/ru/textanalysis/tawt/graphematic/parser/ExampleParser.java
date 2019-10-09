package ru.textanalysis.tawt.graphematic.parser;

import ru.textanalysis.tawt.graphematic.parser.exception.NotParserTextException;
import ru.textanalysis.tawt.graphematic.parser.text.ParserImpl;

import java.util.List;

public class ExampleParser {
    public static void main(String...args) throws NotParserTextException {
        String sentence;
        if (args == null || args.length == 0) {
            sentence = "Осенний марафон: стало ясно, " +
                    "что будет с российской валюто. Справедливый курс, по мнению " +
                    "аналитиков, — на уровне 65-66.";
        } else {
            sentence = args[0];
        }
        ParserImpl parser = new ParserImpl();
        List<List<List<String>>> listBasicsPhase = parser.parserParagraph("Осенний марафон -"
                + " стало ясно, что будет с российской валютой. Справедливый курс,"
                + " по мнению аналитиков, — на уровне 65-66.");
        System.out.println(listBasicsPhase);
    }
}
