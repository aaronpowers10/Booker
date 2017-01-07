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

import java.util.ArrayList;

public class EntryList<T extends Entry> {
	private ArrayList<T> entries;

	public EntryList() {
		entries = new ArrayList<T>();
	}

	public void add(T entry) {
		entries.add(entry);
	}

	public boolean isMember(String entryName) {
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).isMatch(entryName)) {
				return true;
			}
		}
		return false;
	}

	public T getEntry(String entryName) {
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).isMatch(entryName)) {
				return entries.get(i);
			}
		}
		return null;
	}

	public boolean contains(String sequence) {
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).startsWith(sequence)) {
				return true;
			}
		}
		return false;
	}

	public T getEntry(int index) {
		return entries.get(index);
	}

	public int size() {
		return entries.size();
	}

}
