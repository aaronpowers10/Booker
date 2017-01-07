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

public class NextToListParser implements Parser {

	private Parser[] parsers;

	public NextToListParser(Parser[] parsers) {
		this.parsers = parsers;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		int mark = in.pos();
		for (int i = 0; i < parsers.length; i++) {
			if (i < parsers.length - 1) {
				try {
					parsers[i].parse(in);
					in.moveTo(mark);
					return in.token();
				} catch (CannotParseException e) {
				}
			} else {
				return parsers[i].parse(in);
			}
		}
		throw new CannotParseException();
	}

}
