package zaslonad.builderPatern.director;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import zaslonad.builderPatern.builder.DocumentBuilder;

public class LoremIpsumDocumentDirector {
	DocumentBuilder builder;
	Map<Integer, String> menuMap;
	Integer[] rozdzialy = {0,0,0,0};
	
	public LoremIpsumDocumentDirector(DocumentBuilder builder){
		this.builder = builder;
		menuMap = constructMenuMap();
	}
	
	public void construct(){
		MenuEnum choice ;
		do{
			choice = MenuEnum.fromInteger(showMenu());
			System.out.println(">>> Wybór: "+menuMap.get(choice.getCode())+"<<<");
			switch(choice){
			case ERROR_TEST_1:
				errorTest1();
				break;
			case ERROR_TEST_2:
				errorTest2();
				break;
			case PASS_TEST_1:
				passTest1();
				break;
			case PASS_TEST_2:
				passTest2();
				break;
			case PASS_TEST_3:
				passTest3();
				break;
			default:
				break;
			}
			
		}while(choice.getCode() != MenuEnum.EXIT.getCode());
	}

	private void passTest3() {
		// TODO Auto-generated method stub
		
	}

	private void passTest2() {
		// TODO Auto-generated method stub
		
	}

	private void passTest1() {
		// TODO Auto-generated method stub
		
	}

	private void errorTest2() {
		// TODO Auto-generated method stub
		
	}

	private void errorTest1() {
		// TODO Auto-generated method stub
		
	}

	private int showMenu(){
		Scanner in = new Scanner(System.in);
		int num = 0;
		System.out.println("\n\n--------------------------");
		System.out.println("Wybierz co chcesz zrobić:");
		boolean notFirstChoice = false;
		for(MenuEnum item : MenuEnum.values()){
			System.out.println(""+item.code+".\t"+menuMap.get(item.code));
		}
		
		do{
	        if(notFirstChoice){
	        	System.out.println("Kiepski wybór!");
	        }
			System.out.print("Twój wybór:");
			if (in.hasNextInt()) {
		        num = in.nextInt() ; 
		    } else {
		    	in.next();
		    }
			notFirstChoice = true;
		}while(num < 1 || num > 6);
		//in.close();
		return num;
	}

	private enum MenuEnum{
		PASS_TEST_1(1),
		PASS_TEST_2(2),
		PASS_TEST_3(3),
		ERROR_TEST_1(4),
		ERROR_TEST_2(5),
		EXIT(6);
		
		private int code;
		 
	    MenuEnum(int code) {
	        this.code = code;
	    }
	 
	    public int getCode() {
	        return code;
	    }
	    
	    public static MenuEnum fromInteger(int x) {
	        switch(x) {
	        case 1:
	            return PASS_TEST_1;
	        case 2:
	            return PASS_TEST_2;
	        case 3:
	            return PASS_TEST_3;
	        case 4:
	            return ERROR_TEST_1;
	        case 5:
	            return ERROR_TEST_2;
	        case 6:
	            return EXIT;
	        default:
	        	return null;
	        }
	    }
	}
	
	private Map<Integer, String> constructMenuMap(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Poprawny test nr 1");
		map.put(2, "Poprawny test nr 2");
		map.put(3, "Poprawny test nr 3");
		map.put(4, "Test z błędem nr 1");
		map.put(5, "Test z błędem nr 2");
		map.put(6, "Wyjście");
		return map;
	}
}
