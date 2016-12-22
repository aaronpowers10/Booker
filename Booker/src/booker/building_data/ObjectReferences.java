package booker.building_data;

/**
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public interface ObjectReferences<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>>  {

	public T get(String name) throws BuildingDataException;
	
	public void addObjectLoadListener(ObjectLoadListener<T,U,V> objectLoadListener);
	
	public void notifyObjectLoadComplete();

}
