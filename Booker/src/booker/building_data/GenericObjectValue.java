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

public abstract class GenericObjectValue<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements FieldValue<T, U, V> {

	private T value;
	private ObjectDeleteListener<T, U, V> deleteListener;

	public GenericObjectValue(T object) {
		this.value = object;
		deleteListener = new SetReferenceNullDeleteListener<T, U, V>(this);
	}

	public T value() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public ValueType type() {
		return GenericValueType.OBJECT;
	}

	public void objectDeleted(T object) {
		deleteListener.objectDeleted(object);
	}

	public void setObjectDeleteListener(ObjectDeleteListener<T, U, V> deleteListener) {
		this.deleteListener = deleteListener;
	}

}
