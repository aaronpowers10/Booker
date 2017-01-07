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

public class FieldList<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements Parsable {

	private ArrayList<U> fields;

	public FieldList() {
		fields = new ArrayList<U>();
	}

	public void add(U field) {
		fields.add(field);
	}

	public U get(int index) {
		return fields.get(index);
	}

	public void remove(int index) {
		fields.remove(index);
	}

	public U get(String name) throws BuildingDataException {
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).name().equals(name)) {
				return fields.get(i);
			}
		}
		throw new BuildingDataException();
	}

	public int size() {
		return fields.size();
	}

}
