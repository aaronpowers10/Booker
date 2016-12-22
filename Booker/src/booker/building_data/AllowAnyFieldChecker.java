package booker.building_data;

/**
 * 
 * Allows any value to be set.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public class AllowAnyFieldChecker<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> implements FieldChecker<T,U,V>{

	@Override
	public boolean isAllowed(U field, T object) {
		return true;
	}

	@Override
	public String message() {
		return "";
	}

}
