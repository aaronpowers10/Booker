package booker.dictionary;

/**
 * 
 * @author Aaron Powers
 *
 * @param <T>
 */

public interface EntryListReader<T extends Entry> {

	public EntryList<T> read();

}
