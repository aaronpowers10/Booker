package booker.building_data;

/**
 * 
 * Prints the update message to the console.
 * 
 * @author Aaron Powers
 *
 */

public class ConsoleUpdateListener implements UpdateListener{

	@Override
	public void update(String message) {
		System.out.println(message);
	}

}
