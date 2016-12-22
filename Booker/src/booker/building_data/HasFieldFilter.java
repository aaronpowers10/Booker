package booker.building_data;

/**
 * 
 * Filter which returns a match if the object has a field with the given name.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public class HasFieldFilter<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> implements ObjectFilter<T,U,V>{

	private String fieldName;

	public HasFieldFilter(String fieldName){
		this.fieldName = fieldName;
	}

	@Override
	public boolean filter(T object) throws BuildingDataException {
		if(object.hasField(fieldName)){
			return true;
		} else {
			return false;
		}
	}

}
