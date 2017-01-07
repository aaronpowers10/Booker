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

import booker.dictionary.DictionaryLookup;

public class DictionaryParser implements Parser {

	private DictionaryLookup dictionary;

	public DictionaryParser(DictionaryLookup dictionary) {
		this.dictionary = dictionary;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		int mark = in.pos();
		StringBuilder word = new StringBuilder();

		boolean continueParsing = true;
		while (continueParsing) {
			word.append(in.token());
			in.increment();
			if (!dictionary.contains(word.toString() + in.token())) {
				continueParsing = false;
			}
		}

		if (dictionary.isMember(word.toString())) {
			return word.toString();
		} else {
			in.moveTo(mark);
			throw new CannotParseException();
		}

	}

}
