package booker.building_data;

/**
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public class AddAfterObjectStrategy <T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
implements AddObjectStrategy<T, U, V> {
	
private String objectName;

public AddAfterObjectStrategy(String objectName){
this.objectName = objectName;
}

@Override
public void addToProject(T object, ObjectList<T, U, V> objects) {
int objectsSize = objects.size();
for (int i = 0; i < objectsSize; i++) {
	if (objects.get(i).name().equals(objectName)) {
		if(i<objects.size()-1){
			objects.add(i+1, object);
		} else {
			objects.add(object);
		}
		break;
	}
}

}

}
