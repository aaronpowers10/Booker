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
import otis.lexical.Parsable;

public interface FieldValue extends Parsable, InputFileWriter{

	public ValueType type();

	
	public default IntegerValue getAsInteger() throws BookerDataException {
		if (this instanceof IntegerValue) {
			return (IntegerValue) this;
		} else {
			throw new BookerDataException("The field is not an integer type.");
		}
	}
	
	public default RealValue getAsReal() throws BookerDataException {
		if (this instanceof RealValue) {
			return (RealValue) this;
		} else {
			throw new BookerDataException("The field is not a real type.");
		}
	}

	public default AlphaValue getAsAlpha() throws BookerDataException {
		if (this instanceof AlphaValue) {
			return (AlphaValue) this;
		} else {
			throw new BookerDataException("The field is not an alpha type.");
		}
	}
	
	public default AlphaValue getAsAlpha(int index) throws BookerDataException{
		if(this.getAsList().get(index) instanceof AlphaValue){
			return (AlphaValue)this.getAsList().get(index);
		} else {
			throw new BookerDataException("The field is not an alpha type.");
		}
	}
	
	public default IntegerValue getAsInteger(int index) throws BookerDataException{
		if(this.getAsList().get(index) instanceof IntegerValue){
			return (IntegerValue)this.getAsList().get(index);
		} else {
			throw new BookerDataException("The field is not an integer type.");
		}
	}
	
	public default RealValue getAsReal(int index) throws BookerDataException{
		if(this.getAsList().get(index) instanceof RealValue){
			return (RealValue)this.getAsList().get(index);
		} else {
			throw new BookerDataException("The field is not a real type.");
		}
	}

	public default ObjectValue getAsObject() throws BookerDataException {
		if (this instanceof ObjectValue) {
			return (ObjectValue) this;
		} else {
			throw new BookerDataException("The field is not an object type.");
		}
	}

	public default ListValue getAsList() throws BookerDataException {
		if (this instanceof ListValue) {
			return (ListValue) this;
		} else {
			throw new BookerDataException("The field is not a list type.");
		}
	}
	
	public default void set(int value) throws BookerDataException {
		if (this instanceof IntegerValue) {
			((IntegerValue) this).set(value);
		} else {
			throw new BookerDataException("The field is not an integer type.");
		}
	}

	public default void set(double value) throws BookerDataException {
		if (this instanceof RealValue) {
			((RealValue) this).set(value);
		} else {
			throw new BookerDataException("The field is not a real type.");
		}
	}

	public default void set(String string) throws BookerDataException {
		if (this instanceof AlphaValue) {
			((AlphaValue) this).set(string);
		} else {
			throw new BookerDataException("The field is not an alpha type.");
		}
	}

	public default void set(BookerObject object) throws BookerDataException {
		if (this instanceof ObjectValue) {
			((ObjectValue) this).setValue(object);
		} else {
			throw new BookerDataException("The field is not an object type.");
		}
	}
	
	public default void set(int index, int value) throws BookerDataException {
		if (this instanceof ListValue) {
			((ListValue) this).set(index, value);
		} else {
			throw new BookerDataException("The field is not a list type.");
		}
	}

	public default void set(int index, double value) throws BookerDataException {
		if (this instanceof ListValue) {
			((ListValue) this).set(index, value);
		} else {
			throw new BookerDataException("The field is not a list type.");
		}
	}

	public default void set(int index, String string) throws BookerDataException {
		if (this instanceof ListValue) {
			((ListValue) this).set(index, string);
		} else {
			throw new BookerDataException("The field is not a list type.");
		}
	}

	public default void set(int index, BookerObject object) throws BookerDataException {
		if (this instanceof ListValue) {
			((ListValue) this).set(index, object);
		} else {
			throw new BookerDataException("The field is not a list type.");
		}
	}

	public default void add(FieldValue element) throws BookerDataException {
		if (this instanceof ListValue) {
			((ListValue) this).add(element);
		} else {
			throw new BookerDataException("The field is not a list type.");
		}
	}

	public FieldValue copy();

}
