/*
 *
 *  Copyright (C) 2017 Aaron Powers
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

import booker.io.InputFileWriter;
import booker.io.NullWriter;
import booker.io.OutputSequence;

public class ListValue implements FieldValue {

	private ArrayList<FieldValue> elements;
	private InputFileWriter writer;

	public ListValue() {
		elements = new ArrayList<FieldValue>();
		writer = new NullWriter();
	}
	
	public ListValue(InputFileWriter writer) {
		elements = new ArrayList<FieldValue>();
		this.writer = writer;
	}

	public ListValue(ArrayList<FieldValue> elements) {
		this.elements = elements;
		writer = new NullWriter();
	}

	public void add(FieldValue element) {
		elements.add(element);
	}

	public FieldValue get(int index) {
		return elements.get(index);
	}

	public void set(int index, FieldValue element) {
		elements.set(index, element);
	}

	public int size() {
		return elements.size();
	}

	@Override
	public void set(int index, double value) {
		elements.get(index).set(value);
	}

	@Override
	public void set(int index, String string) {
		elements.get(index).set(string);
	}

	@Override
	public void set(int index, BookerObject object) {
		elements.get(index).set(object);
	}
	
	public void setValue(int index, FieldValue value){
		elements.set(index, value);
	}

	@Override
	public ValueType type() {
		return ValueType.LIST;
	}

	public void objectDeleted(BookerObject object) {
		for (int i = 0; i < size(); i++) {
			if (get(i) instanceof ObjectValue) {
				((ObjectValue) get(i)).objectDeleted(object);
			}
		}
	}

	@Override
	public FieldValue copy() {
		ListValue copyValue = new ListValue();
		for (int i = 0; i < size(); i++) {
			copyValue.add(get(i).copy());
		}
		return copyValue;
	}

	public void setWriter(InputFileWriter writer){
		this.writer = writer;
	}
	
	@Override
	public void write(OutputSequence out) {
		writer.write(out);
	}

}
