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

public class IntegerValue implements FieldValue {

	private int value;
	private InputFileWriter writer;

	public IntegerValue(int value) {
		this.value = value;
		writer = new NullWriter();
	}
	
	public IntegerValue(int value, InputFileWriter writer) {
		this.value = value;
		this.writer = writer;
	}

	public int value() {
		return value;
	}

	@Override
	public void set(int value) throws BookerDataException {
		this.value = value;
	}

	@Override
	public ValueType type() {
		return ValueType.INTEGER;
	}

	@Override
	public IntegerValue copy() {
		return new IntegerValue(value);
	}
	
	public void setWriter(InputFileWriter writer){
		this.writer = writer;
	}
	
	@Override
	public void write(OutputSequence out) {
		writer.write(out);
	}
}