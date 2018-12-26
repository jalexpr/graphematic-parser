import ru.textanalysis.tfwwt.parser.string.Parser;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        List<List<String>> listBasicsPhase = Parser.parserSentence("Parser - это программа " +
                "начального анализа естественного текста, представленного в виде цепочки ASCII " +
                "символов, вырабатывающая информацию, необходимую для дальнейшей обработки");
        System.out.println(listBasicsPhase);
    }
}
