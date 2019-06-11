package server_side;

import java.util.ArrayList;
import java.util.List;

public class MatrixSearchable implements Searchable<Position> {

	double[][] mat;
	Position initialState;
	Position goalState;
	
	public MatrixSearchable(double[][] mat,Position initialPosition, Position goalPosition) {
		initialState= initialPosition;
		goalState = goalPosition;
		this.mat=mat;
	}

	@Override  
	public State<Position> getInitialState() {
		return new State<Position>(initialState, mat[initialState.getX()][initialState.getY()], null);
	}

	@Override
	public boolean isGoalState(State<Position> state) {
		return goalState.equals(state.getState());
	}
	
	//calculate the possible moves including costs
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		ArrayList<State<Position>> neighbours = new ArrayList<>();
		getMatrixMoves(s.getState())	//calculate the possible moves
			.forEach(position -> neighbours. //add to neighbors list all s' successors and their new cost (calculate by s' cost+ position's cost
					add(new State<Position>(position, s.getCost() + 
							mat[position.getX()][position.getY()], s)));
		return neighbours;
		
	}
	
	//calculate the possible moves
	public List<Position> getMatrixMoves(Position p) {
		ArrayList<Position> moves = new ArrayList<>();
		int x = p.getX();
		int y = p.getY();

		if (isValid(x + 1, y))
			moves.add(new Position(x + 1, y));
		if (isValid(x, y + 1))
			moves.add(new Position(x, y + 1));
		if (isValid(x - 1, y))
			moves.add(new Position(x - 1,y));
		if (isValid(x, y - 1))
			moves.add(new Position(x, y - 1));

		return moves;
	}
	
	public boolean isValid(int x, int y) {
		return x>=0 && x < mat.length && y>=0 && y < mat[x].length;
	}
	
//	public void print() {
//		for(double [] a : mat) {
//			for( double b : a)
//				System.out.print(b+",");
//			System.out.println();
//		}	
//	}
}
