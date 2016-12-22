package booker.dictionary;

/**
 * 
 * @author Aaron Powers
 *
 * @param <T>
 */

public interface ReadableEntryFactory<T extends Entry> {

	public T create(String fileName, String inputString);

}
