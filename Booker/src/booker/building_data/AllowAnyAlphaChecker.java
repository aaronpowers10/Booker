package booker.building_data;

public class AllowAnyAlphaChecker implements AlphaValueChecker{

	@Override
	public boolean isAllowed(String value) {
		return true;
	}

}
