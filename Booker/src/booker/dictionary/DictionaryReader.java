/*
 *
 *  Copyright (C) 2017 Aaron Powers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package booker.dictionary;

public class DictionaryReader {

	public BasicDictionaryLookup<Entry> read(EntryListReader<Entry> entryListReader) {
		BasicDictionaryLookup<Entry> dictionary = new BasicDictionaryLookup<Entry>();
		dictionary.setEntries(entryListReader.read());
		return dictionary;
	}

	public BasicDictionaryLookup<Entry> read(String fileName, ReadableEntryFactory<Entry> factory) {
		LineEntryListReader<Entry> entryListReader = new LineEntryListReader<Entry>(fileName, factory);
		BasicDictionaryLookup<Entry> dictionary = new BasicDictionaryLookup<Entry>();
		dictionary.setEntries(entryListReader.read());
		return dictionary;
	}

}
