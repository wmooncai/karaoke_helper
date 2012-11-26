/**
 * 
 */
package utils;

/**
 * @author W. Mooncai
 * 
 * @since 0.0
 * 
 * Debugging class to output troubleshooting output to Console
 * 
 * Logging Levels are based on Syslog
 *
 */
public class Debug implements DebugInterface {

	/** Toggle Debugging ON or OFF */
	public static boolean toggle = DEBUG_OFF;
	
	/** Level of detail for debugging:
	 * 0	Emergency
	 * 1	Alert
	 * 2	Critical
	 * 3	Error
	 * 4	Warning
	 * 5	Notice
	 * 6	Informational
	 * 7	Debug
	 */
	public static int level = DEBUG_LEVEL_DEBUG;
	
	/**
	 * Constructor
	 * 
	 * @param toggle	Globally toggle logging On / Off 
	 * @param level		Global logging level.  Messages of this level or lower
	 * 					will be output to log.
	 *  
	 */
	public Debug(boolean toggle, int level) {
		setToggle(toggle);
		setLevel(level);
	}
	
	// ******************************** METHODS *******************************
	
	/**
	 * Output error message if Debugging is ON and debugging is at least this
	 *  level of detail.
	 * 
	 * @since 0.0
	 * 
	 * @param lvl		Debugging Level
	 * @param message	Error message to log
	 */
	public void toLog(int lvl, String message) {
		
		if ( (toggle) && (level <= lvl) ) {
			System.out.println("\n\n##########################\n"
					+ message + "\n##########################\n\n");
		}
	}

	// ******************************** GETTERS *******************************

	/**
	 * @since 0.0
	 * 
	 * @return		Value of global debug On / Off toggle.
	 */
	public boolean getToggle () { return toggle; }
	
	/**
	 * @since 0.0
	 * 
	 * @return		Value of the global logging level setting.
	 */
	public int getLevel() { return level; }

	// ******************************** SETTERS *******************************

	/**
	 * @since 0.0
	 * 
	 * @param lvl	Global logging level.
	 */
	public void setLevel (int lvl) {
	
		if ( (lvl < DEBUG_LEVEL_DEBUG) && (lvl > DEBUG_LEVEL_EMERGENCY) )
			level = lvl;
	}
	
	/**
	 * @since 0.0
	 * 
	 * @param		Global logging level.
	 */
	public void setToggle (boolean turn) { toggle = turn; }
	

}
