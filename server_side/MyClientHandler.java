package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class MyClientHandler implements ClientHandler{
	
	Solver<Searchable<Position>, String> s; 
	CacheManager<Searchable<Position>, String> cm;
	
	public MyClientHandler() { 
		cm=new FileCacheManager<>();
		s = new SolverSearcher(new BFS<Position>());
	}

	@Override
	public void handleClient(InputStream inFile, OutputStream outFile) {

		BufferedReader reader=null;
		PrintWriter writer=null;
		reader = new BufferedReader(new InputStreamReader(inFile));
		writer= new PrintWriter(outFile);
		String line = null;
		List<String> stringList = new ArrayList<String>();
	
		try {
			while(!(line = reader.readLine()).equals("end")) {
				stringList.add(line);
				System.out.println(line);
			}
					
			Position p1 = stringToPosition(reader.readLine());
			Position p2 = stringToPosition(reader.readLine());
			
			MatrixSearchable matSearch= new MatrixSearchable(stringToMatrix(stringList), p1, p2);
			    
			if(cm.solvedAlready(matSearch)){
				writer.println(cm.GetSoloutin(matSearch));
				writer.flush();
			}	 
			else {
				cm.SaveSoloution(matSearch,s.solve(matSearch));
				writer.println(cm.GetSoloutin(matSearch));
				writer.flush();
			}			
			
			//matSearch.print();
			writer.println(s.solve(matSearch));
			writer.flush();
			
		} catch (IOException e) {e.printStackTrace();}
		try {
			reader.close();
		} catch (IOException e) {e.printStackTrace();}
		writer.close();
	}
	
	//build matrix:
	public double[][] stringToMatrix(List<String> stringlist){
		String[] str= stringlist.get(0).split(",");
		int col = str.length;
		int row = stringlist.size();
		double[][] mat=new double[row][col];
		
		for(int i=0;i<row;i++)
		{
			String[]temp = stringlist.get(i).split(",");
			for(int j=0;j<col;j++)
			{
				mat[i][j]=Double.parseDouble(temp[j]);
			}
		}
		return mat;
	}
	
	//build position
	public Position stringToPosition(String line)
	{
		String[] str=line.split(",");
		int x = Integer.parseInt(str[0]);
		int y= Integer.parseInt(str[1]);
		return new Position(x,y);
	}	
}