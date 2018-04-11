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

public class BookerObject implements NamespaceDeleteListener<BookerObject>, Parsable, InputFileWriter, Namespace {

	private String name;
	private String type;
	private FieldList fields;
	private InputFileWriter writer;

	public BookerObject(String type, String name) {
		this.name = name;
		this.type = type;
		fields = new FieldList();
		writer = new NullWriter();
	}

	public BookerObject(String type, String name, InputFileWriter writer) {
		this.name = name;
		this.type = type;
		fields = new FieldList();
		this.writer = writer;
	}
	
	public BookerObject(String type){
		this.type = type;
		this.name = type;
		fields = new FieldList();
		writer = new NullWriter();
	}

	@Override
	public String name() {
		return name;
	}

	public String type() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getReal(String name) {
		fields.get(name);
		return fields.get(name).getAsReal().value();
	}

	public int getInteger(String name) {
		return (int) fields.get(name).getAsReal().value();
	}

	public String getAlpha(String name) {
		return fields.get(name).getAsAlpha().value();
	}

	public BookerObject getObject(String name) {
		return fields.get(name).getAsObject().value();
	}

	public double getReal(String name, int index) {
		return fields.get(name).getAsReal(index).value();
	}

	public int getInteger(String name, int index) {
		return (int) fields.get(name).getAsReal(index).value();
	}

	public String getAlpha(String name, int index) {
		return fields.get(name).getAsAlpha(index).value();
	}

	public void setFields(FieldList fields) {
		this.fields = fields;
	}

	public int size(String name) {
		return ((ListValue) (fields.get(name).value())).size();
	}

	public BookerField getField(int index) {
		return fields.get(index);
	}

	public int numFields() {
		return fields.size();
	}

	public boolean hasField(String fieldName) {
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).name().equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	public BookerObject copy() {
		BookerObject clone = new BookerObject(type, name);
		for (int i = 0; i < numFields(); i++) {
			clone.addField(fields.get(i).copy());
		}
		return clone;
	}

	public BookerField getField(String name) throws BuildingDataException {
		for (int i = 0; i < numFields(); i++) {
			if (getField(i).name().equals(name)) {
				return getField(i);
			}
		}
		throw new BuildingDataException("'" + name + "' is not a field of '" + name() + "'.");
	}

	@Override
	public void itemDeleted(BookerObject object) {
		for (int i = 0; i < numFields(); i++) {
			if (getField(i).value() instanceof ObjectValue) {
				((ObjectValue) getField(i).value()).objectDeleted(object);
			} else if (getField(i).value() instanceof ListValue) {
				((ListValue) getField(i).value()).objectDeleted(object);
			}
		}
	}

	public void setWriter(InputFileWriter writer) {
		this.writer = writer;
	}

	@Override
	public void write(OutputSequence out) {
		writer.write(out);
	}

	public void addField(BookerField field) throws BuildingDataException {
		fields.add(field);
	}

	/**
	 * Deletes the field with the given name. If the field doesn't exist, does
	 * nothing.
	 *
	 * @param fieldName
	 */
	public void deleteField(String fieldName) {
		int deleteIndex = -1;
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).name().equals(fieldName)) {
				deleteIndex = i;
			}
		}
		if (deleteIndex != -1) {
			fields.remove(deleteIndex);
		}
	}

	public void set(String fieldName, double value) {
		getField(fieldName).set(value);
	}

	public void set(String fieldName, String string) {
		getField(fieldName).set(string);
	}

	public void set(String fieldName, BookerObject object) {
		getField(fieldName).set(object);
	}

	public void set(String fieldName, int index, double value) {
		getField(fieldName).set(index, value);
	}

	public void set(String fieldName, int index, String string) {
		getField(fieldName).set(index, string);
	}

	public void set(String fieldName, int index, BookerObject object) {
		getField(fieldName).set(index, object);
	}

	public boolean hasReference(BookerObject object) {
		for (int i = 0; i < fields.size(); i++) {
			try {
				ObjectValue value = fields.get(i).getAsObject();
				if (value.value().equals(object)) {
					return true;
				}
			} catch (BuildingDataException e) {
				try {
					ListValue list = fields.get(i).getAsList();
					for (int j = 0; j < list.size(); j++) {
						try {
							ObjectValue value = list.get(j).getAsObject();
							if (value.value().equals(object)) {
								return true;
							}
						} catch (BuildingDataException e2) {

						}
					}
				} catch (BuildingDataException e2) {

				}
			}
		}
		return false;
	}

}
