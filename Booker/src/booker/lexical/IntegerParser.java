package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

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
