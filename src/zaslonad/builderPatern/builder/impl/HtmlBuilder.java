package zaslonad.builderPatern.builder.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zaslonad.builderPatern.builder.DocumentBuilder;
import zaslonad.builderPatern.exceptions.NotReadyStateException;

public class HtmlBuilder implements DocumentBuilder{
	
	StringBuilder sBuilder;
	int poziom;
	int chapterNumber[] = {1,1,1,1};
	boolean isNotReady;
	boolean isTitleAdded;
	boolean isNumerated;
	boolean isAutorAdded;
	boolean isNoChapter;
	boolean isListStarted;
	boolean isDocumentClosed;
	List<String> authorsList;
	
	public HtmlBuilder(boolean isChapterNumerated){
		isNumerated = isChapterNumerated;
		sBuilder = new StringBuilder();
		poziom = 0;
		isNotReady = true;
		isTitleAdded = false;
		isAutorAdded = false;
		isNoChapter = true;
		authorsList = new ArrayList<String>();
		isListStarted = false;
		isDocumentClosed = false;
	}

	@Override
	public DocumentBuilder setDocumentTitle(String title) {
		valid(title);
		if(isTitleAdded){
			throw new IllegalStateException("Tytul dokumentu został już podany");
		}
		startDocument(title);
		sBuilder.append(serroundTag("Tytuł: ".concat(title.toUpperCase()), "h1"));
		isTitleAdded = true;
		return this;
	}

	
	private void startDocument(String title) {
		sBuilder.append(openTag("html"))
				.append(openTag("head"))
				.append(openTag("meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/"));
		if(title != null){
			sBuilder.append(serroundTag(title, "title"));
		}
		sBuilder.append(closeTag("head"))
				.append(openTag("body"));
		if(authorsList.size() == 1){
			sBuilder.append(serroundTag("Autor: ", "h2"));
		}else if(authorsList.size() > 1){
			sBuilder.append(serroundTag("Autorzy: ", "h2"));
		}
		for(String author : authorsList){
			sBuilder.append(serroundTag(author, "h3"));
		}
	}

	@Override
	public DocumentBuilder addAuthor(String name) {
		valid(name);
		authorsList.add(name);
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
		if(isListStarted){
			sBuilder.append(closeTag("ul"));
			isListStarted = false;
		}
		// Resetowanie numeru podrozdziału
		chapterNumber[poziom] = 1;
		poziom--;
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
		if(isListStarted){
			sBuilder.append(closeTag("ul"));
			isListStarted = false;
		}
		poziom++;
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
		if(!isTitleAdded){
			startDocument(null);
			isTitleAdded = true;
		}
		if(isListStarted){
			sBuilder.append(closeTag("ul"));
			isListStarted = false;
		}
		sBuilder.append(prepareChapterTitle(title));
		incrementChapterNumber();
		isNotReady = true;
		return this;
	}

	@Override
	public DocumentBuilder addParagraph(String text) {
		valid(text);
		if(isListStarted){
			sBuilder.append(closeTag("ul"));
			isListStarted = false;
		}
		sBuilder.append(serroundTag(text, "p"));
		isNotReady = false;
		return this;
	}

	@Override
	public DocumentBuilder addListElement(String text) {
		valid(text);
		if(!isListStarted){
			sBuilder.append(openTag("ul"));
			isListStarted = true;
		}
		sBuilder.append(serroundTag(text, "li"));
		isNotReady = false;
		return this;
	}

	
	public String build() {
		if(!isDocumentClosed){
			closeDocument();
		}
		return sBuilder.toString();
	}
	
	public String saveToFile(String fileName){
		File file = new File(fileName+".html");
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter(new FileWriter(file));
		    writer.write(build());
		} catch (IOException e) {
			throw new IllegalArgumentException("Plik nie został utworzony, podaj inną nazwę pliku.", e);
		} finally {
		    if (writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					throw new IllegalArgumentException("Plik nie został utworzony, podaj inną nazwę pliku.", e);
				}
		    }
		}
		return sBuilder.toString();
	}
	
	private void closeDocument(){
		if(isListStarted){
			sBuilder.append(closeTag("ul"));
			isListStarted = false;
		}
		sBuilder
		.append(closeTag("BODY"))
		.append(closeTag("HTML"));
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
	
	private String prepareChapterTitle(String title){
		if(isNumerated){
			title = addChapterNumber(title);
		}
		return serroundTag(title, "h".concat(String.valueOf(poziom+2)));
	}
	
	private String serroundTag(String value, String tagName){
		return openTag(tagName)
				.concat(value)
				.concat(closeTag(tagName));
	}
	
	private String openTag(String tagName){
		return "<".concat(tagName).concat(">");
	}
	
	private String closeTag(String tagName){
		return "<".concat(tagName).concat("/>\n");
	}
	
	private String addChapterNumber(String title){
		return getChapterNumber().concat(". ").concat(title);
	}
	
	private String getChapterNumber(){
		return String.valueOf(chapterNumber[poziom]);
	}
	
	private void incrementChapterNumber(){
		chapterNumber[poziom]++;
	}

}
