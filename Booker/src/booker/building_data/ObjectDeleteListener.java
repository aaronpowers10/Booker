package booker.building_data;

/**
 * 
 * These objects will be notified when the given object is deleted.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public interface ObjectDeleteListener <T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> {

	public void objectDeleted(T object);

}
