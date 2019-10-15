package ru.textanalysis.tawt.graphematic.parser.text;

import ru.textanalysis.tawt.graphematic.parser.exception.NotParserTextException;

import java.util.List;

public interface GraphematicParser {
    List<String> parserBasicsPhase(String basicsPhase) throws NotParserTextException;

    List<List<String>> parserSentence(String sentence) throws NotParserTextException;

    List<List<List<String>>> parserParagraph(String paragraph) throws NotParserTextException;

    List<List<List<List<String>>>> parserText(String text) throws NotParserTextException;
}
