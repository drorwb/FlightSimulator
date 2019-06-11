package test;

import ShuntingYard.ShuntingYardAlg;

//add return value

public class SetVarCommand implements Command {

	@SuppressWarnings("null")
	@Override
	public double doCommand(String[] parameters, int commandIndex) {
		StringBuilder sb = new StringBuilder();
		for(String s:parameters) {
			sb.append(s+" ");
		}
		System.out.println("parameter is: "+sb.toString());
        //cases: "x="+rand || "x=y" || "x=3" || "x=y+3"
        if (parameters.length == 1) {
        	handelEquasionAndInsertIntoSymbolTable(parameters);
        } 
        //case: "x=" "y" "+" "3" || "x" "=" "y" "+" "3"
        else {
        	boolean isBind = false;
        	for(int i=0; i<parameters.length;i++) {
        		if(parameters[i].equals("bind")){
        			isBind= true;
        			break;
        		}
        	}
        	//case's: "x=" "y" "+" "3" || "x" "=" "y" "+" "3"
        	if(isBind==false) {	
        		System.out.println("enterd herreee !!");
        		handelEquasionAndInsertIntoSymbolTable(parameters);
        	}
        	// X = bind simy
        	else {
        		System.out.println("enterd !!");
        		if(!MyInterpreter.varToPathMap.get(parameters[commandIndex]).equals(parameters[commandIndex+3])
        				|| !MyInterpreter.pathToVarMap.get(parameters[commandIndex+3]).equals(parameters[commandIndex])) {
        			System.out.println("enterd here but its not good!");
        		MyInterpreter.varToPathMap.put(parameters[commandIndex], parameters[commandIndex+3]);
        		MyInterpreter.pathToVarMap.put(parameters[commandIndex+3], parameters[commandIndex]);
        		}
                else {
                	MyInterpreter.symbolTable.put(parameters[commandIndex], MyInterpreter.pathToDoubleValueTable.get(parameters[commandIndex+3]));
                	 //MyInterpreter.commands.add("set " + MyInterpreter.varToPathMap.get(parameters[commandIndex]) + " " + MyInterpreter.pathToDoubleValueTable.get(parameters[commandIndex+3]));
                	System.out.println("Sending to simulator 1: "+"set " + MyInterpreter.varToPathMap.get(parameters[commandIndex]) + " " + MyInterpreter.pathToDoubleValueTable.get(parameters[commandIndex+3]));
                	 ConnectCommand.out.println("set " + MyInterpreter.varToPathMap.get(parameters[commandIndex]) + " " + MyInterpreter.pathToDoubleValueTable.get(parameters[commandIndex+3]));
                }
        	}
        }
	return parameters.length;
	}
	
	public static void handelEquasionAndInsertIntoSymbolTable(String[] str) {
		String var =str[0];
		StringBuilder sb= new StringBuilder();
		//from "x=y+3" to --> "y+3"
		for (int i = 2; i< str.length ; i++) {
			sb.append(str[i]);
		}
		String equation= sb.toString();
		System.out.println("equation is: "+equation);
		double res = ShuntingYardAlg.calc(equation);
		MyInterpreter.symbolTable.put(var, res);
		if(MyInterpreter.varToPathMap.containsKey(var)) {
			String path = MyInterpreter.varToPathMap.get(var);
			MyInterpreter.pathToDoubleValueTable.replace(path, MyInterpreter.pathToDoubleValueTable.get(path),res);
			//MyInterpreter.commands.add("set " + path + " " + res);
			System.out.println("Sending to simulator 2: "+"set " + path + " " + res);
			ConnectCommand.out.println("set " + path + " " + res);
			MyInterpreter.varToPathMap.forEach((v,p)->{if(MyInterpreter.varToPathMap.get(v).equals(path)) {
				MyInterpreter.symbolTable.put(v,res);
				
			}});
		}
	}
}