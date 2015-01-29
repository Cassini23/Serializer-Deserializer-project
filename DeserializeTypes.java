package reflection.serDeser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import reflection.util.Logging;

public class DeserializeTypes {
//declare both class objects
	int startpos,endpos;
	Method  m[];
	Method meth = null;
	Class<?>[] paramList;
	Object[] params =  new Object[1];
	Object result;
	
	/**
	 * deserializes an int
	 * @param lineRead
	 * @param className
	 * @param obj
	 * @param sInt
	 * @return Object
	 */
	public Object deserializeInt(String lineRead, Class className, Object obj, String sInt){
		//receive string name class and get methods, pass string read
		int num = 0;
		startpos = lineRead.indexOf(sInt)+sInt.length()+2;
		endpos= lineRead.lastIndexOf("<");
		num = Integer.parseInt(lineRead.substring(startpos, endpos));
		m = className.getDeclaredMethods();
		for(int i =0 ; i < m.length; i++){
			if(m[i].toString()!= null){
				if(m[i].toString().contains("set")){
					meth=m[i];
					paramList = meth.getParameterTypes();
					for(int  j =0 ; j<paramList.length; j++){
						if(paramList[j].toString().contains("int")){
							params[0] = new Integer(num);
							try{
								result = meth.invoke(obj, params);
								Logging.getInstance().write(3, m[i].getName()+ " invoked");
							}
							catch(InvocationTargetException| IllegalAccessException ie ){
								System.err.println("Could not invoke method");
								System.exit(1);
							}
						}
					}	
				}
			}
		}					
		return obj;
	}
	
	/**
	 * deserializes a string
	 * @param lineRead
	 * @param className
	 * @param obj
	 * @param sString
	 * @return
	 */
	public Object deserializeString(String lineRead, Class className, Object obj, String sString){
		String temp = null;
		startpos = lineRead.indexOf(sString)+sString.length()+2;
		endpos= lineRead.lastIndexOf("<");
		temp = (lineRead.substring(startpos, endpos));
		m = className.getDeclaredMethods();
		for(int i =0 ; i < m.length; i++){
			if(m[i].toString()!= null){
				if(m[i].toString().contains("set")){
					meth=m[i];
					paramList = meth.getParameterTypes();
					for(int  j =0 ; j<paramList.length; j++){
						if(paramList[j].toString().contains("String")){
							params[0] = new String(temp);
							try{
								result = meth.invoke(obj, params);
								Logging.getInstance().write(3, m[i].getName()+ " invoked");
							}
							catch(InvocationTargetException| IllegalAccessException ie ){
								System.err.println("Could not invoke method");
								System.exit(1);
							}
						}
					}	
				}
			}
		}						
		return obj;
	}

	/**
	 * deserializes a double
	 * @param lineRead
	 * @param className
	 * @param obj
	 * @param sDouble
	 * @return
	 */
	public Object deserializeDouble(String lineRead, Class className, Object obj, String sDouble){
		double d = 0.0;
		startpos = lineRead.indexOf(sDouble)+sDouble.length()+2;
		endpos= lineRead.lastIndexOf("<");
		d = Double.parseDouble(lineRead.substring(startpos, endpos));
		m = className.getDeclaredMethods();
		for(int i =0 ; i < m.length; i++){
			if(m[i].toString()!= null){
				if(m[i].toString().contains("set")){
					meth=m[i];
					paramList = meth.getParameterTypes();
					for(int  j =0 ; j<paramList.length; j++){
						if(paramList[j].toString().contains("double")){
							params[0] = new Double(d);
							try{
								result = meth.invoke(obj, params);
								Logging.getInstance().write(3, m[i].getName()+ " invoked");
							}
							catch(InvocationTargetException| IllegalAccessException ie ){
								System.err.println("Could not invoke method");
								System.exit(1);
							}
						}
					}	
				}
			}
		}
		return obj;
	}

