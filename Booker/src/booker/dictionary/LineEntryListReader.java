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
