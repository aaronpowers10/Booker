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

public class NamespaceList<T extends Namespace> {

	private ArrayList<T> items;
	private ArrayList<NamespaceDeleteListener<T>> deleteListeners;
	private ArrayList<NamespaceAddListener<T>> addListeners;

	public NamespaceList() {
		items = new ArrayList<T>();
		deleteListeners = new ArrayList<NamespaceDeleteListener<T>>();
		addListeners = new ArrayList<NamespaceAddListener<T>>();
	}

	public void addDeleteListener(NamespaceDeleteListener<T> deleteListener) {
		deleteListeners.add(deleteListener);
	}

	public void removeDeleteListener(NamespaceDeleteListener<T> deleteListener) {
		deleteListeners.remove(deleteListener);
	}
	
	public void addAddListener(NamespaceAddListener<T> addListener) {
		addListeners.add(addListener);
	}

	public void removeAddListener(NamespaceAddListener<T> addListener) {
		addListeners.remove(addListener);
	}

	public void add(T item) {
		items.add(item);
		notifyAddListeners(item);
	}

	public void add(int index, T item) {
		items.add(index, item);
		notifyAddListeners(item);
	}

	public void add(T item, AddNamespaceStrategy<T> addStrategy) {
		addStrategy.addToProject(item, this);
		notifyAddListeners(item);
	}

	public T get(int index) {
		return items.get(index);
	}

	public T get(String name) throws BookerDataException {
		
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).name().equals(name)) {
				return items.get(i);
			}
		}
		throw new BookerDataException(name + " is not a member of the namespace.");
	}

	public boolean isMember(String name) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).name().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return items.size();
	}

	public void delete(T item) {
		int deleteIndex = -1;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				deleteIndex = i;
			}
		}
		items.remove(deleteIndex);
		notifyDeleteListeners(item);
	}

//	public void delete(String name) {
//		delete(get(name));
//	}

	private void notifyDeleteListeners(T item) {
		for (int i = 0; i < deleteListeners.size(); i++) {
			deleteListeners.get(i).itemDeleted(item);
		}
	}
	
	private void notifyAddListeners(T item) {
		for(int i=0;i<addListeners.size();i++) {
			addListeners.get(i).itemAdded(item);
		}
	}

	public int indexOf(T item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				return i;
			}
		}
		return -1;
	}

	public NamespaceList<T> getList(NamespaceFilter<T> filter) throws BookerDataException {
		NamespaceList<T> list = new NamespaceList<T>();
		for (int i = 0; i < items.size(); i++) {
			if (filter.filter(items.get(i))) {
				list.add(items.get(i));
			}
		}
		return list;
	}
	
	public ArrayList<T> getAsArrayList(){
		return items;
	}
	
	public static <T extends Namespace> NamespaceList<T> create(NamespaceList<T> list) {
		NamespaceList<T> objectList = new NamespaceList<T>();
		for (int i = 0; i < list.size(); i++) {
			objectList.add(list.get(i));
		}
		return objectList;
	}

//	public ObjectList copy() {
//		ObjectList copyList = new ObjectList();
//		for (int i = 0; i < copyList.size(); i++) {
//			copyList.add(objects.get(i).copy());
//		}
//		return copyList;
//	}

}
