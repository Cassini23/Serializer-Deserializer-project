package reflection.serDeser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import reflection.util.Logging;

public class SerializeTypes {
	
	private String sInt = "xsd:int";
	private String sString = "xsd:string";
	private String sDouble = "xsd:double";
	private String sLong = "xsd:long";
	private String sChar = "xsd:char";
	private String sFloat = "xsd:float";
	private String sShort = "xsd:short";
	private String types= "xsi:type=";
	
	//tags
	private String openTag ="<";
	private String closeTag =">";
	
	private String ret_string ="";
	
	//for reflection
	Method[] m;
	Object result;
	
	/**
	 * Serializes an int variable
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public String serializeInt(String fieldName, Object obj){
		ret_string ="";
		int value = 0;
		m = obj.getClass().getDeclaredMethods();
		for(int counter = 0; counter < m.length; counter++){
			if(m[counter].getName().contains("get")){
				if(m[counter].getReturnType().toString().equalsIgnoreCase("int")){
					try{
						result = m[counter].invoke(obj);
						value = (int)result;
						Logging.getInstance().write(4, m[counter].getName() +" invoked");
					}
					catch(IllegalAccessException  | InvocationTargetException e){
						System.err.println("failed to serialize, could not retrieve int value");
						System.exit(1);
					}
				}
			}
		}
		ret_string = openTag + fieldName + " " + types + '"' + sInt + '"' + closeTag + value + openTag +"/"+ fieldName + closeTag;
		return ret_string;
	}
	
	/**
	 * Serializes a string variable
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public String serializeString(String fieldName, Object obj){
		ret_string ="";
		String value = null;
		m = obj.getClass().getDeclaredMethods();
		for(int counter = 0; counter < m.length; counter++){
			if(m[counter].getName().contains("get")){
				if(m[counter].getReturnType().toString().contains("String")){
					try{
						result = m[counter].invoke(obj);
						value = (String)result;
						Logging.getInstance().write(4, m[counter].getName() +" invoked");
					}
					catch(IllegalAccessException  | InvocationTargetException e){
						System.err.println("failed to serialize, could not retrieve String value");
						System.exit(1);
					}
				}
			}
		}
		ret_string = openTag + fieldName + " " + types + '"' + sString + '"' + closeTag + value + openTag +"/"+ fieldName + closeTag;
		return ret_string;
	}

	/**
	 * Serializes a double variable
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public String serializeDouble(String fieldName, Object obj){
		//System.out.println("Double serialized");
		ret_string ="";
		double value = 0;
		m = obj.getClass().getDeclaredMethods();
		for(int counter = 0; counter < m.length; counter++){
			if(m[counter].getName().contains("get")){
				if(m[counter].getReturnType().toString().equalsIgnoreCase("double")){
					try{
						result = m[counter].invoke(obj);
						value = (double)result;
						Logging.getInstance().write(4, m[counter].getName() +" invoked");
					}
					catch(IllegalAccessException  | InvocationTargetException e){
						System.err.println("failed to serialize, could not retrieve double value");
						System.exit(1);
					}
				}
			}
		}
		ret_string = openTag + fieldName + " " + types + '"' + sDouble + '"' + closeTag + value + openTag +"/"+ fieldName + closeTag;
		return ret_string;
	}

	/**
	 * Serializes a long variable
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public String serializeLong(String fieldName, Object obj){
		//System.out.println("Long serialized");
		ret_string ="";
		long value = 0;
		m = obj.getClass().getDeclaredMethods();
		for(int counter = 0; counter < m.length; counter++){
			if(m[counter].getName().contains("get")){
				if(m[counter].getReturnType().toString().equalsIgnoreCase("long")){
					try{
						result = m[counter].invoke(obj);
						value = (Long)result;
						Logging.getInstance().write(4, m[counter].getName() +" invoked");
					}
					catch(IllegalAccessException  | InvocationTargetException e){
						System.err.println("failed to serialize, could not retrieve long value");
						System.exit(1);
					}
				}
			}
		}
		ret_string = openTag + fieldName + " " + types + '"' + sLong + '"' + closeTag + value + openTag +"/"+ fieldName + closeTag;
		return ret_string;
	}

	/**
	 * Serializes a char variable
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public String serializeChar(String fieldName, Object obj){
		//System.out.println("Char serialized");
		ret_string ="";
		char value = '\0';
		m = obj.getClass().getDeclaredMethods();
		for(int counter = 0; counter < m.length; counter++){
			if(m[counter].getName().contains("get")){
				if(m[counter].getReturnType().toString().equalsIgnoreCase("char")){
					try{
						result = m[counter].invoke(obj);
						value = (char)result;
						Logging.getInstance().write(4, m[counter].getName() +" invoked");
					}
					catch(IllegalAccessException  | InvocationTargetException e){
						System.err.println("failed to serialize, could not retrieve char value");
						System.exit(1);
					}
				}
			}
		}
		ret_string = openTag + fieldName + " " + types + '"' + sChar + '"' + closeTag + value + openTag +"/"+ fieldName + closeTag;
		return ret_string;
	}
	
	/**
	 * Serializes a float variable
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public String serializeFloat(String fieldName, Object obj){
		//System.out.println("Float serialized");
		ret_string ="";
		float value = 0;
		m = obj.getClass().getDeclaredMethods();
		for(int counter = 0; counter < m.length; counter++){
			if(m[counter].getName().contains("get")){
				if(m[counter].getReturnType().toString().equalsIgnoreCase("float")){
					try{
						result = m[counter].invoke(obj);
						value = (float)result;
						Logging.getInstance().write(4, m[counter].getName() +" invoked");
					}
					catch(IllegalAccessException  | InvocationTargetException e){
						System.err.println("failed to serialize, could not retrieve float value");
						System.exit(1);
					}
				}
			}
		}
		ret_string = openTag + fieldName + " " + types + '"' + sFloat + '"' + closeTag + value + openTag +"/"+ fieldName + closeTag;
		return ret_string;
	}
	
	/**
	 * Serializes a Short variable
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public String serializeShort(String fieldName, Object obj){
		//System.out.println("Short serialized");
		short value = 0;
		m = obj.getClass().getDeclaredMethods();
		for(int counter = 0; counter < m.length; counter++){
			if(m[counter].getName().contains("get")){
				if(m[counter].getReturnType().toString().equalsIgnoreCase("short")){
					try{
						result = m[counter].invoke(obj);
						value = (short)result;
						Logging.getInstance().write(4, m[counter].getName() +" invoked");
					}
					catch(IllegalAccessException  | InvocationTargetException e){
						System.err.println("failed to serialize, could not retrieve short value");
						System.exit(1);
					}
				}
			}
		}
		ret_string = openTag + fieldName + " " + types + '"' + sShort + '"' + closeTag + value + openTag +"/" + fieldName + closeTag;
		return ret_string;
	}
}
