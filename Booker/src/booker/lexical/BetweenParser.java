package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class BetweenParser implements Parser{

	private Parser fromParser;
	private Parser toParser;

	public BetweenParser(Parser fromParser, Parser toParser){
		this.fromParser = fromParser;
		this.toParser = toParser;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		fromParser.parse(in);
		boolean continueParsing = true;
		while(continueParsing){
			try{
				toParser.parse(in);
				continueParsing = false;
			} catch (CannotParseException e){
				output.append(new AnyCharacterParser().parse(in));
			}
		}
		return output.toString();
	}

}
