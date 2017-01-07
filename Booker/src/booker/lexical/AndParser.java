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

public class AndParser implements Parser {

	private Parser[] parsers;

	public AndParser(Parser[] parsers) {
		this.parsers = parsers;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		int mark = in.pos();

		for (int i = 0; i < parsers.length; i++) {
			try {
				output.append(parsers[i].parse(in));
			} catch (CannotParseException e) {
				in.moveTo(mark);
				throw new CannotParseException();
			}
		}

		return output.toString();
	}

}
