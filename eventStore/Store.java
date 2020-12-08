package eventStore;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;


public class Store implements EventStore, EventIterator {
	
	private Vector<Event> storage;
	private int cursor;
	private int lastRet = -1;
	
	public Store() {
		this.storage = new Vector<Event>();
	}
	
	
	public Vector<Event> getStorage() {
		return this.storage;
	}

	public int getCursor() {
		return this.cursor;
	}
	
	@Override
	public void insert(Event event) {			
		this.storage.add(event);			
	}

	@Override
	public void removeAll(String type) {
		for (int i = this.storage.size()-1; i >= 0; i--) {
			if(this.storage.elementAt(i).type().equals(type)) {
				this.storage.remove(i);
			}
		}
	}

	@Override
	public EventIterator query(String type, long startTime, long endTime) {
		Store store = new Store();
		for (int i = 0; i < this.storage.size(); i++) {
			if (this.storage.elementAt(i).type().equals(type) 
					&& this.storage.elementAt(i).timestamp() >= startTime 
					&& this.storage.elementAt(i).timestamp() < endTime) {
				store.insert(this.storage.elementAt(i));
			}
		}
		return store;
		
	}


	@Override
	public boolean moveNext() {
		return this.cursor != this.storage.size();
	}


	@Override
	public Event current() {
		synchronized (Store.this) {
			int i = this.cursor;
			if (i >= this.storage.size())
				throw new NoSuchElementException();
			this.cursor = i + 1;
			return this.storage.elementAt(lastRet = i);
		}
	}


	@Override
	public void remove() {
		if (this.lastRet == -1) {
			throw new IllegalStateException();
		}
		synchronized (Store.this) {
			this.storage.remove(this.lastRet);
		}
		this.cursor = this.lastRet;
		this.lastRet = -1;
	}

}
