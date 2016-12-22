package booker.dictionary;

/**
 * 
 * @author Aaron Powers
 * 
 */

import java.util.ArrayList;

public class EntryList<T extends Entry> {
	private ArrayList<T> entries;

	public EntryList(){
		entries = new ArrayList<T>();
	}

	public void add(T entry){
		entries.add(entry);
	}

	public boolean isMember(String entryName){
		for(int i=0; i<entries.size(); i++){
			if(entries.get(i).isMatch(entryName)){
				return true;
			}
		}
		return false;
	}

	public T getEntry(String entryName){
		for(int i=0; i<entries.size(); i++){
			if(entries.get(i).isMatch(entryName)){
				return entries.get(i);
			}
		}
		return null;
	}

	public boolean contains(String sequence){
		for(int i=0;i<entries.size(); i++){
			if(entries.get(i).startsWith(sequence)){
				return true;
			}
		}
		return false;
	}

	public T getEntry(int index){
		return entries.get(index);
	}

	public int size(){
		return entries.size();
	}

}
