package zaslonad.builderPatern.director;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import zaslonad.builderPatern.builder.DocumentBuilder;

public class ConsoleDocumentDirector {
	DocumentBuilder builder;
	Map<Integer, List<Integer>> stateMap;
	Map<Integer, String> menuMap;
	Integer[] rozdzialy = {0,0,0,0};
	
	public ConsoleDocumentDirector(DocumentBuilder builder){
		this.builder = builder;
		stateMap = constrctStateMap();
		menuMap = constructMenuMap();
	}
	
	public String construct(){
		int ileAutorow = 0;
		int state = 1;
		MenuEnum choice ;
		do{
			choice = MenuEnum.fromInteger(showMenu(state));
			System.out.println(">>> Wybór: "+menuMap.get(choice.getCode())+"<<<");
			switch(choice){
			case ADD_AUTOR:
				builder.addAuthor(getString("Wprowadź autora dokumentu"));
				ileAutorow++;
				if(ileAutorow > 2){
					state = 2;
				}
				break;
			case ADD_LIST_ELEM:
				builder.addListElement(getString("Wprowadź element listy"));
				state = 6;
				break;
			case ADD_PARAGRAF:
				builder.addParagraph(getString("Wprowadz tekst paragrafu"));
				state = 6;
				break;
			case ADD_PODROZDZIAL:
				builder.setChaprerLevelDown();
				builder.addChapter("Wprowadź tytół podrozdziału");
				state = 4;
				break;
			case ADD_ROZDZIAL:
				break;
			case SET_TYTUL:
				builder.setDocumentTitle("Wprowadź tytuł dokumentu");
				break;
			case UP_POZIOM:
				builder.setChapterLevelUp();
				break;
			case WYSWIETL:
				break;
			default:
				break;
			}
			
		}while(choice.equals(MenuEnum.WYSWIETL));
		
		return "Koniec";
		//return builder.build();
	}
	private String getString(String text) {
		Scanner in = new Scanner(System.in);
		System.out.println(text);
		String input;
		do{
			System.out.print(": ");
			input = in.nextLine();
		}while(!valid(input));
		return input;
	}

	private boolean valid(String input) {
		if(input == null){
			System.err.print("NULL! Spróbuj ponownie!");
			return false;
		}
		if(input.trim().length() < 1){
			System.err.print("Pusty string! Spróbuj ponownie!");
			return false;
		}
		return true;
	}

	private int showMenu(int state){
		Scanner in = new Scanner(System.in);
		int num = 0;
		System.out.println("\n\n--------------------------");
		System.out.println("Wybierz co chcesz zrobić:");
		int licznik = 1;
		boolean notFirstChoice = false;
		List<Integer> choices = stateMap.get(state);
		for(Integer item : choices){
			System.out.println("\t"+licznik+"\t"+menuMap.get(item));
			licznik++;
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
		}while(num < 1 || num > choices.size());
		//in.close();
		return choices.get(num-1);
	}
	private Map<Integer, List<Integer>> constrctStateMap(){
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
//		map.put(0, createList(-2,-1));			// wybór numerowania
		map.put(1, createList(1,2));			// dodawanie autorów lub tytułu
		map.put(2, createList(2));				// po dodaniu trzech autorów
		map.put(3, createList(3,4,5));			// po wprowadzeniu tytułu
		map.put(4, createList(3,4,6));			// po dodaniu rozdziału
		map.put(5, createList(3,4));			// po dodaniu rozdziału na najniższym poziomie
		map.put(6, createList(3,4,5,6,7));		// po dodaniu treści
		map.put(7, createList(3,4,5,6,7));		// po dodaniu treści na najniższym poziomie
		map.put(100, createList(1,2,3,4,5,6,7,10)); //ALL
		return map;
	}

	private enum MenuEnum{
//		Z_NUMEROWANIEM(-2),
//		BEZ_NUMEROWANIA(-1),
		ADD_AUTOR(1),
		SET_TYTUL(2),
		ADD_PARAGRAF(3),
		ADD_LIST_ELEM(4),
		ADD_ROZDZIAL(5),
		ADD_PODROZDZIAL(6),
		UP_POZIOM(7),
		WYSWIETL(10);
		
		private int code;
		 
	    MenuEnum(int code) {
	        this.code = code;
	    }
	 
	    public int getCode() {
	        return code;
	    }
	    
	    public static MenuEnum fromInteger(int x) {
	        switch(x) {
//	        case -2:
//	            return Z_NUMEROWANIEM;
//	        case -1:
//	            return BEZ_NUMEROWANIA;
	        case 1:
	            return ADD_AUTOR;
	        case 2:
	            return SET_TYTUL;
	        case 3:
	            return ADD_PARAGRAF;
	        case 4:
	            return ADD_LIST_ELEM;
	        case 5:
	            return ADD_ROZDZIAL;
	        case 6:
	            return ADD_PODROZDZIAL;
	        case 7:
	            return UP_POZIOM;
	        case 10:
	            return WYSWIETL;
	        }
	        return null;
	    }
	}
	
	private Map<Integer, String> constructMenuMap(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(-2, "Utórz dokument z numerowaniem rozdziałów");
		map.put(-1, "Utórz dokument bez numerowania rozdziałów");
		map.put(1, "Dodaj autora dokumentu");
		map.put(2, "Wprowadz tytuł dokumentu");
		map.put(3, "Dodaj paragraf");
		map.put(4, "Dodaj element listy");
		map.put(5, "Dodaj kolejny rozdział");
		map.put(6, "Dodaj podrozdział do tworzonego rozdziału");
		map.put(7, "Zakończ dodawanie rozdziałów na tym poziomie");
		map.put(10, "Wyświetl dokument");
		return map;
	}
	
	private List<Integer> createList(Integer... list){
		List<Integer> temp = new ArrayList<Integer>();
		for(Integer item : list){
			temp.add(item);
		}
		return temp;
	}
}
