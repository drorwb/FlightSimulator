package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTrain {

	public static void main(String[] args) {
		//initializeMaps();
        try {
            BufferedReader scriptReader=new BufferedReader(new FileReader("script.txt"));
            String line;
            List<String> lines=new ArrayList<>();
            while((line=scriptReader.readLine())!=null){
                lines.add(line);
            }
            scriptReader.close();
            MyInterpreter.interpret(lines.toArray(new String[1]));
        } catch (FileNotFoundException e) {} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
//	public static void initializeMaps() {
//		MyInterpreter.pathToVarMap.put("/instrumentation/airspeed-indicator/indicated-speed-kt" , "indicated-speed-kt");
//		MyInterpreter.varToPathMap.put("indicated-speed-kt" , "/instrumentation/airspeed-indicator/indicated-speed-kt");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/altimeter/indicated-altitude-ft" , "indicated-altitude-ft");
//		MyInterpreter.varToPathMap.put("indicated-altitude-ft" , "/instrumentation/altimeter/indicated-altitude-ft");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/altimeter/pressure-alt-ft" , "pressure-alt-ft");
//		MyInterpreter.varToPathMap.put("pressure-alt-ft" , "/instrumentation/altimeter/pressure-alt-ft");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/attitude-indicator/indicated-pitch-deg" , "indicated-pitch-deg");
//		MyInterpreter.varToPathMap.put("indicated-pitch-deg" , "/instrumentation/attitude-indicator/indicated-pitch-deg");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/attitude-indicator/indicated-roll-deg" , "indicated-roll-deg");
//		MyInterpreter.varToPathMap.put("indicated-roll-deg" , "/instrumentation/attitude-indicator/indicated-roll-deg");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/attitude-indicator/internal-pitch-deg" , "internal-pitch-deg");
//		MyInterpreter.varToPathMap.put("internal-pitch-deg" , "/instrumentation/attitude-indicator/internal-pitch-deg");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/attitude-indicator/internal-roll-deg" , "internal-roll-deg");
//		MyInterpreter.varToPathMap.put("internal-roll-deg" , "/instrumentation/attitude-indicator/internal-roll-deg");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/encoder/indicated-altitude-ft" , "indicated-altitude-ft");
//		MyInterpreter.varToPathMap.put("indicated-altitude-ft" , "/instrumentation/encoder/indicated-altitude-ft");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/encoder/pressure-alt-ft" , "pressure-alt-ft");
//		MyInterpreter.varToPathMap.put("pressure-alt-ft" , "/instrumentation/encoder/pressure-alt-ft");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/gps/indicated-altitude-ft" , "indicated-altitude-ft");
//		MyInterpreter.varToPathMap.put("indicated-altitude-ft" , "/instrumentation/gps/indicated-altitude-ft");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/gps/indicated-ground-speed-kt" , "indicated-ground-speed-kt");
//		MyInterpreter.varToPathMap.put("indicated-ground-speed-kt" , "/instrumentation/gps/indicated-ground-speed-kt");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/gps/indicated-vertical-speed" , "indicated-vertical-speed");
//		MyInterpreter.varToPathMap.put("indicated-vertical-speed" , "/instrumentation/gps/indicated-vertical-speed");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/heading-indicator/indicated-heading-deg" , "indicated-heading-deg");
//		MyInterpreter.varToPathMap.put("indicated-heading-deg" , "/instrumentation/heading-indicator/indicated-heading-deg");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/magnetic-compass/indicated-heading-deg" , "indicated-heading-deg");
//		MyInterpreter.varToPathMap.put("indicated-heading-deg" , "/instrumentation/magnetic-compass/indicated-heading-deg");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/slip-skid-ball/indicated-slip-skid" , "indicated-slip-skid");
//		MyInterpreter.varToPathMap.put("indicated-slip-skid" , "/instrumentation/slip-skid-ball/indicated-slip-skid");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/turn-indicator/indicated-turn-rate" , "indicated-turn-rate");
//		MyInterpreter.varToPathMap.put("indicated-turn-rate" , "/instrumentation/turn-indicator/indicated-turn-rate");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/instrumentation/vertical-speed-indicator/indicated-speed-fpm" , "indicated-speed-fpm");
//		MyInterpreter.varToPathMap.put("indicated-speed-fpm" , "/instrumentation/vertical-speed-indicator/indicated-speed-fpm");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/controls/flight/aileron" , "aileron");
//		MyInterpreter.varToPathMap.put("aileron" , "/controls/flight/aileron");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/controls/flight/elevator" , "elevator");
//		MyInterpreter.varToPathMap.put("elevator" , "/controls/flight/elevator");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/controls/flight/rudder" , "rudder");
//		MyInterpreter.varToPathMap.put("rudder" , "/controls/flight/rudder");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/controls/flight/flaps" , "flaps");
//		MyInterpreter.varToPathMap.put("flaps" , "/controls/flight/flaps");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/controls/engines/engine/throttle" , "throttle");
//		MyInterpreter.varToPathMap.put("throttle" , "/controls/engines/engine/throttle");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/engines/engine/rpm" , "rpm");
//		MyInterpreter.varToPathMap.put("rpm" , "/engines/engine/rpm");
//		//---------------------------------------------------------------------------------------------------//
//		MyInterpreter.pathToVarMap.put("/controls/flight/speedbrake" , "speedbrake");
//		MyInterpreter.varToPathMap.put("speedbrake" , "/controls/flight/speedbrake");
//	}

}
