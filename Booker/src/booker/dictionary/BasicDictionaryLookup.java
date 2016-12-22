package booker.dictionary;

/**
 * 
 * @author Aaron Powers
 *
 * @param <T>
 */

public class BasicDictionaryLookup<T extends Entry> implements DictionaryLookup{

	private EntryList<T> entries;

	public BasicDictionaryLookup(){
		entries = new EntryList<T>();
	}

	public void setEntries(EntryList<T> entries){
		this.entries = entries;
	}

	@Override
	public boolean isMember(String entryName) {
		return entries.isMember(entryName);
	}

	@Override
	public boolean contains(String sequence) {
		return entries.contains(sequence);
	}

	@Override
	public Entry getEntry(String entryName) {
		return entries.getEntry(entryName);
	}

}
