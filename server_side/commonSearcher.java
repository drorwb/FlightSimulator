package server_side;

import java.util.PriorityQueue;

public abstract class commonSearcher<T> implements Searcher<T> {
	
	protected PriorityQueue<State<T>> openList; 	
	private int evaluatedNodes;  				   
	
	public commonSearcher() {
		evaluatedNodes=0;
	}
	protected State<T> popOpenList() {			
		evaluatedNodes++;
		return openList.poll();
	}
	protected void addToOpenList(State<T> s) {
		openList.add(s);
	}

	

}
