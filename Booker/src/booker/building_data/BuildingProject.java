package booker.building_data;

import java.util.ArrayList;

/**
 * 
 * A building project defines an entire building or multiple building descriptions.  It holds a list
 * of building objects.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public abstract class BuildingProject<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> 
	implements ObjectReferences<T,U,V> {
	
	private ObjectList<T,U,V> objects;
	private ObjectList<T,U,V> libraryObjects;
	private ArrayList<ObjectLoadListener<T,U,V>> objectLoadListeners;
	
	public BuildingProject(){
		objects = new ObjectList<T,U,V>();
		libraryObjects = new ObjectList<T,U,V>();
		objectLoadListeners = new ArrayList<ObjectLoadListener<T,U,V>>();
	}

	public void add(T object){
		add(object, new AddToEndStrategy<T,U,V>());
	}
	
	public void addLibraryObject(T object){
		libraryObjects.add(object);
	}

	public void add(T object, AddObjectStrategy<T,U,V> addStrategy){
		objects.add(object,addStrategy);
		objects.addDeleteListener(object);
	}
	
	public void addBeforeFirstInstance(T object, String beforeType){
		AddBeforeFirstInstanceStrategy<T,U,V> addStrategy = new AddBeforeFirstInstanceStrategy<T,U,V>(beforeType);
		add(object,addStrategy);
	}
	
	public void addAfterLastInstance(T object, String afterType){
		AddAfterLastInstanceStrategy<T,U,V> addStrategy = new AddAfterLastInstanceStrategy<T,U,V>(afterType);
		add(object,addStrategy);
	}
	
	public void addBeforeObject(T object, String objectName){
		AddBeforeObjectStrategy<T,U,V> addStrategy = new AddBeforeObjectStrategy<T,U,V>(objectName);
		add(object,addStrategy);
	}
	
	public void addAfterObject(T object, String objectName){
		AddAfterObjectStrategy<T,U,V> addStrategy = new AddAfterObjectStrategy<T,U,V>(objectName);
		add(object,addStrategy);
	}

	public T get(int index){
		return objects.get(index);
	}
	
	public T get(String name){
		return objects.get(name);
	}
	
	public void addObjectDeleteListener(ObjectDeleteListener<T,U,V> deleteListener){
		objects.addDeleteListener(deleteListener);
	}
	
	public void removeObjectDeleteListener(ObjectDeleteListener<T,U,V> deleteListener){
		objects.removeDeleteListener(deleteListener);
	}

	public boolean isMember(String name){
		return objects.isMember(name);
	}

	public int size(){
		return objects.size();
	}

	public void delete(T object){
		objects.delete(object);
		objects.removeDeleteListener(object);
	}
	
	public void delete(String name){
		delete(objects.get(name));
	}

	public int indexOf(T object){
		return objects.indexOf(object);
	}

	public ObjectList<T,U,V> getTypeList(String type){
		return objects.getTypeList(type);
	}

	public ObjectList<T,U,V> getTypeList(String type, ObjectFilter<T,U,V> filter){
		return objects.getTypeList(type,filter);
	}

	public ObjectList<T,U,V> getList(ObjectFilter<T,U,V> filter){
		return objects.getList(filter);
	}

	public abstract BuildingProject<T,U,V> copy();
	
	@Override
	public void addObjectLoadListener(ObjectLoadListener<T,U,V> objectLoadListener) {
		objectLoadListeners.add(objectLoadListener);		
	}

	@Override
	public void notifyObjectLoadComplete() {
		for(int i=0;i<objectLoadListeners.size();i++){
			objectLoadListeners.get(i).notifyObjectLoadingComplete(this);
		}		
	}

}
