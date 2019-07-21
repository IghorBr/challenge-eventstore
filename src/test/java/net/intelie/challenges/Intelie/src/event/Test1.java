package event;

/* Test class 2
 * This class uses thread to make concurrency programing
 * */

public class Test1 {
	
	public static void main(String[] args) {
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
		
		//Insert some events in the ArrayList
		s.insert(a);
		s.insert(b);
		s.insert(d);
		s.insert(c);
		s.insert(e);
		s.insert(g);
		s.insert(k);
		s.insert(l);
		s.insert(m);
		
		//Print the events in the ArrayList
		System.out.println("These are the events that are in the ArrayList: ");
		s.printEvents();
		
		
		//These are some operations, just to test the thread-safety
		s.removeAll("Tech");
		s.insert(f);
		s.insert(h);
		
		//Print the events in the ArrayList
		System.out.println("These are the events that are in the ArrayList after some operations: ");
		s.printEvents();
		
		s.insert(j);
		s.insert(i);
		s.removeAll("Game");
		
		//Print the events in the ArrayList
		System.out.println("These are the events in the ArrayList after the final operations");
		s.printEvents();		
		
	}

}
