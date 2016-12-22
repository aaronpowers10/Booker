package booker.dictionary;

/**
 * 
 * @author Aaron Powers
 *
 */

public interface DictionaryLookup {

	public boolean isMember(String entryName);

	public boolean contains(String sequence);

	public Entry getEntry(String entryName);

}
