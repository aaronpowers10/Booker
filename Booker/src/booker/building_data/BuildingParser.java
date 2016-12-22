package booker.building_data;
/**
 * 
 * @author Aaron Powers
 * 
 */

import java.util.ArrayList;

import booker.lexical.CannotParseException;
import booker.lexical.InputSequence;

public abstract class BuildingParser <T extends BuildingObject<T,U,V>, U extends BuildingField<T,U,V>, V extends FieldValue<T,U,V>,
									W extends Parsable>{

	private ArrayList<UpdateListener> listeners;

	public BuildingParser(ArrayList<UpdateListener> listeners){
		this.listeners = listeners;
	}

	public void addListener(UpdateListener listener){
		listeners.add(listener);
	}

	public W parseWithMessage(InputSequence in, ObjectReferences<T,U,V> objects) throws CannotParseException{
		W parsable = parse(in,objects);
		notifyListeners();
		return parsable;
	}

	public abstract W parse(InputSequence in, ObjectReferences<T,U,V> objects) throws CannotParseException;

	public abstract String message();

	private void notifyListeners(){
		for(int i=0;i<listeners.size();i++){
			listeners.get(i).update(message());
		}
	}

}
