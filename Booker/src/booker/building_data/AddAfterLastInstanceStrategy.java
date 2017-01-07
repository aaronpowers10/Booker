package booker.building_data;

/**
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public class AddAfterLastInstanceStrategy<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements AddObjectStrategy<T, U, V> {

	private String type;

	public AddAfterLastInstanceStrategy(String type) {
		this.type = type;
	}

	@Override
	public void addToProject(T object, ObjectList<T, U, V> objects) {
		for (int i = objects.size()-1; i > -1; i--) {
			if (objects.get(i).type().equals(type)) {
				if(i < objects.size() - 1){
					objects.add(i + 1,object);
				} else {
					objects.add(object);
				}
				break;
			}
		}
	}
}
