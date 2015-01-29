package reflection.serDeser;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.Field;

public class Serializer {
	
	private String openDP = "<DPSerialization>";
	private String closeDP = "</DPSerialization>";
	private String openCT = "<complexType";
	private String closeCT = "</complexType>";
	private String types= "xsi:type=";
	private String openTag ="<";
	private String closeTag =">";
	private String className ="";
	private FileWriter file;
	private BufferedWriter bufferOut;
	private ArrayList<Object> objects =  new ArrayList<Object>();
	
	//for reflection
	Field[] field;
	String fieldName;
	String tagName;
	
	SerializeTypes st = new SerializeTypes();
	StringBuffer writeStream = new StringBuffer();
	
	public Serializer(){
		
	}
	
	public Serializer(ArrayList<Object> listIn){
		objects = listIn;
	}
	
	/**
	 * serializes a class
	 * @param outputFile
	 */
	public void serializeClass(String outputFile){
		
		for(int i=0; i < objects.size();i++){
			serializeObject(outputFile, objects.get(i));
			if(i!=objects.size()-1)
			writeStream.append("\n");
		}
		try{
			bufferOut.write(writeStream.toString());
			bufferOut.close();
		}
		catch(IOException io){
			System.err.println("Could not close the file");
			System.exit(1);
		}
	}
	
	/**
	 * serializes an object passed to it
	 * @param outputFile
	 * @param obj
	 */
	public void serializeObject(String outputFile, Object obj) {
		// TODO Auto-generated method stub
		//Initializing writers
		int flag = 0;
		String eofl;
		try{
			file = new FileWriter(outputFile);	
			bufferOut = new BufferedWriter(file);
			//writing
				Object temp = obj;
				className = temp.getClass().getCanonicalName();
				field = temp.getClass().getDeclaredFields();
				
				writeStream.append(openDP);
				writeStream.append("\n");
				writeStream.append(" "+openCT);
				writeStream.append(" ");
				
				writeStream.append(types);
				writeStream.append('"');
				
				writeStream.append(className);
				writeStream.append('"');
				writeStream.append(closeTag);
				writeStream.append("\n");
				//individual data types
				
					for(int j=0 ;j<field.length;j++){
						fieldName = field[j].getName();
						if(fieldName.toLowerCase().contains("int")){
							tagName = st.serializeInt(field[j].getName(),temp);
						}
						else if(fieldName.toLowerCase().contains("string")){
							tagName = st.serializeString(field[j].getName(),temp);
						}
						else if(fieldName.toLowerCase().contains("double")){
							tagName = st.serializeDouble(field[j].getName(),temp);
						}
						else if(fieldName.toLowerCase().contains("long")){
							tagName = st.serializeLong(field[j].getName(),temp);
						}
						else if(fieldName.toLowerCase().contains("char")){
							tagName = st.serializeChar(field[j].getName(),temp);
						}
						else if(fieldName.toLowerCase().contains("float")){
							tagName = st.serializeFloat(field[j].getName(),temp);
						}
						//if(fieldName.toLowerCase().contains("short"))
						else{	
							tagName = st.serializeShort(field[j].getName(),temp);
						}
						writeStream.append("  "+tagName);
						writeStream.append("\n");
						tagName="";
					}
				//end of individual data types
				writeStream.append(" "+closeCT);
				writeStream.append("\n");
				writeStream.append(closeDP);
				//writeStream.append("\n");
		}
		catch(IOException io){
			System.err.println("Could not write to output file....exiting!");
			System.exit(1);
		}
	}
}
