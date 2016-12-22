package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class FromToParser implements Parser{

	private Parser fromParser;
	private Parser toParser;

	public FromToParser(Parser fromParser, Parser toParser){
		this.fromParser = fromParser;
		this.toParser = toParser;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		output.append(fromParser.parse(in));
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
