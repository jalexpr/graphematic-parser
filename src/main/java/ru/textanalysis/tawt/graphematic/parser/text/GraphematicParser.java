package ru.textanalysis.tawt.graphematic.parser.text;

import ru.textanalysis.tawt.graphematic.parser.exception.NotParserTextException;

import java.util.List;

public interface GraphematicParser {

	List<String> parserBasicsPhase(String basicsPhase) throws NotParserTextException;

	List<List<String>> parserSentence(String sentence) throws NotParserTextException;

	List<List<List<String>>> parserParagraph(String paragraph) throws NotParserTextException;

	List<List<List<List<String>>>> parserText(String text) throws NotParserTextException;

	/**
	 * Разбивает текст на токены - отдельные слова, знаки препинания и пробелы, - которые помещаются в один список.
	 * @param basicsPhase Фраза, которую необходимо разбить на токены.
	 * @return Список, хранящий переданную фразу в виде токенов, включая отдельные слова, знаки препинания и пробелы.
	 */
	List<String> parserBasicsPhaseWithPunctuation(String basicsPhase);

	/**
	 * Разбивает текст на токены - отдельные слова, знаки препинания и пробелы, - которые помещаются в два списка: вложенный
	 * список хранит токены каждой части предложения, выделенной знаками препинания, а второй - списки с разбитыми на токены
	 * фразами.
	 * @param sentence Предложение, которое необходимо разбить на токены.
	 * @return Список, хранящий переданное предложение в виде вложенных списков, где содержатся токены, включая отдельные
	 * слова, знаки препинания и пробелы.
	 */
	List<List<String>> parserSentenceWithPunctuation(String sentence);

	/**
	 * Разбивает текст на токены - отдельные слова, знаки препинания и пробелы, - которые помещаются в три списка: первый
	 * вложенный список хранит токены каждой части предложения, выделенной знаками препинания, второй - списки с разбитыми
	 * на токены фразами, третий - списки с предложениями, разбитыми на фразы.
	 * @param paragraph Абзац, который необходимо разбить на токены.
	 * @return Список, хранящий переданный параграф в виде вложенных списков, где содержатся токены, включая отдельные
	 * слова, знаки препинания и пробелы.
	 */
	List<List<List<String>>> parserParagraphWithPunctuation(String paragraph);

	/**
	 * Разбивает текст на токены - отдельные слова, знаки препинания и пробелы, - которые помещаются в четыре списка: первый
	 * вложенный список хранит токены каждой части предложения, выделенной знаками препинания, второй - списки с разбитыми
	 * на токены фразами, третий - списки с предложениями, разбитыми на фразы, четвертый - списки с абзацами, из которых
	 * состоит переданный текст.
	 * @param text Текст, который необходимо разбить на токены.
	 * @return Список, хранящий переданный текст в виде вложенных списков, где содержатся токены, включая отдельные
	 * слова, знаки препинания и пробелы.
	 */
	List<List<List<List<String>>>> parserTextWithPunctuation(String text);
}