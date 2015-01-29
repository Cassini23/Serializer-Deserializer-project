package reflection.serDeser;

//packages
import java.lang.Class;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
//import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import reflection.util.Logging;
import reflection.util.ResultInterface;
import reflection.util.Results;

//USING REFLECTION TO CREATE OBJECTS OF EITHER CLASS
public class Deserialize implements deserializeStrategy{
	
	Class c = null;
	Object obj = null;
	Method m[];
	private String fileName;
	private String className;
	private ArrayList<Object> objList;
	private BufferedReader buffer;
	
	//data types
	private String sInt = "xsd:int";
	private String sString = "xsd:string";
	private String sDouble = "xsd:double";
	private String sLong = "xsd:long";
	private String sChar = "xsd:char";
	private String sFloat = "xsd:float";
	private String sShort = "xsd:short";
	private String sClass1 = "reflection.util.MyAllTypesFirst";
	private String sClass2 = "reflection.util.MyAllTypesSecond";
	
	DeserializeTypes dt = new DeserializeTypes();
	
	public Deserialize(){
		objList = new ArrayList<Object>();
	}
	
	/**
	 * Opens file by the file name fileNameIn
	 * @param fileNameIn
	 */
	public void openFile(String fileNameIn){
		try{
			FileReader file = new FileReader(fileName);
			buffer = new BufferedReader(file);
		}
		catch(IOException e){
			System.err.println("Could not read from file");
			System.exit(1);
		}
		
	}
	
	/**
	 * Closes file
	 */
	public void closeFile(){
		try{
			buffer.close();
		}
		catch(IOException io){
			System.err.println("File closed");
			System.exit(1);
		}
	}
	
	/**deserializes an object
	 * @return ArrayList<Object>
	 */
	
	public ArrayList<Object> deSerializeClass(String input, Object obj){
		//System.out.println("In deserializeClass");
		fileName=input;
		openFile(fileName);
		String lineRead = " ";
		try{
			while(lineRead != null){
				//consuming the xml at first level @DPserialization
				lineRead = buffer.readLine();
				if(lineRead.contains("DPSerialization"))
				{
					if(lineRead.contains("</DPSerialization>")){
						if(lineRead!=null)
							lineRead = buffer.readLine();
					}
					// second consumption of xml @complexType
					lineRead = buffer.readLine();
					if(lineRead!=null){	
						//for the class MyAllTypesFirst
						if((lineRead.contains("complexType")) && (lineRead.contains(sClass1))){
							className = sClass1;
							try{
								c = Class.forName(sClass1);
								m=c.getDeclaredMethods();
								obj = c.newInstance();
								Logging.getInstance().write(2, "Object of class "+obj.getClass().getCanonicalName() +" has been created");
							}
							catch(ClassNotFoundException ce){
								System.err.println("Cannot find class: "+ sClass1);
								System.exit(1);
							}
							catch(InstantiationException |IllegalAccessException e){
								System.err.println("object cannot be instantiated");
								System.exit(1);
							}
						}
						//for the class MyAllTypesSecond
						else {
							className = sClass2;
							try{
								c = Class.forName(sClass2);
								m=c.getDeclaredMethods();
								obj = c.newInstance();
								Logging.getInstance().write(2, "Object of class "+obj.getClass().getCanonicalName() +" has been created");
							}
							catch(ClassNotFoundException ce){
								System.err.println("Cannot find class: "+ sClass2);
								System.exit(1);
							}
							catch(InstantiationException |IllegalAccessException e){
								System.err.println("object cannot be instantiated");
								System.exit(1);
							}
						}
			
						while(!(lineRead.contains("</complexType>"))){
							lineRead = buffer.readLine();//third consumption
							if(lineRead.contains(sInt)){
								obj = dt.deserializeInt(lineRead, c, obj, sInt);
							}
							else if(lineRead.contains(sString)){
								obj = dt.deserializeString(lineRead, c, obj, sString);
							}
							else if(lineRead.contains(sDouble)){
								obj = dt.deserializeDouble(lineRead, c, obj, sDouble);
							}
							else if(lineRead.contains(sLong)){
								obj = dt.deserializeLong(lineRead, c, obj, sLong);
							}
							else if(lineRead.contains(sChar)){
								obj = dt.deserializeChar(lineRead, c, obj, sChar);
							}
							else if(lineRead.contains(sFloat)){
								obj = dt.deserializeFloat(lineRead, c, obj, sFloat);
							}
							else if(lineRead.contains(sShort)){
								obj = dt.deserializeShort(lineRead, c, obj, sShort);
							}
						}
					}
					//If object is not null add to the ArrayList
					if(obj!=null)
						objList.add(obj);
					obj=null;
				}
			}
		}		
		catch(IOException io){
			System.err.println("File not found");
			System.exit(1);
		}
		finally{
			
		}
		result(objList);
		return objList;
	}
	
	/**
	 * displays result
	 * @param a
	 */
	public void result(ArrayList<Object> a){

		ResultInterface rslt = new Results();
		rslt.disp_result(a);
		
	}
	/**
	 * gets an arraylist
	 * @return ArrayList<Object>
	 */
	public ArrayList<Object> getObjList() {
		return objList;
	}
	/**
	 * sets an arraylist
	 * @param objList
	 */
	public void setObjList(ArrayList<Object> objList) {
		this.objList = objList;
	}
	/**
	 * @return String
	 */
	@Override
	public String toString(){
		return obj.getClass().getName();
	}
}

