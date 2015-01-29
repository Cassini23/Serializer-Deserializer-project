package reflection.driver;

import java.util.ArrayList;

import reflection.serDeser.Deserialize;
import reflection.serDeser.Serializer;
import reflection.serDeser.Compare;
import reflection.util.*;
import reflection.serDeser.deserializeStrategy;

public class Driver {
	public static void main(String[]args){
		//begin
		int arg_count = args.length;
		for (String s : args){
			System.out.println("input: "+s);
		}
		String inputFile = args[0];
		String outputFile = args[1];
		int debug_value = Integer.parseInt(args[2]);
		ArrayList<Object> result_list = new ArrayList<Object>();
		
		try {
			Debug.setDEBUG_VALUE(Integer.parseInt(args[2]));
		} 
		catch (NumberFormatException e) {
			System.err.println("Debug value must be integer");
			System.exit(1);
		} 
		finally {
		}
		
		if (arg_count != 3){
			System.err.println("Input arguments must be <input file> <output file> debug_value.....exiting!");
			System.exit(1);
		}
		if(inputFile == null){
			System.err.println("Input file not found .....exiting");
			System.exit(1);
		}
		if(outputFile == null){
			System.err.println("Output file not found.....exiting");
			System.exit(1);
		}
		if(debug_value < 0 || debug_value > 4){
			System.err.println("debug_value must be between 0 and 4....exiting");
			System.exit(1);
		}
		System.out.println("In main");
		
		deserializeStrategy strategy = new Deserialize();
		result_list = strategy.deSerializeClass(inputFile, strategy);

		Serializer s = new Serializer(result_list);
		s.serializeClass(outputFile);
		
		Compare comp = new Compare();
		
		comp.compareObject(result_list);
		System.out.println("Exiting main");
	}
}
