package event;

import java.util.ArrayList;

public class Store implements EventStore{

	//ARRAYLIST THAT CONTAINS THE EVENTS
	private ArrayList<Event> eventArray;
	//INDEX OF THE ARRAY
	private int index;
	//VARIABLE THAT VERIFIES IF A METHOD HAS BEEN CALLED
	private static boolean test1 = false;
	//VARIABLE THAT CONTAINS THE RETURN OF THE METHOD
	private static boolean test2;
	
	public Store() {
		eventArray = new ArrayList<Event>();
		this.index = 0;
	}

	public ArrayList<Event> getEventArray() {
		return eventArray;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public static boolean isTest1() {
		return test1;
	}

	public static void setTest1(boolean test1) {
		Store.test1 = test1;
	}

	public static boolean isTest2() {
		return test2;
	}

	public static void setTest2(boolean test2) {
		Store.test2 = test2;
	}

	public synchronized void printEvents() {
		new Thread() {
			public void run() {
				for (int i = 0; i < eventArray.size(); i++) {
					System.out.println(eventArray.get(i).type());
					System.out.println(eventArray.get(i).timestamp());
					System.out.println();
				}
			}
		}.start();
	}

	@Override
	public synchronized void insert(Event event) {
		new Thread() {
			public void run() {
				eventArray.add(event);
			}
		}.start();
	}

	@Override
	public synchronized void removeAll(String type) {
		new Thread() {
			public void run() {
				ArrayList<Event> aux = new ArrayList<Event>();
				
				for (int i = 0; i < eventArray.size(); i++) {
					if (type.intern() != getEventArray().get(i).type()) {
						aux.add(getEventArray().get(i));
					}
				}
				
				eventArray.removeAll(eventArray);
				eventArray.addAll(aux);
			}
		}.start();
	}

	@Override
	public EventIterator query(String type, long startTime, long endTime) {
		//This method returns a instance of EventIterator
		//The instance that is returned is chosen based on the index
		//If the index is multiple of two, it will be returned the moveNext
		//If the index is multiple of three, it will be returned the current
		//Else, it will be returned the remove
		for (int i = 0; i < getEventArray().size(); i++) {
			if (type.intern() == getEventArray().get(i).type()) {
				if (getEventArray().get(i).timestamp() >= startTime && getEventArray().get(i).timestamp() < endTime) {
					if (i == getEventArray().size()) {
						return new EventIterator() {
							
							@Override
							public void close() throws Exception {
								throw new Exception("You exited the program and all the list was lost.");							
							}
							
							@Override
							public void remove() {
								if (isTest1() && !isTest2()) {
									throw new IllegalStateException("It is not possible to remove the current Event");
								}
								
								getEventArray().remove(getIndex());
							}
							
							@Override
							public boolean moveNext() {
								if (getIndex() == getEventArray().size() - 1) {
									//array's index
									setIndex(0);
									//variable that verifies if this method has been called
									setTest1(true);
									//variable that has the value of the return of the method
									//if this method returns TRUE, then test2 will receive TRUE
									//if this method returns FALSE, then test2 will receive FALSE
									setTest2(false);
									return false;
								}
								else {
									setIndex(getIndex() + 1);
									setTest1(true);
									setTest2(true);
									return true;
								}
							}
							
							@Override
							public Event current() {
								if (isTest1() && !isTest2()) {
									throw new IllegalStateException("It is not possible to acess the current Event");
								}
								
								return getEventArray().get(getIndex());
							}
						};
					}
				}
			}
		}
		return null;
	}
}
