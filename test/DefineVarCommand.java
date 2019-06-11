package test;

import java.lang.reflect.Parameter;

public class DefineVarCommand implements Command {
	@SuppressWarnings("null")
	public DefineVarCommand() {
	
	}
	
	@Override
	public double doCommand(String[] parameters, int commandIndex) {
		StringBuilder sb1 = new StringBuilder();
		for(String s:parameters) {
			sb1.append(s+" ");
		}
		System.out.println("parameter is: "+sb1.toString());
	    //case's: var x || var y=x+3 || var y=3
        if(parameters.length==2)  {
        	//case: var x
        	if(parameters[1].length()==1) {
        		MyInterpreter.symbolTable.put(parameters[1], null);
        	}
        	
        	//case's: var y=x+3 , var y=3
        	else {
        		String[] strWithoutVAR = new String[1];
        		strWithoutVAR[0]= parameters[1];
        		SetVarCommand.handelEquasionAndInsertIntoSymbolTable(strWithoutVAR);
        	}
        }
		
        // with path
        // var a = bind x
        else if (parameters.length > 2) {
            if (parameters[commandIndex+3].equals("bind")) {
                MyInterpreter.varToPathMap.put(parameters[commandIndex+1], parameters[commandIndex+4]);
                MyInterpreter.pathToVarMap.put(parameters[commandIndex+4], parameters[commandIndex+1]);
                                    
                if (MyInterpreter.pathToDoubleValueTable.containsKey(parameters[commandIndex+4])) {
                	MyInterpreter.symbolTable.put(parameters[commandIndex+1],MyInterpreter.pathToDoubleValueTable.get(parameters[commandIndex+4]));
                }
                else {
                	System.out.println("i intialize you!");
                	MyInterpreter.symbolTable.put(parameters[commandIndex+1], 0.0);
                }
            }
            //case's: var y = x+3 || var y = x + 3
            else {
            	StringBuilder sb = new StringBuilder();
            	//String[] strWithoutVAR = new String[1];
            	for(int i=1; i<parameters.length;i++) {
            		sb.append(parameters[i]+ ",");
        	}
            	System.out.println("line is: "+ sb.toString());
            	String[] line = sb.toString().split(",");
            	//for(String s : line) { System.out.println(s); }
            	SetVarCommand.handelEquasionAndInsertIntoSymbolTable(line);
            	}
        }
        return parameters.length;
	}
}