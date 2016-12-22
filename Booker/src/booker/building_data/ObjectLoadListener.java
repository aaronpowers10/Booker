package booker.building_data;

public interface ObjectLoadListener<T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>> {
	
	public void notifyObjectLoadingComplete(ObjectReferences<T,U,V> objects);

}
