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

public class HasFieldFilter<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements ObjectFilter<T, U, V> {

	private String fieldName;

	public HasFieldFilter(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public boolean filter(T object) throws BuildingDataException {
		if (object.hasField(fieldName)) {
			return true;
		} else {
			return false;
		}
	}

}
