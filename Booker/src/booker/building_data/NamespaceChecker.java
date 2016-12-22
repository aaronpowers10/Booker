package booker.building_data;

/**
 * 
 * Checks if the given name is allowed.
 * 
 * @author Aaron Powers
 *
 */

public interface NamespaceChecker {

	public boolean isAllowed(String name);

	public String message();

}
