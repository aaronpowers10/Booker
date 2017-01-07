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

public interface FieldValue<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>> {

	public ValueType type();

	public default GenericNumericValue<T, U, V> getAsGenericNumeric() throws BuildingDataException {
		if (this instanceof GenericNumericValue) {
			return (GenericNumericValue<T, U, V>) this;
		} else {
			throw new BuildingDataException("The field is not a numeric type.");
		}
	}

	public default GenericAlphaValue<T, U, V> getAsGenericAlpha() throws BuildingDataException {
		if (this instanceof GenericAlphaValue) {
			return (GenericAlphaValue<T, U, V>) this;
		} else {
			throw new BuildingDataException("The field is not an alpha type.");
		}
	}

	public default GenericObjectValue<T, U, V> getAsGenericObject() throws BuildingDataException {
		if (this instanceof GenericObjectValue) {
			return (GenericObjectValue<T, U, V>) this;
		} else {
			throw new BuildingDataException("The field is not an object type.");
		}
	}

	public default ListValue<T, U, V> getAsList() throws BuildingDataException {
		if (this instanceof ListValue) {
			return (ListValue<T, U, V>) this;
		} else {
			throw new BuildingDataException("The field is not a list type.");
		}
	}

	public default void set(double value) throws BuildingDataException {
		if (this instanceof GenericNumericValue) {
			((GenericNumericValue<T, U, V>) this).set(value);
		} else {
			throw new BuildingDataException("The field is not a numeric type.");
		}
	}

	public default void set(String string) throws BuildingDataException {
		if (this instanceof GenericAlphaValue) {
			((GenericAlphaValue<T, U, V>) this).set(string);
		} else {
			throw new BuildingDataException("The field is not an alpha type.");
		}
	}

	public default void set(T object) throws BuildingDataException {
		if (this instanceof GenericObjectValue) {
			((GenericObjectValue<T, U, V>) this).setValue(object);
		} else {
			throw new BuildingDataException("The field is not an object type.");
		}
	}

	public default void set(int index, double value) throws BuildingDataException {
		if (this instanceof ListValue) {
			((ListValue<T, U, V>) this).set(index, value);
		} else {
			throw new BuildingDataException("The field is not a list type.");
		}
	}

	public default void set(int index, String string) throws BuildingDataException {
		if (this instanceof ListValue) {
			((ListValue<T, U, V>) this).set(index, string);
		} else {
			throw new BuildingDataException("The field is not a list type.");
		}
	}

	public default void set(int index, T object) throws BuildingDataException {
		if (this instanceof ListValue) {
			((ListValue<T, U, V>) this).set(index, object);
		} else {
			throw new BuildingDataException("The field is not a list type.");
		}
	}

	public default void add(V element) throws BuildingDataException {
		if (this instanceof ListValue) {
			((ListValue<T, U, V>) this).add(element);
		} else {
			throw new BuildingDataException("The field is not a list type.");
		}
	}

	public V copy();

}
