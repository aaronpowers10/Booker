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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import booker.io.OutputSequence;

public class BookerProject implements NamespaceReferences<BookerObject> {

	private NamespaceList<BookerObject> objects;
	private NamespaceList<BookerObject> libraryObjects;
	private ArrayList<NamespaceLoadListener<BookerObject>> objectLoadListeners;

	public BookerProject() {
		objects = new NamespaceList<BookerObject>();
		libraryObjects = new NamespaceList<BookerObject>();
		objectLoadListeners = new ArrayList<NamespaceLoadListener<BookerObject>>();
	}

	public void add(BookerObject object) {
		add(object, new AddToEndStrategy<BookerObject>());
	}

	public void addLibraryObject(BookerObject object) {
		libraryObjects.add(object);
	}

	public void add(BookerObject object, AddNamespaceStrategy<BookerObject> addStrategy) {
		objects.add(object, addStrategy);
		objects.addDeleteListener(object);
	}

	public void addBeforeFirstInstance(BookerObject object, String beforeType) {
		AddBeforeFirstInstanceStrategy addStrategy = new AddBeforeFirstInstanceStrategy(beforeType);
		add(object, addStrategy);
	}

	public void addAfterLastInstance(BookerObject object, String afterType) {
		AddAfterLastInstanceStrategy addStrategy = new AddAfterLastInstanceStrategy(afterType);
		add(object, addStrategy);
	}

	public void addBeforeObject(BookerObject object, String objectName) {
		AddBeforeObjectStrategy<BookerObject> addStrategy = new AddBeforeObjectStrategy<BookerObject>(objectName);
		add(object, addStrategy);
	}

	public void addAfterObject(BookerObject object, String objectName) {
		AddAfterObjectStrategy<BookerObject> addStrategy = new AddAfterObjectStrategy<BookerObject>(objectName);
		add(object, addStrategy);
	}

	public BookerObject get(int index) {
		return objects.get(index);
	}

	public BookerObject get(String name) {
		return objects.get(name);
	}

	public void addObjectDeleteListener(NamespaceDeleteListener<BookerObject> deleteListener) {
		objects.addDeleteListener(deleteListener);
	}

	public void removeObjectDeleteListener(NamespaceDeleteListener<BookerObject> deleteListener) {
		objects.removeDeleteListener(deleteListener);
	}

	public boolean isMember(String name) {
		return objects.isMember(name);
	}

	public int size() {
		return objects.size();
	}

	public void delete(BookerObject object) {
		objects.delete(object);
		objects.removeDeleteListener(object);
	}

	public void delete(String name) {
		delete(objects.get(name));
	}

	public int indexOf(BookerObject object) {
		return objects.indexOf(object);
	}

	public NamespaceList<BookerObject> getTypeList(String type) {
		return objects.getList(new TypeFilter(type));
	}

	public NamespaceList<BookerObject> getList(NamespaceFilter<BookerObject> filter) {
		return objects.getList(filter);
	}

	public BookerProject copy(){
		BookerProject clone = new BookerProject();
		for (int i = 0; i < size(); i++) {
			clone.add(get(i).copy());
		}
		return clone;
	}
	
	public void addDeleteListener(NamespaceDeleteListener<BookerObject> listener) {
		objects.addDeleteListener(listener);
	}
	
	public void addAddListener(NamespaceAddListener<BookerObject> listener) {
		objects.addAddListener(listener);
	}

	@Override
	public void addLoadListener(NamespaceLoadListener<BookerObject> objectLoadListener) {
		objectLoadListeners.add(objectLoadListener);
	}

	@Override
	public void notifyLoadComplete() {
		for (int i = 0; i < objectLoadListeners.size(); i++) {
			objectLoadListeners.get(i).notifyLoadingComplete(this);
		}
	}
	
	public void write(String fileName) {
		Writer output;
		OutputSequence outputSequence = new OutputSequence();

		for (int i = 0; i < size(); i++) {
			get(i).write(outputSequence);
		}

		try {
			output = new FileWriter(new File(fileName));
			output.write(outputSequence.getString());
			output.close();
			output = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
