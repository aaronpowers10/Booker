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

public class AlphaValue
		implements FieldValue {

	private String value;
	private AlphaValueChecker alphaChecker;
	private InputFileWriter writer;

	public AlphaValue(String value) {
		this.value = value;
		alphaChecker = new AllowAnyAlphaChecker();
		writer = new NullWriter();
	}
	
	public AlphaValue(String value, InputFileWriter writer){
		this.value = value;
		this.writer = writer;
		alphaChecker = new AllowAnyAlphaChecker();
	}

	public void setAlphaValueChecker(AlphaValueChecker alphaChecker) {
		this.alphaChecker = alphaChecker;
	}

	public String value() {
		return value;
	}

	@Override
	public void set(String value) throws BookerDataException {
		if (alphaChecker.isAllowed(value)) {
			this.value = value;
		} else {
			throw new BookerDataException("The value " + value + " is not allowed.");
		}
	}

	@Override
	public ValueType type() {
		return ValueType.ALPHA;
	}

	@Override
	public AlphaValue copy() {
		return new AlphaValue(value);
	}


	public void setWriter(InputFileWriter writer){
		this.writer = writer;
	}
	
	@Override
	public void write(OutputSequence out) {
		writer.write(out);
	}

}
