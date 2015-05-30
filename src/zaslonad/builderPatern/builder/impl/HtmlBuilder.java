package zaslonad.builderPatern.builder.impl;

import zaslonad.builderPatern.builder.DocumentBuilder;

public class HtmlBuilder implements DocumentBuilder{
	
	StringBuilder sBuilder;
	int poziom;
	boolean isReady;
	boolean isTitleAdded;
	
	
	public HtmlBuilder(){
		sBuilder = new StringBuilder();
		poziom = 0;
		isReady = false;
		isTitleAdded = false;
		openDocument();
	}

	@Override
	public DocumentBuilder setDocumentTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentBuilder addAuthor(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentBuilder setChapterLevelUp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentBuilder setChaprerLevelDown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentBuilder addChapter(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentBuilder addParagraph(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentBuilder addListElement(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String build() {
		return sBuilder.toString();
	}
	
	private void openDocument(){
		//TODO:
	}
	private void closeDocument(){
		//TODO:
	}
	private String addHeaderTag(String text, int hTagNumber){
		closeDocument();
		return "<h"+hTagNumber+">"+text+"</"+hTagNumber+">";
	}

}
