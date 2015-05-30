package zaslonad.builderPatern.builder;

public interface DocumentBuilder {
	
	
	/**
	 * Dodaje autrora dokumentu, może zostać wywołana tylko na samym począktu i maksymalnie 3 razy
	 * @param name: Ciąg znaków określających autora dokumentu, max 30 znaków, nie null, niepusty ciąg znaków
	 * @return Obiekt klasy implementującej interfejs DocumentBuilder
	 * @throws	InllegalArgumentException 
	 * 				jeśli name jest nullem, pustym stringiem, ma więcej niż 30 znaków
	 * 			InllegalStateException 
	 * 				jeśli funkcja została wywołana więcej niż 3 razy
	 * 				jeśli wcześniej została wywołana setDocumentTitle(String)
	 */
	DocumentBuilder addAuthor(String name);
	
	
	/**
	 * Ustawia tytuł dokumentu, musi zostać wywołana bezpośrednio po konstruktorze, lub po metodzie addAuthor(String)
	 * @param title: max 100 znaków, niepusty, nie null
	 * @return Obiekt klasy implementującej interfejs DocumentBuilder
	 * @throws 	InllegalArgumentException 
	 * 				jeśli title jest nulem, pustym ciągiem znaków, lub długość większa niż 100 znaków
	 * 			InllegalStateException 
	 * 				jeśli funkcja została już wcześniej wywołana
	 */
	DocumentBuilder setDocumentTitle(String title);
	
	
	/**
	 * Ustawia poziom zagnieżdżenia podrozdziałów o jeden poziom niżej
	 * @return Obiekt klasy implementującej interfejs DocumentBuilder
	 * @throws	IllegalStateException 
	 * 				jeśli aktualny poziom zagnieżdzenia wynosi 3,
	 * 				jeśli została wywołana przed metodą setDocumentTitle(String), lub bezpośrednio po niej
	 *				jeśli na aktualnym poziomie zagnieżdzenia rozdziałów nie został dodany żaden rozdział
	 */
	DocumentBuilder setChaprerLevelDown();
	
	
	/**
	 * Ustawia poziom zagnieżdżenia podrozdziałów o jeden poziom wyżej
	 * @return Obiekt klasy implementującej interfejs DocumentBuilder
	 * @throws	InllegalStateException 
	 * 				jeśli aktualne zagnieżdzenie wynosi 0, 
	 * 				jeśli na aktualnym pozimie utworzono rozdział który nie zawierał treści, czyli po ostatnim wywołaniu 
	 * 					funkcji addChapter(String) nie została wywołana	metoda addParagraph(String) lub addListElement(String)
	 * 				
	 */
	DocumentBuilder setChapterLevelUp();
	
	
	/**
	 * Tworzy nowy rozdział, lub podrozdział jeśli poziom zagnieżdzenia większy od 3
	 * @param title, null lub ciąg znaków o długości od 1 do 100 znaków
	 * @return Obiekt klasy implementującej interfejs DocumentBuilder
	 * @throws	InllegalArgumentException 
	 * 				jeśli pusty ciag znaków, lub dłuższy od 100
	 * 			InllegalStateException 
	 * 				jeśli została wywołana przed metodą setDocumentTitle
	 * 				jeśli ostatni rozdział nie zawierał treśći lub nie wywołano metody setChapterLevelDown()
	 * 				
	 */
	DocumentBuilder addChapter(String title);
	
	
	/**
	 * Dodaje paragraf do ostatnio utworzonego rozdziału
	 * @param text, treść paragrafu, różny od null i nie pusty
	 * @return Obiekt klasy implementującej interfejs DocumentBuilder
	 * @throws	InllegalArgumentException 
	 * 				jeśli ciag znaków pusty, lub null
	 * 			InllegalStateException 
	 * 				jeśli została wywołana przed metodą setDocumentTitle
	 * 				jesli
	 */
	DocumentBuilder addParagraph(String text);
	
	
	/**
	 * Dodaje element listy
	 * @param text, treść elementu listy
	 * @return Obiekt klasy implementującej interfejs DocumentBuilder
	 * @throws	InllegalArgumentException 
	 * 				jeśli null, lub pusty ciąg znaków
	 * 			InllegalStateException 	
	 * 				jeśli została wywołana przed metodą setDocumentTitle
	 */
	DocumentBuilder addListElement(String text);
	
	
	/**
	 * Zwraca zbudowany dokuent w formie ciągu znaków
	 * @return Obiekt String z pakietu java.lang
	 * @throws	InllegalStateException 	
	 * 				jeśli została wywołana przed metodą setDocumentTitle
	 * 				jeśli przed wywołaniem build() nie została wywołana funkcja
	 * 					addParagraph(String text) lub addListElement(String text)
	 * 					(czyli jeśli ostatnio dodany rozdział był pusty)
	 */
	String build();
}
