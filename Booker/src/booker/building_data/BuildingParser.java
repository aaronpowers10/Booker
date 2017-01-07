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

import booker.lexical.CannotParseException;
import booker.lexical.InputSequence;

public abstract class BuildingParser<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>, W extends Parsable> {

	private ArrayList<UpdateListener> listeners;

	public BuildingParser(ArrayList<UpdateListener> listeners) {
		this.listeners = listeners;
	}

	public void addListener(UpdateListener listener) {
		listeners.add(listener);
	}

	public W parseWithMessage(InputSequence in, ObjectReferences<T, U, V> objects) throws CannotParseException {
		W parsable = parse(in, objects);
		notifyListeners();
		return parsable;
	}

	public abstract W parse(InputSequence in, ObjectReferences<T, U, V> objects) throws CannotParseException;

	public abstract String message();

	private void notifyListeners() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).update(message());
		}
	}

}