	/**
	 * deserializes a long
	 * @param lineRead
	 * @param className
	 * @param obj
	 * @param sLong
	 * @return
	 */
	public Object deserializeLong(String lineRead, Class className, Object obj, String sLong){
		long l = 0;
		startpos = lineRead.indexOf(sLong)+sLong.length()+2;
		endpos= lineRead.lastIndexOf("<");
		l = Long.parseLong(lineRead.substring(startpos, endpos));
		m = className.getDeclaredMethods();
		for(int i =0 ; i < m.length; i++){
			if(m[i].toString()!= null){
				if(m[i].toString().contains("set")){
					meth=m[i];
					paramList = meth.getParameterTypes();
					for(int  j =0 ; j<paramList.length; j++){
						if(paramList[j].toString().contains("long")){
							params[0] = new Long(l);
							try{
								 result = meth.invoke(obj, params);
								 Logging.getInstance().write(3, m[i].getName()+ " invoked");
							}
							catch(InvocationTargetException| IllegalAccessException ie ){
								System.err.println("Could not invoke method");
								System.exit(1);
							}
						}
					}	
				}
			}
		}
		
		return obj;
	}

	/**
	 * deserializes a char 
	 * @param lineRead
	 * @param className
	 * @param obj
	 * @param sChar
	 * @return
	 */
	public Object deserializeChar(String lineRead, Class className, Object obj, String sChar){
		char ch;
		startpos = lineRead.indexOf(sChar)+sChar.length()+2;
		endpos= lineRead.lastIndexOf("<");
		//System.out.println(lineRead.substring(startpos, endpos)+ " ..........."+className.getName());
		ch = lineRead.charAt(startpos);
		m = className.getDeclaredMethods();
		for(int i =0 ; i < m.length; i++){
			if(m[i].toString()!= null){
				if(m[i].toString().contains("set")){
					meth=m[i];
					paramList = meth.getParameterTypes();
					for(int  j =0 ; j<paramList.length; j++){
						if(paramList[j].toString().contains("char")){
							params[0] = new Character(ch);
							try{
								result = meth.invoke(obj, params);
								Logging.getInstance().write(3, m[i].getName()+ " invoked");
							}
							catch(InvocationTargetException| IllegalAccessException ie ){
								System.err.println("Could not invoke method");
								System.exit(1);
							}
						}
					}	
				}
			}
		}
		
		return obj;
	}
	
	/**
	 * deserializes a float
	 * @param lineRead
	 * @param className
	 * @param obj
	 * @param sFloat
	 * @return
	 */
	public Object deserializeFloat(String lineRead, Class className, Object obj, String sFloat){
		float f = 0;
		startpos = lineRead.indexOf(sFloat)+sFloat.length()+2;
		endpos= lineRead.lastIndexOf("<");
		f = Float.parseFloat(lineRead.substring(startpos, endpos));
		m = className.getDeclaredMethods();
		for(int i =0 ; i < m.length; i++){
			if(m[i].toString()!= null){
				if(m[i].toString().contains("set")){
					meth=m[i];
					paramList = meth.getParameterTypes();
					for(int  j =0 ; j<paramList.length; j++){
						if(paramList[j].toString().contains("float")){
							params[0] = new Float(f);
							try{
								 result = meth.invoke(obj, params);
								 Logging.getInstance().write(3, m[i].getName()+ " invoked");
							}
							catch(InvocationTargetException| IllegalAccessException ie ){
								System.err.println("Could not invoke method");
								System.exit(1);
							}
						}
					}	
				}
			}
		}
		return obj;
	}
	
	/**
	 * deserializes short
	 * @param lineRead
	 * @param className
	 * @param obj
	 * @param sShort
	 * @return
	 */
	public Object deserializeShort(String lineRead, Class className, Object obj, String sShort){
		short s = 0;
		startpos = lineRead.indexOf(sShort)+sShort.length()+2;
		endpos= lineRead.lastIndexOf("<");
		s = Short.parseShort(lineRead.substring(startpos, endpos));
		m = className.getDeclaredMethods();
		for(int i =0 ; i < m.length; i++){
			if(m[i].toString()!= null){
				if(m[i].toString().contains("set")){
					meth=m[i];
					paramList = meth.getParameterTypes();
					for(int  j =0 ; j<paramList.length; j++){
						if(paramList[j].toString().contains("short")){
							params[0] = new Short(s);
							try{
								result = meth.invoke(obj, params);
								Logging.getInstance().write(3, m[i].getName()+ " invoked");
							}
							catch(InvocationTargetException| IllegalAccessException ie ){
								System.err.println("Could not invoke method");
								System.exit(1);
							}
						}
					}	
				}
			}
		}
		return obj;
	}
}
