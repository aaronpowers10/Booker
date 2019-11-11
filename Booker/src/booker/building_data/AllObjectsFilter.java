package booker.building_data;

public class AllObjectsFilter implements NamespaceFilter<BookerObject>{

	@Override
	public boolean filter(BookerObject item) {
		return true;
	}

}
