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

package booker.building_data;

import java.util.ArrayList;

import otis.lexical.Parsable;

public class FieldList
		implements Parsable {

	private ArrayList<BookerField> fields;

	public FieldList() {
		fields = new ArrayList<BookerField>();
	}

	public void add(BookerField field) {
		fields.add(field);
	}

	public BookerField get(int index) {
		return fields.get(index);
	}

	public void remove(int index) {
		fields.remove(index);
	}

	public BookerField get(String name) throws BookerDataException {
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).name().equals(name)) {
				return fields.get(i);
			}
		}
		throw new BookerDataException("The field " + name + " cannot be found.");
	}

	public int size() {
		return fields.size();
	}

}
