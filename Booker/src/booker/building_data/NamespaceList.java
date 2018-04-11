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

	public NamespaceList() {
		items = new ArrayList<T>();
		deleteListeners = new ArrayList<NamespaceDeleteListener<T>>();
	}

	public void addDeleteListener(NamespaceDeleteListener<T> deleteListener) {
		deleteListeners.add(deleteListener);
	}

	public void removeDeleteListener(NamespaceDeleteListener<T> deleteListener) {
		deleteListeners.remove(deleteListener);
	}

	public void add(T item) {
		items.add(item);

	}

	public void add(int index, T item) {
		items.add(index, item);
	}

	public void add(T item, AddNamespaceStrategy<T> addStrategy) {
		addStrategy.addToProject(item, this);
	}

	public T get(int index) {
		return items.get(index);
	}

	public T get(String name) throws BuildingDataException {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).name().equals(name)) {
				return items.get(i);
			}
		}
		throw new BuildingDataException();
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

	public int indexOf(T item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				return i;
			}
		}
		return -1;
	}

	public NamespaceList<T> getList(NamespaceFilter<T> filter) throws BuildingDataException {
		NamespaceList<T> list = new NamespaceList<T>();
		for (int i = 0; i < items.size(); i++) {
			if (filter.filter(items.get(i))) {
				list.add(items.get(i));
			}
		}
		return list;
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
