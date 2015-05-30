package zaslonad.builderPatern.test;

import zaslonad.builderPatern.builder.impl.TxtBuilder;
import zaslonad.builderPatern.director.LoremIpsumDocumentDirector;

public class TestMain {

	public static void main(String[] args){
		
		test1();
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

}
