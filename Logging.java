package reflection.util;

public class Logging {

	public enum LogLevels {
		UNIQUE, NO_OUTPUT, OBJ_CREATED, DSERIAL_METHOD_INVOKE, SERIAL_METHOD_INVOKE;

	}

	private int debug_value;

	private static Logging instance = null;

	private Logging() {
		setDebug_value(Debug.getDEBUG_VALUE());
	}

	/**
	 * getInstance method 
	 * @return the unique instance of Logging object
	 */
	public static Logging getInstance() {
		if (instance == null) {
			instance = new Logging();
		}
		return instance;
	}

	 public int getDebug_value() {
		return debug_value;
	}

	public void setDebug_value(int debug_value) {
		this.debug_value = debug_value;
	}
	
	/**
     * write method
     *
     * @param debug_level given debug level for the program
     * @param message log message to print 
     */
     public void write(int debug_level, String message) {
		
		
		if (debug_level == Debug.getDEBUG_VALUE()) {
			switch (LogLevels.values()[debug_level]) {

			case UNIQUE: {
				System.out.println("Unique Instances");
				System.out.println(message);
				break;
			}
			case NO_OUTPUT: {
				break;
			}
			case OBJ_CREATED: {
			//	System.out.println("Objects");
				System.out.println(message);
				break;
			}
			case DSERIAL_METHOD_INVOKE: {
				System.out.println("Method Calls (Deserialization):" + message);
				break;
			}
			case SERIAL_METHOD_INVOKE: {
				System.out.println("Method Calls (Serialization):" + message);
				break;
			}

			default: {
				System.out.println("No such debug level");
				break;
			}

			}
		}

		
	}
     /**@Override
 	 * toString method
 	 * @return
 	 * returns the current state of variables in String format
 	 */
 	public String toString(){
 		   return "Debug value = " + debug_value;
 	   }
}

