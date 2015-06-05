package zaslonad.builderPatern.test;

import zaslonad.builderPatern.builder.impl.HtmlBuilder;
import zaslonad.builderPatern.builder.impl.TxtBuilder;
import zaslonad.builderPatern.director.LoremIpsumDocumentDirector;
import zaslonad.builderPatern.director.SampleLoremIpsumDocumentDirector;

public class TestMain {

	public static void main(String[] args){
		
		//test1();
		//test2();
		testHtmlBuilderWitchLoremIpsumDirector();
	}
	private static void test2(){
		LoremIpsumDocumentDirector director = new LoremIpsumDocumentDirector(new TxtBuilder(false));
		director.construct();
	}
	private static void test1(){
		TxtBuilder builder = new TxtBuilder(false);
		TestDocumentDirector director = new TestDocumentDirector(builder);
		director.construct();
		System.out.print(builder.build());
	}
	private static void testHtmlBuilderWitchLoremIpsumDirector(){
		HtmlBuilder builder = new HtmlBuilder(true);
		SampleLoremIpsumDocumentDirector director = new SampleLoremIpsumDocumentDirector(builder);
		director.construct();
		builder.saveToFile("test");
		System.out.print(builder.build());
	}

}
