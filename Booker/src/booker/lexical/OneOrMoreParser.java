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

public class OneOrMoreParser implements Parser {

	private Parser parser;

	public OneOrMoreParser(Parser parser) {
		this.parser = parser;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		output.append(parser.parse(in));
		boolean continueParsing = true;
		while (continueParsing) {
			try {
				output.append(parser.parse(in));
			} catch (CannotParseException e) {
				continueParsing = false;
			}
		}
		return output.toString();
	}

}
