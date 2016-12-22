package booker.building_data;

/**
 *
 * The AddObjectStrategy interface must be implemented to add an object to an object list.
 *
 * @author Aaron Powers
 *
 */

public interface AddObjectStrategy<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>>{

	public void addToProject(T object, ObjectList<T,U,V> objects);
}
