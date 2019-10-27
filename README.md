# Parser

#### Создание объекта парсера
```
GraphematicParser parser = new GParserImpl();
```

#### Пример 1
```
List<List<String>> listBasicPhase = parser.parserSentence("Parser - это программа"
    + " начального анализа естественного текста, представленного в виде цепочки"
    + " символов, вырабатывающая информацию, необходимую для дальнейшей обработки.");
System.out.println(listBasicPhase);
```
#### Вывод
```
[
	[-, это, программа, начального, анализа, естественного, текста], 
	[представленного, в, виде, цепочки, символов], 
	[вырабатывающая, информацию], 
	[необходимую, для, дальнейшей, обработки]
]
```

#### Пример 2
```
List<List<List<String>>> listBasicsPhase = parser.parserParagraph("Осенний марафон -"
	+ " стало ясно, что будет с российской валютой. Справедливый курс,"
	+ " по мнению аналитиков, — на уровне 65-66.");
System.out.println(listBasicsPhase);
```

#### Вывод
```
[
	[
		[Осенний, марафон, -, стало, ясно], 
		[что, будет, с, российской, валютой]
	], 
	[
		[Справедливый, курс], 
		[по, мнению, аналитиков], 
		[на, уровне, 65-66]
	]
]
```
