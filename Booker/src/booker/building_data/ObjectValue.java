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

import booker.io.InputFileWriter;
import booker.io.NullWriter;
import booker.io.OutputSequence;

public class ObjectValue
		implements FieldValue {

	private BookerObject value;
	private NamespaceDeleteListener deleteListener;
	private InputFileWriter writer;

	public ObjectValue(BookerObject object) {
		this.value = object;
		deleteListener = new SetReferenceNullDeleteListener(this);
		writer = new NullWriter();
	}
	
	public ObjectValue(BookerObject object, InputFileWriter writer) {
		this.value = object;
		this.writer = writer;
		deleteListener = new SetReferenceNullDeleteListener(this);
	}

	public BookerObject value() {
		return value;
	}

	public void setValue(BookerObject value) {
		this.value = value;
	}

	@Override
	public ValueType type() {
		return ValueType.OBJECT;
	}

	public void objectDeleted(BookerObject object) {
		deleteListener.itemDeleted(object);
	}

	public void setObjectDeleteListener(NamespaceDeleteListener deleteListener) {
		this.deleteListener = deleteListener;
	}

	@Override
	public ObjectValue copy() {
		ObjectValue clone = new ObjectValue(value);
		return clone;
	}
	
	public void setWriter(InputFileWriter writer){
		this.writer = writer;
	}
	
	@Override
	public void write(OutputSequence out) {
		writer.write(out);
	}

}
