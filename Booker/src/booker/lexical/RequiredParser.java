package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class RequiredParser implements Parser {

	private Parser parser;
	private String message;

	private RequiredParser(Parser parser, String message){
		this.parser = parser;
		this.message = message;
	}

	@Override
	public String parse(InputSequence in){
		try{
			return parser.parse(in);
		} catch (CannotParseException e){
			throw new SyntaxException("Syntax error on line " + in.lineNumber() + ".  " + message);
		}
	}

	public static Parser wrap(Parser parser, String message){
		return new RequiredParser(parser, message);
	}

}
