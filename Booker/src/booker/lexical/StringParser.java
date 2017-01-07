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

import java.util.Scanner;

public class StringParser implements Parser {

	private String string;
	private StringComparer comparer;

	public StringParser(String string) {
		this.string = string;
		comparer = new CaseSensitive();
	}

	public StringParser(String string, CompareType compareType) {
		this.string = string;
		if (compareType == CompareType.CASE_SENSITIVE) {
			comparer = new CaseSensitive();
		} else if (compareType == CompareType.CASE_INSENSITIVE) {
			comparer = new CaseInsensitive();
		}
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		int mark = in.pos();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter("");
		StringBuilder output = new StringBuilder();

		while (scanner.hasNext()) {
			if (comparer.compare(in.token(), scanner.next())) {
				output = output.append(in.token());
				in.increment();
			} else {
				in.moveTo(mark);
				scanner.close();
				throw new CannotParseException();
			}
		}
		scanner.close();
		return output.toString();
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
