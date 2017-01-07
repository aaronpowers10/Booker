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

package booker.lexical;

public class CharacterParser implements Parser {

	private String character;
	private StringComparer comparer;

	public CharacterParser(String character) {
		this.character = character;
		comparer = new CaseSensitive();
	}

	public CharacterParser(String character, CompareType compareType) {
		this.character = character;
		if (compareType == CompareType.CASE_SENSITIVE) {
			comparer = new CaseSensitive();
		} else if (compareType == CompareType.CASE_INSENSITIVE) {
			comparer = new CaseInsensitive();
		}
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		if (comparer.compare(in.token(), character)) {
			in.increment();
			return character;
		} else {
			throw new CannotParseException();
		}
	}

	private enum CompareType {
		CASE_SENSITIVE, CASE_INSENSITIVE;
	}

	public static CompareType CASE_SENSITIVE() {
		return CompareType.CASE_SENSITIVE;
	}

	public static CompareType CASE_INSENSITIVE() {
		return CompareType.CASE_INSENSITIVE;
	}

}
