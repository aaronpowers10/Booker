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

package booker.io;

import java.util.ArrayList;

import booker.building_data.Namespace;
import booker.building_data.NamespaceReferences;
import otis.lexical.CannotParseException;
import otis.lexical.InputSequence;
import otis.lexical.Parsable;
import otis.lexical.UpdateListener;

public abstract class BuildingParser<T extends Namespace>{

	private ArrayList<UpdateListener> listeners;

	public BuildingParser(ArrayList<UpdateListener> listeners) {
		this.listeners = listeners;
	}

	public void addListener(UpdateListener listener) {
		listeners.add(listener);
	}

	public Parsable parseWithMessage(InputSequence in, NamespaceReferences<T> objects) throws CannotParseException {
		Parsable parsable = parse(in, objects);
		notifyListeners();
		return parsable;
	}

	public abstract Parsable parse(InputSequence in, NamespaceReferences<T> objects) throws CannotParseException;

	public abstract String message();

	private void notifyListeners() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).update(message());
		}
	}

}
