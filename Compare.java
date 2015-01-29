package reflection.serDeser;

import reflection.util.Logging;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Compare {
	
	Object obj1;

	int UniqueInstancefirst ;
	int UniqueInstanceSecond ;
	boolean isUnique = false;
	
	Method[] m1,m2 ;
	Class<?> retList1;

	Class<?> retList2;
	
	ArrayList<Object> listedUnique = new ArrayList<Object>(0); 
	
	public Compare(){
		
	}
	/**
	 * compares objects
	 * @param objList
	 */
	public void compareObject(ArrayList<Object> objList){
		UniqueInstancefirst = 0;
		UniqueInstancefirst = 0;
		for(int i=0;i<objList.size();i++){
			obj1 = null;
			obj1 = objList.get(i);
			for(int k=i+1; k < objList.size(); k++){
				Object temp;
				temp = objList.get(k);
				String s2=temp.getClass().getCanonicalName().toString();
				boolean b = equals(temp);
				if(listedUnique.size()==0)
					listedUnique.add(obj1);
				
				if(b==false){
					isUnique=true;
						if(!listedUnique.contains(obj1)){
							listedUnique.add(obj1);
						}
				}
			}
		}
		for(int counter = 0; counter <listedUnique.size(); counter++){
			String s1=listedUnique.get(counter).getClass().getCanonicalName().toString();
			if(listedUnique.get(counter).getClass().getCanonicalName().contains("First"))
				UniqueInstancefirst = UniqueInstancefirst + 1;
			else
				UniqueInstanceSecond = UniqueInstanceSecond + 1;
		}
		Logging.getInstance().write(0,"Unique MyAllTypesFirst: "+UniqueInstancefirst+"\n"+"Unique MyAllTypesSecond: " +UniqueInstanceSecond);
	}
	
	/**
	 * @return int
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int num = 31;
		if(obj1 == null)
			return num*0;
		else
			return num*obj1.hashCode();
	}

	/**
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean ret_val=false;
		if(obj == null){
			return ret_val;
		}
		if(obj == obj1){
			ret_val=true;
			return ret_val;
		}
		
		else{ 
			if(obj.getClass() == obj1.getClass()){
				m1 = obj.getClass().getDeclaredMethods();
				m2 = obj1.getClass().getDeclaredMethods();
				for(int i=0 ; i<m1.length ; i++){
					for(int j=0 ; j<m2.length ; j++){
						if(m1[i].getName() == m2[j].getName()){
							retList1 = m1[i].getReturnType();
							retList2 = m2[j].getReturnType();
							if((retList1==retList2) && (!retList1.toString().contains("void"))&&(!retList2.toString().contains("void"))){
								try{
									if(m1[i].invoke(obj).toString().equals(m2[j].invoke(obj1).toString())){
										ret_val = true;
									}
									else
										ret_val = false;
								}
								catch(IllegalAccessException |InvocationTargetException e){
									System.err.println("Method cannot be accessed");
								}
							}
						}
					}
				}
			}
				
			else{
				ret_val = false;
			}
		}
		return ret_val;
	}
	
}
