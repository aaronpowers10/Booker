package booker.building_data;

/**
 * Adds a building object to a building object list just before the first
 * instance of a given type.
 *
 * @author Aaron Powers
 *
 */

public class AddBeforeFirstInstanceStrategy<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements AddObjectStrategy<T, U, V> {

	private String type;

	/**
	 *
	 * @param type
	 *            The new object will be added just before the first instance of
	 *            this type.
	 */
	public AddBeforeFirstInstanceStrategy(String type) {
		this.type = type;
	}

	@Override
	public void addToProject(T object, ObjectList<T, U, V> objects) {
		int objectsSize = objects.size();
		for (int i = 0; i < objectsSize; i++) {
			if (objects.get(i).type().equals(type)) {
				objects.add(i, object);
				break;
			}
		}
	}

}
