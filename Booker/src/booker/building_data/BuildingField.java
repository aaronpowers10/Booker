package booker.building_data;

/**
 * A generic building field has both a name and a value.  Building fields belong to building objects.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public interface BuildingField<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> extends HoldsObjectReferences<T,U,V>{

	public String name();

	public V value();

	public default ValueType type(){
		return value().type();
	}

	public default GenericNumericValue<T,U,V> getAsGenericNumeric() throws BuildingDataException{
		return value().getAsGenericNumeric();
	}

	public default GenericAlphaValue<T,U,V> getAsGenericAlpha() throws BuildingDataException{
		return value().getAsGenericAlpha();
	}

	public default GenericObjectValue<T,U,V> getAsGenericObject() throws BuildingDataException{
		return value().getAsGenericObject();
	}

	public default void set(double value) throws BuildingDataException{
		value().set(value);
	}

	public default void set(String string) throws BuildingDataException{
		value().set(string);
	}

	public default void set(T object) throws BuildingDataException{
		value().set(object);
	}

	public default void set(int index, double value) throws BuildingDataException{
		value().set(index, value);
	}

	public default void set(int index,String string) throws BuildingDataException{
		value().set(index,string);
	}

	public default void set(int index, T object) throws BuildingDataException{
		value().set(index,object);
	}

	public default void add(V element)throws BuildingDataException{
		value().add(element);
	}

	@Override
	public default boolean dependsOn(T object){
		return value().dependsOn(object);
	}

	public U copy();

}
