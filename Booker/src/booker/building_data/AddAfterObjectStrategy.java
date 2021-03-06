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

public class AddAfterObjectStrategy<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements AddObjectStrategy<T, U, V> {

	private String objectName;

	public AddAfterObjectStrategy(String objectName) {
		this.objectName = objectName;
	}

	@Override
	public void addToProject(T object, ObjectList<T, U, V> objects) {
		int objectsSize = objects.size();
		for (int i = 0; i < objectsSize; i++) {
			if (objects.get(i).name().equals(objectName)) {
				if (i < objects.size() - 1) {
					objects.add(i + 1, object);
				} else {
					objects.add(object);
				}
				break;
			}
		}

	}

}
