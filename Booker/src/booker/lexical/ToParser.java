package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class ToParser implements Parser {

	private Parser toParser;

	public ToParser(Parser toParser){
		this.toParser = toParser;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		boolean continueParsing = true;
		while(continueParsing){
			try{
				output.append(toParser.parse(in));
				continueParsing = false;
			} catch (CannotParseException e){
				output.append(new AnyCharacterParser().parse(in));
			}
		}
		return output.toString();
	}


}
