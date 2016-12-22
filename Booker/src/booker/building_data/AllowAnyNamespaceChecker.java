package booker.building_data;

/**
 * 
 * Allows any string to e set as a name.
 * 
 * @author Aaron Powers
 *
 */

public class AllowAnyNamespaceChecker implements NamespaceChecker{

	@Override
	public boolean isAllowed(String name) {
		return true;
	}

	@Override
	public String message() {
		return "";
	}

}
