package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class EventQueue {

	private List<LinkedList<Event>> queues;

	public EventQueue() {

		queues = new ArrayList<LinkedList<Event>>();
		
		queues.add(new LinkedList<Event>());
		queues.add(new LinkedList<Event>());
		queues.add(new LinkedList<Event>());
	}

	public EventQueue(int numQueues) {
		for (int i = 0; i < numQueues; i++) {
			queues.add(new LinkedList<Event>());
		}
	}

	public void addEvent(Event event) {
		queues.get(1).add(event);
	}

	public void addEvent(Event event, int queueNumber) {
		queues.get(queueNumber).add(event);
	}

	public Event removeEvent() {
		return queues.get(0).remove();
	}

	public Event removeEvent(int queueNumber) {
		return queues.get(queueNumber).remove();
	}

	public boolean hasEvents() {
		for (LinkedList<Event> list : queues) {
			if (!list.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<EventListener> getEventListeners(String eventName){
		if(myEventListeners.get(eventName) == null){
			return new ArrayList<EventListener>();
		}
		
		return myEventListeners.get(eventName);
	}
	
	public Object[] getNextEvents() {
		ArrayList<Event> list = new ArrayList<Event>();
		for (Queue<Event> q : queues) {
			list.add(q.poll());
		}
		return list.toArray();
	}

	public List<LinkedList<Event>> getQueues(){
		return queues;
	}
	
	public void swapQueues(int q1, int q2) {
		queues.get(q2).addAll(0, queues.get(q1));
		queues.get(q1).clear();
		queues.set(q1, queues.get(q2));
		queues.get(q2).clear();
	}

	public Map<String, ArrayList<EventListener>> getEventListenerMap(){
		return myEventListeners;
	}
	
	// public void addEvent(Event e, int queueNumber){
	// if(queueNumber >= queues.length-1){
	// Queue<Event>[] temp = new Queue[queueNumber-queues.length+1];
	// temp[queueNumber-queues.length+1] = new LinkedList<Event>();
	// temp[queueNumber-queues.length+1].add(e);
	// }
	// queues[queueNumber].add(e);
	// }

}
