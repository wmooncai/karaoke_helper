/**
 * 
 */
package utils;

import android.app.Activity;


/**
 * @author W. Mooncai
 * 
 * @since 0.0
 * 
 * Adds Debug class to the standard Android Activity.
 *
 */
public class WAMSActivity extends Activity implements DebugInterface {

	protected Debug d = new Debug(DEBUG_OFF, DEBUG_LEVEL_INFORMATIONAL);
/*
	public WAMSActivity() {
		// TODO Auto-generated constructor stub
	}
*/
}
