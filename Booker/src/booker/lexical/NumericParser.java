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

public class NumericParser implements Parser {
	private Parser numericParser;

	public NumericParser() {
		CharacterParser plus = new CharacterParser("+");
		CharacterParser minus = new CharacterParser("-");
		Parser unaryOperator = new OptionalParser(new OrParser(new Parser[] { plus, minus }));

		Parser requiredDigits = new IntegerParser();
		Parser optionalDigits = new OptionalParser(new IntegerParser());
		OptionalParser optionalDecimal = new OptionalParser(new CharacterParser("."));
		CharacterParser requiredDecimal = new CharacterParser(".");
		AndParser fractionalParser = new AndParser(new Parser[] { requiredDecimal, requiredDigits });

		AndParser significandForm2 = new AndParser(new Parser[] { requiredDigits, optionalDecimal });
		AndParser significandForm1 = new AndParser(new Parser[] { optionalDigits, fractionalParser });

		OrParser significand = new OrParser(new Parser[] { significandForm1, significandForm2 });

		CharacterParser exponential = new CharacterParser("e", CharacterParser.CASE_INSENSITIVE());
		OptionalParser exponent = new OptionalParser(
				new AndParser(new Parser[] { exponential, unaryOperator, requiredDigits }));

		numericParser = new AndParser(new Parser[] { unaryOperator, significand, exponent });
	}

	public String parse(InputSequence in) throws CannotParseException {
		return numericParser.parse(in);
	}

}
