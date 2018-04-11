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

public class RealValue
		implements FieldValue {

	private double value;
	private RealChecker realChecker;
	private InputFileWriter writer;

	public RealValue(double value) {
		this.value = value;
		realChecker = new AllowAnyNumericChecker();
		writer = new NullWriter();
	}
	
	public RealValue(double value, InputFileWriter writer) {
		this.value = value;
		realChecker = new AllowAnyNumericChecker();
		this.writer = writer;
	}

	public RealValue(double value, RealChecker realChecker) {
		this.realChecker = realChecker;
		set(value);
	}

	public void setChecker(RealChecker realChecker) {
		this.realChecker = realChecker;
	}

	public double value() {
		return value;
	}

	@Override
	public void set(double value) throws BuildingDataException {
		if (realChecker.isAllowed(value)) {
			this.value = value;
		} else {
			throw new BuildingDataException();
		}
	}

	@Override
	public ValueType type() {
		return ValueType.REAL;
	}

	class AllowAnyNumericChecker implements RealChecker {
		@Override
		public boolean isAllowed(double value) {
			return true;
		}
	}

	@Override
	public RealValue copy() {
		return new RealValue(value);
	}
	
	public void setWriter(InputFileWriter writer){
		this.writer = writer;
	}
	
	@Override
	public void write(OutputSequence out) {
		writer.write(out);
	}

}
