package server_side;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class BFS<T> extends commonSearcher<T> {
		
	@Override
	public List<State<T>> search(Searchable<T> s) {  
		//make a priority queue of states to be evaluated (handled)
		//in this point we only add the Initial State to the queue
		openList = new PriorityQueue<>((s1,s2)-> (int)(s1.getCost() - s2.getCost()));
		addToOpenList(s.getInitialState());	
		//a set of states already handled
		HashSet<State<T>> closedSet = new HashSet<State<T>>(); 
		State<T> n = new State<T>();
		
		while(openList.size()>0) {
			n = popOpenList(); //remove the best node from the PriorityQueue	      
			closedSet.add(n); //so we won't check n again			      
			if(s.isGoalState(n))	  
				return n.backTrace();	
			//creates n successors
			ArrayList<State<T>> successors = s.getAllPossibleStates(n);
			for(State<T> state:successors) //for each successor
				{
					if(!closedSet.contains(state) && !openList.contains(state)){
						addToOpenList(state);
					}
					else
					{	
						if(!openList.contains(state))
							addToOpenList(state);
				}
			}				
	}
		return null;
}

	@Override
	public int getNumberOfNodesEvaluated() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
