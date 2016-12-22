package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class OptionalParser implements Parser {

	private Parser parser;

	public OptionalParser(Parser parser){
		this.parser = parser;
	}

	@Override
	public String parse(InputSequence in) {
		try{
			return parser.parse(in);
		} catch (CannotParseException e){
			return "";
		}
	}

	public static Parser wrap(Parser parser){
		return new OptionalParser(parser);
	}

}
