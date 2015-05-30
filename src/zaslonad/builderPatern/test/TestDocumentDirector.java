package zaslonad.builderPatern.test;

import zaslonad.builderPatern.builder.DocumentBuilder;

public class TestDocumentDirector {
	DocumentBuilder builder;
	
	public TestDocumentDirector(DocumentBuilder builder){
		this.builder = builder;
	}
	
	public void construct(){
		builder
			.addAuthor("Darek Zet")
			.addAuthor("Adaś \"Kowal\" Kowalski")
			.setDocumentTitle("Dokument Testowy")
			.addChapter(IPSUM.wers1)
			.addParagraph(IPSUM.akapit1)
			.addListElement(IPSUM.wers2)
			.addListElement(IPSUM.wers3)
			.addParagraph(IPSUM.akapit4);
	}
}
