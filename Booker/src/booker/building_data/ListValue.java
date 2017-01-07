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

public abstract class ListValue<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements FieldValue<T, U, V> {

	private ArrayList<V> elements;

	public ListValue() {
		elements = new ArrayList<V>();
	}

	public ListValue(ArrayList<V> elements) {
		this.elements = elements;
	}

	public void add(V element) {
		elements.add(element);
	}

	public V get(int index) {
		return elements.get(index);
	}

	public void set(int index, V element) {
		elements.set(index, element);
	}

	public int size() {
		return elements.size();
	}

	@Override
	public void set(int index, double value) {
		elements.get(index).set(value);
	}

	@Override
	public void set(int index, String string) {
		elements.get(index).set(string);
	}

	@Override
	public void set(int index, T object) {
		elements.get(index).set(object);
	}

	@Override
	public GenericValueType type() {
		return GenericValueType.LIST;
	}

	public void objectDeleted(T object) {
		for (int i = 0; i < size(); i++) {
			if (get(i) instanceof GenericObjectValue) {
				((GenericObjectValue<T, U, V>) get(i)).objectDeleted(object);
			}
		}
	}

}
