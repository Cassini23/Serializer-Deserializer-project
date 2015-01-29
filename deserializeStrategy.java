package reflection.serDeser;

import java.util.ArrayList;

public interface deserializeStrategy {

	public ArrayList<Object> deSerializeClass(String input, Object obj);
}
