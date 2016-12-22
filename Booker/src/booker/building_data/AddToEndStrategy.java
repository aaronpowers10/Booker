package booker.building_data;

/**
 *
 * Adds a new building object to the end of a building object list.
 *
 * @author Aaron Powers
 *
 */

public class AddToEndStrategy<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> implements AddObjectStrategy<T,U,V>{

	@Override
	public void addToProject(T object, ObjectList<T,U,V> objects) {
		objects.add(object);
	}

}
