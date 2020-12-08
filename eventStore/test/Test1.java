package eventStore;

import java.util.Iterator;
import java.util.Vector;

/*
 * Test with just one thread, where some events are inserted, excluded and queried
*/

public class Test1 {
	
	static Store store = new Store();	
	
	public static void main(String[] args){		
		new Thread(t1).start();
	}
	
	private static Runnable t1 = new Runnable() {
		public void run() {
			try {
				Event e1 = new Event("data", 50);
				Event e2 = new Event("stop", 21);
				Event e3 = new Event("data", 17);
				Event e4 = new Event("span", 33);
				Event e5 = new Event("span", 25);
				Event e6 = new Event("data", 10);
				
				store.insert(e1);
				store.insert(e2);
				store.insert(e3);
				store.insert(e4);
				store.insert(e5);
				store.insert(e6);
								
				store.removeAll("data");
				
				
				EventIterator ei = store.query("span", 20, 40);
				
				while(ei.moveNext()) {
					Event e = ei.current();
					System.out.println(e.type());
					System.out.println(e.timestamp());
					ei.remove();
				}
		

				
			} catch (Exception e) {
				System.out.println("cursed");
			}
			
		}		
	};
}
