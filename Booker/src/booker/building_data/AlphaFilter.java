package booker.building_data;

/**
 * 
 * Filter which matches if an object has an alpha field with the given name with the given value.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public class AlphaFilter<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> implements ObjectFilter<T,U,V> {
	
	private String fieldName;
	private String matchString;
	
	public AlphaFilter(String fieldName, String matchString){
		this.fieldName = fieldName;
		this.matchString = matchString;
	}

	@Override
	public boolean filter(T object) throws BuildingDataException {
		if(object.hasField(fieldName)){
			U field = object.getField(fieldName);
			if(field.getAsGenericAlpha().value().equals(matchString)){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
