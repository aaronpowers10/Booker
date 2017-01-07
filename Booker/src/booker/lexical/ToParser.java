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

public class ToParser implements Parser {

	private Parser toParser;

	public ToParser(Parser toParser) {
		this.toParser = toParser;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		boolean continueParsing = true;
		while (continueParsing) {
			try {
				output.append(toParser.parse(in));
				continueParsing = false;
			} catch (CannotParseException e) {
				output.append(new AnyCharacterParser().parse(in));
			}
		}
		return output.toString();
	}

}
