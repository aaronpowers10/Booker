package booker.building_data;

/**
 * 
 * A field value which represents a list of other values.
 * 
 * @author Aaron Powers
 * 
 */

import java.util.ArrayList;

public abstract class ListValue<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements FieldValue<T, U, V> {

	private ArrayList<V> elements;

	public ListValue() {
		elements = new ArrayList<V>();
	}

	public ListValue(ArrayList<V> elements) {
		this.elements = elements;
	}

	public void add(V element) {
		elements.add(element);
	}

	public V get(int index) {
		return elements.get(index);
	}

	public void set(int index, V element) {
		elements.set(index, element);
	}

	public int size() {
		return elements.size();
	}

	@Override
	public void set(int index, double value) {
		elements.get(index).set(value);
	}

	@Override
	public void set(int index, String string) {
		elements.get(index).set(string);
	}

	@Override
	public void set(int index, T object) {
		elements.get(index).set(object);
	}

	@Override
	public GenericValueType type() {
		return GenericValueType.LIST;
	}
	
	public void objectDeleted(T object){
		for(int i=0;i<size();i++){
			if(get(i) instanceof GenericObjectValue){
				((GenericObjectValue<T,U,V>)get(i)).objectDeleted(object);
			} 
		}
	}

}
