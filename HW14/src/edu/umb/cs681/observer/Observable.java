package edu.umb.cs681.observer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
	private LinkedList<Observer<T>> observers = new LinkedList<>();
	private ReentrantLock lockObs = new ReentrantLock();
	
	public void addObserver(Observer<T> o) {
		lockObs.lock();
		try {
			observers.add(o);
		} finally {
			lockObs.unlock();
		}
	}

	public void clearObservers() {
		lockObs.lock();
		try {
			observers.clear();
		} finally {
			lockObs.unlock();
		}
		
	}
	public List<Observer<T>> getObservers(){
		return observers;
	}
	
	public int countObservers() {
		return observers.size();
		
	}
	public void removeObserver(Observer<T> o) {
		lockObs.lock();
		try {
			observers.remove(o);
		} finally {
			lockObs.unlock();
		}
	}

	public void notifyObservers(T event) {
		lockObs.lock();
		try {
		observers.forEach( (observer)->{observer.update(this, event);} );
		} finally {
			lockObs.unlock();
		}
	}
	
}
