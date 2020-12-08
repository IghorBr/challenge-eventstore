package eventStore;

/*
 * Test with two threads, where some events are inserted and the other thread will exclude some of the; 
*/

public class Test2 {

	static Store store = new Store();
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(thread1).start();
		//This sleep method is to grant that the events will be stored in the Store before it query
		//Otherwise, it might print anything, because the store is empty
		Thread.sleep(5000);
		new Thread(thread2).start();
	}
	
	private static Runnable thread1 = new Runnable() {
		public void run() {
			try {
				Event e1 = new Event("data", 50);
				Event e2 = new Event("stop", 21);
				Event e3 = new Event("data", 17);
				Event e4 = new Event("span", 33);
				Event e5 = new Event("span", 01);
				Event e6 = new Event("data", 10);
				
				store.insert(e1);
				store.insert(e2);
				store.insert(e3);
				store.insert(e4);
				store.insert(e5);
				store.insert(e6);
				
			} catch (Exception e) {}
		}
	};
	
	private static Runnable thread2 = new Runnable() {
		public void run() {
			try {
				store.removeAll("span");
				
				for (int i = 0; i < store.getStorage().size(); i++) {
					System.out.println("Type = " + store.getStorage().elementAt(i).type());
					System.out.println("Timestamp = " + store.getStorage().elementAt(i).timestamp());
					System.out.println("____________________________");
				}
			} catch (Exception e) {}
		}
	};
}