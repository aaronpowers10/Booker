/*
 *
 *  Copyright (C) 2018 Aaron Powers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package booker.building_data;

import java.util.ArrayList;

public class TypeFilter implements NamespaceFilter<BookerObject> {
	
	private String[] types;
	
	public TypeFilter(String... types){
		this.types = types;
	}
	
	public TypeFilter(ArrayList<String> types) {
		this.types = new String[types.size()];
		this.types = types.toArray(this.types);
	}

	@Override
	public boolean filter(BookerObject object) throws BookerDataException {
		if(checkVsTypes(object.type())){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkVsTypes(String type) {
		for(int i=0;i<types.length;i++) {
			if(types[i].equals(type)) {
				return true;
			}
		}
		return false;
	}

}