package booker.building_data;

/**
 * 
 * Represents a list of building objects.
 * 
 * @author Aaron Powers
 */

import java.util.ArrayList;

public class ObjectList<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> {

	private ArrayList<T> objects;
	private ArrayList<ObjectDeleteListener<T,U,V>> deleteListeners;

	public ObjectList() {
		objects = new ArrayList<T>();
		deleteListeners = new ArrayList<ObjectDeleteListener<T,U,V>>();
	}
	
	public void addDeleteListener(ObjectDeleteListener<T,U,V> deleteListener){
		deleteListeners.add(deleteListener);
	}
	
	public void removeDeleteListener(ObjectDeleteListener<T,U,V> deleteListener){
		deleteListeners.remove(deleteListener);
	}

	public void add(T object) {
		objects.add(object);
		
	}

	public void add(int index, T object) {
		objects.add(index, object);
	}

	public void add(T object, AddObjectStrategy<T,U,V> addStrategy) {
		addStrategy.addToProject(object, this);
	}

	public T get(int index) {
		return objects.get(index);
	}

	public T get(String name) throws BuildingDataException{
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).name().equals(name)) {
				return objects.get(i);
			}
		}
		throw new BuildingDataException();
	}

	public boolean isMember(String name) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).name().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return objects.size();
	}

	public void delete(T object) {
		int deleteIndex = -1;
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).equals(object)) {
				deleteIndex = i;
			}
		}
		objects.remove(deleteIndex);
		notifyDeleteListeners(object);
	}
	
	public void delete(String name){
		delete(get(name));
	}

	private void notifyDeleteListeners(T object) {
		for (int i = 0; i < deleteListeners.size(); i++) {
			deleteListeners.get(i).objectDeleted(object);
		}
	}

	public int indexOf(T object) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).equals(object)) {
				return i;
			}
		}
		return -1;
	}

	public ObjectList<T,U,V> getTypeList(String type) {
		ObjectList<T,U,V> typeList = new ObjectList<T,U,V>();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).type().equals(type)) {
				typeList.add(objects.get(i));
			}
		}
		return typeList;
	}

	public ObjectList<T,U,V> getTypeList(String type, ObjectFilter<T,U,V> filter) throws BuildingDataException{
		ObjectList<T,U,V> typeList = new ObjectList<T,U,V>();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).type().equals(type)) {
				if (filter.filter(objects.get(i))) {
					typeList.add(objects.get(i));
				}
			}
		}
		return typeList;
	}

	public ObjectList<T,U,V> getList(ObjectFilter<T,U,V> filter) throws BuildingDataException{
		ObjectList<T,U,V> list = new ObjectList<T,U,V>();
		for (int i = 0; i < objects.size(); i++) {
			if (filter.filter(objects.get(i))) {
				list.add(objects.get(i));
			}
		}
		return list;
	}

	public ObjectList<T,U,V> copy(){
		ObjectList<T,U,V> copyList = new ObjectList<T,U,V>();
		for(int i=0;i<copyList.size();i++){
			copyList.add(objects.get(i).copy());
		}
		return copyList;
	}

}
