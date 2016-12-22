package booker.building_data;

/**
 * 
 * Represents a list of building fields.
 * 
 * @author Aaron Powers
 * 
 */

import java.util.ArrayList;

public class FieldList<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> implements Parsable{

	private ArrayList<U> fields;

	public FieldList(){
		fields = new ArrayList<U>();
	}

	public void add(U field){
		fields.add(field);
	}

	public U get(int index){
		return fields.get(index);
	}

	public void remove(int index){
		fields.remove(index);
	}

	public U get(String name) throws BuildingDataException{
		for(int i=0; i<fields.size(); i++){
			if(fields.get(i).name().equals(name)){
				return fields.get(i);
			}
		}
		throw new BuildingDataException();
	}

	public int size(){
		return fields.size();
	}

}
