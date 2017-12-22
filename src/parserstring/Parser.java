package parserstring;

import java.util.LinkedList;
import java.util.List;

public class Parser {

    public static List<String> parserBasicsPhase(String basicsPhase) throws RuntimeException {

        basicsPhase = basicsPhase.replaceAll("[^а-я^А-Я^-^0-9]", " ");

        LinkedList<String> strList = new LinkedList<>();

        for (String str : basicsPhase.split(" ")) {
            if (!str.equals("")) {
                strList.add(str);
            }
        }

        if (strList.isEmpty()) {
            throw new RuntimeException("Передана пустая строка");
        }
        return strList;
    }

    public static List<List<String>> parserSentence(String sentence) throws RuntimeException {

        sentence = sentence.replaceAll("[@\"№#;$%^:&*()\\!?.]", ",");

        List<List<String>> sentenceList = new LinkedList<>();

        for (String basicsPhase : sentence.split(",")) {
            if (!basicsPhase.equals("")) {
                try {
                    sentenceList.add(parserBasicsPhase(basicsPhase));
                } catch (RuntimeException ex) {
                }
            }
        }

        if (sentenceList.isEmpty()) {
            throw new RuntimeException("Передана пустая строка");
        }

        return sentenceList;
    }

    public static List<List<List<String>>> parserParagraph(String paragraph) {

        List<List<List<String>>> paragraphList = new LinkedList<>();

        for (String sentence : paragraph.split("[@\"№#;$%^:&*()\\!?.]")) {
            if (!sentence.equals("")) {
                try {
                    paragraphList.add(parserSentence(sentence));
                } catch (RuntimeException ex) {

                }
            }
        }

        return paragraphList;
    }

    public static List<List<List<List<String>>>> parserText(String text) {

        List<List<List<List<String>>>> textList = new LinkedList<>();

        for (String paragraph : text.split("[\\r\\n]")) {
            if (!paragraph.equals("")) {
                textList.add(parserParagraph(paragraph));
            }
        }

        return textList;
    }

}
