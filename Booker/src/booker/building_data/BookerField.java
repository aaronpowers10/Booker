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
import otis.lexical.Parsable;

public class BookerField implements Parsable, InputFileWriter{
	
	private String name;
	private FieldValue value;
	private InputFileWriter writer;
	
	public BookerField(String name,FieldValue value){
		this.name = name;
		this.value = value;
		writer = new NullWriter();
	}
	
	public BookerField(String name,FieldValue value, InputFileWriter writer){
		this.name = name;
		this.value = value;
		this.writer = writer;
	}

	public String name() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public FieldValue value() {
		return value;
	}
	
	public void setValue(FieldValue value){
		this.value = value;
	}

	public BookerField copy() {
		return new BookerField(name,value.copy());
	}

	public  ValueType type() {
		return value().type();
	}
	
	public  IntegerValue getAsInteger() {
		return value().getAsInteger();
	}

	public  RealValue getAsReal() {
		return value().getAsReal();
	}

	public  AlphaValue getAsAlpha() {
		return value().getAsAlpha();
	}
	
	public  AlphaValue getAsAlpha(int index){
		return value().getAsAlpha(index);
	}
	
	public  IntegerValue getAsInteger(int index){
		return value().getAsInteger(index);
	}
	
	public  RealValue getAsReal(int index){
		return value().getAsReal(index);
	}

	public  ObjectValue getAsObject() {
		return value().getAsObject();
	}
	
	public  ListValue getAsList(){
		return value().getAsList();
	}

	public  void set(double value) {
		value().set(value);
	}

	public  void set(String string) {
		value().set(string);
	}

	public  void set(BookerObject object) {
		value().set(object);
	}

	public  void set(int index, double value) {
		value().set(index, value);
	}

	public  void set(int index, String string) {
		value().set(index, string);
	}

	public  void set(int index, BookerObject object) {
		value().set(index, object);
	}

	public  void add(FieldValue element) {
		value().add(element);
	}
	
	public void setWriter(InputFileWriter writer){
		this.writer = writer;
	}

	@Override
	public void write(OutputSequence out) {
		writer.write(out);
	}

}
