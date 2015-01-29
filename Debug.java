
package reflection.util;

public class Debug {
    private static int DEBUG_VALUE;

 /**
  *  accessor for DEBUG_VALUE
  * @return
  * returns int DEBUG_VALUE
  */
	public static int getDEBUG_VALUE() {
		return DEBUG_VALUE;
	}
/**
 * Mutator for DEBUG_VALUE
 * @param dEBUG_VALUE
 * sets the DEBUG_VALUE to dEBUG_VALUE
 */
	public static void setDEBUG_VALUE(int dEBUG_VALUE) {
		DEBUG_VALUE = dEBUG_VALUE;
	}

}
