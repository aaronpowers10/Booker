package booker.building_data;

/**
 * 
 * A field value which is a number.
 * 
 * @author Aaron Powers
 *
 * @param <T>
 * @param <U>
 * @param <V>
 */

public abstract class GenericNumericValue<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements FieldValue<T, U, V> {

	private double value;
	private NumericChecker numericChecker;
	private NumericType numericType;

	public GenericNumericValue(double value) {
		this.value = value;
		numericType = NumericType.REAL;
		numericChecker = new AllowAnyNumericChecker();
	}

	public GenericNumericValue(double value, NumericChecker numericChecker) {
		this.numericChecker = numericChecker;
		set(value);
	}

	public void setChecker(NumericChecker numericChecker) {
		this.numericChecker = numericChecker;
	}

	public double value() {
		return value;
	}

	@Override
	public void set(double value) throws BuildingDataException {
		if (numericChecker.isAllowed(value)) {
			this.value = value;
		} else {
			throw new BuildingDataException();
		}
	}

	@Override
	public ValueType type() {
		return GenericValueType.NUMERIC;
	}

	class AllowAnyNumericChecker implements NumericChecker {
		@Override
		public boolean isAllowed(double value) {
			return true;
		}
	}

}
