package event;

/*
 * TEST CLASS 1
 * THIS'S A CLASS USED FOR A SIMPLE TEST
 * THIS CLASS DOESN'T HAVE ANY CONCURRENCY PROGRAMING AND THREADS  
 * */


public class Test {

	public static void main(String[] args) throws Exception {
		/* THESE ARE SOME EVENTS, THE TYPE ARE JUST RANDOM WORDS, AND THE TIMESTAMP WERE CHOSEN ARBITRARILY
		 * */
		Event a = new Event("Tech", 41);
		Event b = new Event("Tech", 21);
		Event c = new Event("Tech", 10);
		
		Event d = new Event("RPG", 150);
		Event e = new Event("RPG", 12);
		Event f = new Event("RPG", 300);
		
		Event g = new Event("Book", 1);
		Event h = new Event("Book", 23);
		Event i = new Event("Book", 1000);
		
		Event j = new Event("Game", 141);
		Event k = new Event("Game", 41);
		Event l = new Event("Game", 50);
		Event m = new Event("Game", 90);
		
		/* THIS IS THE CLASS THAT IMPLEMENTS THE REQUIRED METHODS
		 * IN THE EVENTSTORE INTERFACE
		 * */
		
		Store s = new Store();
		
		//INSERTING THE EVENTS INTO THE ARRAYLIST
		s.insert(a);
		s.insert(b);
		s.insert(d);
		s.insert(c);
		s.insert(e);
		s.insert(g);
		s.insert(f);
		s.insert(h);
		s.insert(j);
		s.insert(i);
		s.insert(k);
		s.insert(l);
		s.insert(m);
		
		//JUST PRINT THE EVENTS IN THE ARRAYLIST
		s.printEvents();
		
		//REMOVE THE EVENTS THAT HAS TYPE EQUALS TO TECH
		s.removeAll("Tech");
		
		//PRINT THE EVENTS IN THE ARRAYLIST 
		s.printEvents();		
	}
}
