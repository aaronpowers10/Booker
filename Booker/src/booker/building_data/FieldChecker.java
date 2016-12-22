package booker.building_data;

/**
 * 
 * Checks if a field is allowed for a particular object.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public interface FieldChecker<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> {

	public boolean isAllowed(U field, T Object);

	public String message();

}
