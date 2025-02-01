package ru.textanalysis.tawt.graphematic.parser;

import ru.textanalysis.tawt.graphematic.parser.exception.NotParserTextException;
import ru.textanalysis.tawt.graphematic.parser.text.GParserImpl;
import ru.textanalysis.tawt.graphematic.parser.text.GraphematicParser;

import java.util.List;


public class ExampleParser {

	public static void main(String... args) throws NotParserTextException {
		{
			GraphematicParser parser = new GParserImpl();
			List<String> words = parser.parserBasicsPhase("Graphematic Parser - это программа"
				+ " начального анализа естественно-языкового текста, рассматривающая его как цепочку"
				+ " символов для получения информации, необходимой для следующих этапов анализа.");
			System.out.println(words);

//			[
//			    Graphematic, Parser, -, это, программа, начального, анализа, естественно-языкового,
//			    текста, рассматривающая, его, как, цепочку, символов, для, получения, информации,
//			    необходимой, для, следующих, этапов, анализа
//			]
		}
		{
			GraphematicParser parser = new GParserImpl();
			List<List<String>> basicPhases = parser.parserSentence("Graphematic Parser - это программа"
				+ " начального анализа естественно-языкового текста, рассматривающая его как цепочку"
				+ " символов для получения информации, необходимой для следующих этапов анализа.");
			System.out.println(basicPhases);

//			[
//				[Graphematic, Parser, -, это, программа, начального, анализа, естественно-языкового, текста],
//			    [рассматривающая, его, как, цепочку, символов, для, получения, информации],
//			    [необходимой, для, следующих, этапов, анализа]
//			]
		}

		{
			GraphematicParser parser = new GParserImpl();
			List<List<List<String>>> listBasicsPhase = parser.parserParagraph("Осенний марафон -"
				+ " стало ясно, что будет с российской валютой. Справедливый курс,"
				+ " по мнению аналитиков, — на уровне 65-66.");
			System.out.println(listBasicsPhase);
		}
	}
}
