package zaslonad.builderPatern.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class IPSUMLists {
	
	private static List<String> akapity;
	private static List<String> nazwiska;
	private static List<String> wersy;
	private Random generator;
	
	public IPSUMLists(){
		utworzAkapity();
		utworzNazwiska();
		utworzWersy();
		generator = new Random();
	}
	
	public String gerRandomWers(){
		return wersy.get(generator.nextInt(7));
	}
	
	public String gerRandomNazwisko(){
		return nazwiska.get(generator.nextInt(3));
	}
	
	public String gerRandomAkapit(){
		return akapity.get(generator.nextInt(7));
	}
	
	private void utworzAkapity(){
		akapity = new ArrayList<String>();
		akapity.add(IPSUM.akapit1);
		akapity.add(IPSUM.akapit2);
		akapity.add(IPSUM.akapit3);
		akapity.add(IPSUM.akapit4);
		akapity.add(IPSUM.akapit5);
		akapity.add(IPSUM.akapit6);
		akapity.add(IPSUM.akapit7);
	}

	
	private void utworzNazwiska(){
		nazwiska = new ArrayList<String>();
		nazwiska.add(IPSUM.name1);
		nazwiska.add(IPSUM.name2);
		nazwiska.add(IPSUM.name3);
	}
	
	private void utworzWersy(){
		wersy = new ArrayList<String>();
		wersy.add(IPSUM.wers1);
		wersy.add(IPSUM.wers2);
		wersy.add(IPSUM.wers3);
		wersy.add(IPSUM.wers4);
		wersy.add(IPSUM.wers5);
		wersy.add(IPSUM.wers6);
		wersy.add(IPSUM.wers7);
	}
}
