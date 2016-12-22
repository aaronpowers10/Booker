package booker.building_data;

/**
 * 
 * A building object is a building block to an entire building/project definition.  An object has
 * a list of fields which define the properties of that object.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public interface BuildingObject<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> extends HoldsObjectReferences<T,U,V> {

	public String name();

	public String type();

	public default U getField(String name) throws BuildingDataException {
		for (int i = 0; i < numFields(); i++) {
			if (getField(i).name().equals(name)) {
				return getField(i);
			}
		}
		throw new BuildingDataException("'" + name + "' is not a field of '" + name() + "'.");
	}

	public U getField(int index);

	public void addField(U field) throws BuildingDataException;

	public int numFields();

	@Override
	public default boolean dependsOn(T object) {
		for (int i = 0; i < numFields(); i++) {
			if (getField(i).dependsOn(object)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasField(String fieldName);

	public T copy();

}
