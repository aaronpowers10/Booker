package booker.dictionary;

/**
 * 
 * @author Aaron Powers
 * 
 */

import java.util.Scanner;

public class LineEntryListReader<T extends Entry> implements EntryListReader<T> {

	private String fileName;
	private ReadableEntryFactory<T> factory;

	public LineEntryListReader(String fileName, ReadableEntryFactory<T> factory) {
		this.fileName = fileName;
		this.factory = factory;
	}

	@Override
	public EntryList<T> read() {
		EntryList<T> entryList = new EntryList<T>();
		Scanner in = new Scanner(getClass().getResourceAsStream("/doe2_res/" + fileName));
		while (in.hasNextLine()) {
			entryList.add(factory.create(fileName, in.nextLine()));
		}
		in.close();
		return entryList;
	}

}
