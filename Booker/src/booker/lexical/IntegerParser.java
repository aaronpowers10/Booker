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

public class IntegerParser implements Parser {
	
	private Parser parser;
	
	public IntegerParser(){
		OrParser digitParser = new OrParser(new CharacterParser[]{
				new CharacterParser("0"),
				new CharacterParser("1"),
				new CharacterParser("2"),
				new CharacterParser("3"),
				new CharacterParser("4"),
				new CharacterParser("5"),
				new CharacterParser("6"),
				new CharacterParser("7"),
				new CharacterParser("8"),
				new CharacterParser("9")
		});
		parser = new OneOrMoreParser(digitParser);
	}

	public String parse(InputSequence in) throws CannotParseException{
		return parser.parse(in);
	}

}
