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

public class AddBeforeFirstInstanceStrategy
		implements AddNamespaceStrategy<BookerObject> {

	private String type;

	/**
	 *
	 * @param type
	 *            The new object will be added just before the first instance of
	 *            this type.
	 */
	public AddBeforeFirstInstanceStrategy(String type) {
		this.type = type;
	}

	@Override
	public void addToProject(BookerObject object, NamespaceList<BookerObject> objects) {
		int objectsSize = objects.size();
		for (int i = 0; i < objectsSize; i++) {
			if (objects.get(i).type().equals(type)) {
				objects.add(i, object);
				break;
			}
		}
	}

}
