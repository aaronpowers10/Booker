package booker.building_data;

public class SetReferenceNullDeleteListener<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements ObjectDeleteListener<T, U, V> {

	private GenericObjectValue<T, U, V> objectValue;

	public SetReferenceNullDeleteListener(GenericObjectValue<T, U, V> objectValue) {
		this.objectValue = objectValue;
	}

	@Override
	public void objectDeleted(T object) {
		if(objectValue.value()  != null){
			if (objectValue.value().equals(object)) {
				objectValue.setValue(null);
			}
		}

	}

}
