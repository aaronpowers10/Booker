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

public interface BuildingField<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>> {

	public String name();

	public V value();

	public default ValueType type() {
		return value().type();
	}

	public default GenericNumericValue<T, U, V> getAsGenericNumeric() {
		return value().getAsGenericNumeric();
	}

	public default GenericAlphaValue<T, U, V> getAsGenericAlpha() {
		return value().getAsGenericAlpha();
	}

	public default GenericObjectValue<T, U, V> getAsGenericObject() {
		return value().getAsGenericObject();
	}

	public default void set(double value) {
		value().set(value);
	}

	public default void set(String string) {
		value().set(string);
	}

	public default void set(T object) {
		value().set(object);
	}

	public default void set(int index, double value) {
		value().set(index, value);
	}

	public default void set(int index, String string) {
		value().set(index, string);
	}

	public default void set(int index, T object) {
		value().set(index, object);
	}

	public default void add(V element) {
		value().add(element);
	}

	public U copy();

}
