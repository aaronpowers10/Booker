package booker.building_data;

/**
 * 
 * Any data which holds a reference to a building object.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public interface HoldsObjectReferences <T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> {

	public boolean dependsOn(T object);

}
