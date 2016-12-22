package booker.dictionary;

/**
 * 
 * @author Aaron Powers
 *
 */

public interface Entry {

	public boolean isMatch(String entryName);

	public boolean startsWith(String prefix);

}
