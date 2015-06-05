package zaslonad.builderPatern.builder.impl;

import zaslonad.builderPatern.builder.DocumentBuilder;
import zaslonad.builderPatern.exceptions.NotReadyStateException;

public class TxtBuilder implements DocumentBuilder{
	
	StringBuilder sBuilder;
	int poziom;
	boolean isNotReady;
	boolean isTitleAdded;
	boolean isNumerated;
	boolean isAutorAdded;
	boolean isNoChapter;
	
	public TxtBuilder(boolean isChapterNumerated){
		isNumerated = isChapterNumerated;
		sBuilder = new StringBuilder();
		poziom = 0;
		isNotReady = true;
		isTitleAdded = false;
		isAutorAdded = false;
		isNoChapter = true;
	}

	@Override
	public DocumentBuilder setDocumentTitle(String title) {
		valid(title);
		if(isTitleAdded){
			throw new IllegalStateException("Tytul dokumentu został już podany");
		}
		sBuilder.append("\nTytuł:\n\t\t")
				.append(title.toUpperCase())
				.append("\n\n");
		return this;
	}

	@Override
	public DocumentBuilder addAuthor(String name) {
		valid(name);
		if(!isAutorAdded){
			sBuilder.append("Autorzy:\n");
			isAutorAdded = true;
		}
		sBuilder.append("\t")
				.append(name.trim().replaceAll("\n", " "))
				.append("\n");
		return this;
	}

	@Override
	public DocumentBuilder setChapterLevelUp() {
		if(poziom <= 0){
			throw new IllegalStateException("Jesteś już na najwyższym poziomie zagnieżdżenia!");
		}
		if(isNotReady){
			throw new NotReadyStateException();
		}
		poziom++;
		return this;
	}

	@Override
	public DocumentBuilder setChaprerLevelDown() {
		if(poziom >= 3){
			throw new IllegalStateException("Jesteś już na najwyższym poziomie zagnieżdżenia!");
		}
		if(isNoChapter){
			throw new IllegalStateException("Aby dodawac podrozdziału musisz utworzyć pierwszy rozdział!");
		}
		poziom--;
		return this;
	}

	@Override
	public DocumentBuilder addChapter(String title) {
		valid(title);
		if(isNoChapter){
			isNoChapter=false;
		}else if(isNotReady){
			throw new NotReadyStateException();
		}
		sBuilder.append("\n\t")
				.append(title)
				.append("\n");
		return this;
	}

	@Override
	public DocumentBuilder addParagraph(String text) {
		valid(text);
		sBuilder.append("\n\t")
				.append(text);
		isNotReady = false;
		return this;
	}

	@Override
	public DocumentBuilder addListElement(String text) {
		valid(text);
		sBuilder.append("\n\t<> ")
				.append(text);
		return this;
	}

	
	public String build() {
		return sBuilder.toString();
	}
	
	private boolean valid(String input) {
		if(input == null){
			throw new IllegalArgumentException("Parametr musi być różny od null!");
		}
		if(input.trim().length() == 0){
			throw new IllegalArgumentException("Parametr musi zawierać przynajmniej jeden widoczny znak!");
		}
		return true;
	}

}
