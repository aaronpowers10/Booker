package booker.building_data;

/**
 *
 * @author Aaron Powers
 *
 */

public interface ObjectFilter<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> {

	/**
	 * Returns true if the object passes the filter.
	 *
	 * @param object
	 * @return
	 */
	public boolean filter(T object) throws BuildingDataException;

}
