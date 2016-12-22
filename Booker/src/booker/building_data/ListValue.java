package booker.building_data;

/**
 * 
 * A field value which represents a list of other values.
 * 
 * @author Aaron Powers
 * 
 */

import java.util.ArrayList;

public abstract class ListValue <T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> implements FieldValue<T,U,V>{

	private ArrayList<V> elements;

	public ListValue(){
		elements = new ArrayList<V>();
	}

	public ListValue(ArrayList<V> elements){
		this.elements = elements;
	}

	public void add(V element){
		elements.add(element);
	}

	public V get(int index){
		return elements.get(index);
	}

	public int size(){
		return elements.size();
	}

	public void set(int index, double value) throws BuildingDataException{
		elements.get(index).set(value);
	}

	public void set(int index, String string) throws BuildingDataException{
		elements.get(index).set(string);
	}

	public void set(int index, T object) throws BuildingDataException{
		elements.get(index).set(object);
	}


	@Override
	public GenericValueType type() {
		return GenericValueType.LIST;
	}

	@Override
	public boolean dependsOn(T object) {
		for(int i=0; i<elements.size(); i++){
			if(elements.get(i).dependsOn(object)){
				return true;
			}
		}
		return false;
	}

}
