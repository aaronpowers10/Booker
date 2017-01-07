package booker.building_data;

/**
 * 
 * A field value which holds an object reference.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public abstract class GenericObjectValue<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements FieldValue<T, U, V> {

	private T value;
	private ObjectDeleteListener<T,U,V> deleteListener;

	public GenericObjectValue(T object) {
		this.value = object;
		deleteListener = new SetReferenceNullDeleteListener<T,U,V>(this);
	}

	public T value() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public ValueType type() {
		return GenericValueType.OBJECT;
	}
	
	public void objectDeleted(T object){
		deleteListener.objectDeleted(object);
	}
	
	public void setObjectDeleteListener(ObjectDeleteListener<T,U,V> deleteListener){
		this.deleteListener = deleteListener;
	}

}
