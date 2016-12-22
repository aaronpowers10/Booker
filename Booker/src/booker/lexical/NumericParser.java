package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class NumericParser implements Parser {
	private Parser numericParser;

	public NumericParser(){
		CharacterParser plus = new CharacterParser("+");
		CharacterParser minus = new CharacterParser("-");
		Parser unaryOperator = new OptionalParser( new OrParser(new Parser[]{plus,minus}));

		Parser requiredDigits = new IntegerParser();
		Parser optionalDigits = new OptionalParser(new IntegerParser());
		OptionalParser optionalDecimal = new OptionalParser(new CharacterParser("."));
		CharacterParser requiredDecimal = new CharacterParser(".");
		AndParser fractionalParser = new AndParser(new Parser[]{requiredDecimal,requiredDigits});

		AndParser significandForm2 = new AndParser(new Parser[]{requiredDigits,optionalDecimal});
		AndParser significandForm1 = new AndParser(new Parser[]{optionalDigits,fractionalParser});

		OrParser significand = new OrParser(new Parser[]{significandForm1,significandForm2});

		CharacterParser exponential = new CharacterParser("e",CharacterParser.CASE_INSENSITIVE());
		OptionalParser exponent = new OptionalParser(
				new AndParser(new Parser[]{exponential,unaryOperator,requiredDigits}));

		numericParser = new AndParser(new Parser[]{unaryOperator,significand,exponent});
	}

	public String parse(InputSequence in) throws CannotParseException {
		return numericParser.parse(in);
	}

}
