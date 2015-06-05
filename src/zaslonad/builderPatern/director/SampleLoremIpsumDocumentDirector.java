package zaslonad.builderPatern.director;

import zaslonad.builderPatern.builder.DocumentBuilder;
import zaslonad.builderPatern.test.IPSUM;
import zaslonad.builderPatern.test.IPSUMLists;

public class SampleLoremIpsumDocumentDirector {
	DocumentBuilder builder;
	IPSUMLists ipsum;
	
	public SampleLoremIpsumDocumentDirector(DocumentBuilder builder){
		this.builder = builder;
		ipsum = new IPSUMLists();
	}
	
	public void construct(){
		addAutors(2);
		builder.setDocumentTitle(IPSUM.wers1);
		builder.addChapter(IPSUM.wers4);
		addParagrafy(2);
		builder.setChaprerLevelDown();
		builder.addChapter(IPSUM.wers4);
		addParagrafy(3);
		addSimpleList(5);
		addParagrafy(1);
		builder.addChapter(IPSUM.wers3);
		addParagrafy(3);
		addSimpleList(5);
		addParagrafy(1);
		builder.addChapter(IPSUM.wers3);
		addParagrafy(3);
		addSimpleList(5);
		addParagrafy(1);
		builder.setChaprerLevelDown();
		builder.addChapter(IPSUM.wers3);
		addParagrafy(3);
		addSimpleList(5);
		addParagrafy(1);
		builder.setChapterLevelUp();
		builder.addChapter(IPSUM.wers7);
		addParagrafy(2);
		addParagrafy(1);
		builder.addChapter(IPSUM.wers6);
		addParagrafy(1);
		addSimpleList(2);
		addParagrafy(1);
		builder.setChaprerLevelDown();
		builder.addChapter(IPSUM.wers3);
		addParagrafy(3);
		addSimpleList(5);
		addParagrafy(1);
		builder.setChapterLevelUp();
		builder.addChapter(IPSUM.wers7);
		addParagrafy(2);
		addParagrafy(1);
		builder.addChapter(IPSUM.wers6);
		addParagrafy(1);
		addSimpleList(2);
		addParagrafy(1);
	}
	private void addSimpleList(int ile){
		for(int i = 0; i<ile; i++){
			builder.addListElement(ipsum.gerRandomWers());
		}
	}
	private void addAutors(int ile){
		for(int i = 0; i<ile; i++){
			builder.addListElement(ipsum.gerRandomNazwisko());
		}
	}
	private void addParagrafy(int ile){
		for(int i = 0; i<ile; i++){
			builder.addListElement(ipsum.gerRandomAkapit());
		}
	}
}
