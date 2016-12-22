package booker.dictionary;

/**
 * 
 * @author Aaron Powers
 *
 */

public class DictionaryReader {

	public BasicDictionaryLookup<Entry> read(EntryListReader<Entry> entryListReader){
		BasicDictionaryLookup<Entry> dictionary = new BasicDictionaryLookup<Entry>();
		dictionary.setEntries(entryListReader.read());
		return dictionary;
	}

	public BasicDictionaryLookup<Entry> read(String fileName,ReadableEntryFactory<Entry> factory){
		LineEntryListReader<Entry> entryListReader = new LineEntryListReader<Entry>(fileName,factory);
		BasicDictionaryLookup<Entry> dictionary = new BasicDictionaryLookup<Entry>();
		dictionary.setEntries(entryListReader.read());
		return dictionary;
	}

}
