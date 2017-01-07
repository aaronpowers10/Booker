package booker.building_data;

/**
 * 
 * A field value which is a string of characters.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public abstract class GenericAlphaValue<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements FieldValue<T, U, V> {

	private String value;
	private AlphaValueChecker alphaChecker;

	public GenericAlphaValue(String value) {
		this.value = value;
		alphaChecker = new AllowAnyAlphaChecker();
	}

	public void setAlphaValueChecker(AlphaValueChecker alphaChecker) {
		this.alphaChecker = alphaChecker;
	}

	public String value() {
		return value;
	}

	@Override
	public void set(String value) throws BuildingDataException {
		if (alphaChecker.isAllowed(value)) {
			this.value = value;
		} else {
			throw new BuildingDataException();
		}
	}

	@Override
	public ValueType type() {
		return GenericValueType.ALPHA;
	}

}
