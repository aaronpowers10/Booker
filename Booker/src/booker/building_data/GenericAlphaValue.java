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

public abstract class GenericAlphaValue<T extends BuildingObject<T, U, V>, U extends BuildingField<T, U, V>, V extends FieldValue<T, U, V>>
		implements FieldValue<T, U, V> {

	private String value;
	private AlphaValueChecker alphaChecker;

	public GenericAlphaValue(String value) {
		this.value = value;
		alphaChecker = new AllowAnyAlphaChecker();
	}

	public void setAlphaValueChecker(AlphaValueChecker alphaChecker) {
		this.alphaChecker = alphaChecker;
	}

	public String value() {
		return value;
	}

	@Override
	public void set(String value) throws BuildingDataException {
		if (alphaChecker.isAllowed(value)) {
			this.value = value;
		} else {
			throw new BuildingDataException();
		}
	}

	@Override
	public ValueType type() {
		return GenericValueType.ALPHA;
	}

}
