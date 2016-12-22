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

public abstract class GenericObjectValue<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> implements FieldValue<T,U,V>{

	private T value;

	public GenericObjectValue(T object){
		this.value = object;
	}

	public T value(){
		return value;
	}

	public void setValue(T value){
		this.value = value;
	}

	@Override
	public boolean dependsOn(T object){
		if(object.equals(value)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ValueType type() {
		return GenericValueType.OBJECT;
	}

}
