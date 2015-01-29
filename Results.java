package reflection.util;

import java.util.ArrayList;
import java.lang.reflect.Field;

public class Results implements ResultInterface{
	
	Field[] field;
	private StringBuffer stream = new StringBuffer();
	
	@Override
	public void disp_result(ArrayList<Object> list) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<list.size(); i++){
			stream.append("\nObject 1: "+list.get(i).getClass().getCanonicalName()+"\n"+ "fields contained:");
			field = list.get(i).getClass().getDeclaredFields();
			for(int j=0; j<field.length; j++){
				stream.append("\n"+field[j].getName());
			}
		}
		Logging.getInstance().write(2, toString());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return stream.toString();
	}
	
}
