package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class OneOrMoreParser implements Parser{

	private Parser parser;

	public OneOrMoreParser(Parser parser){
		this.parser = parser;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		output.append(parser.parse(in));
		boolean continueParsing = true;
		while(continueParsing){
			try{
				output.append(parser.parse(in));
			} catch (CannotParseException e){
				continueParsing = false;
			}
		}
		return output.toString();
	}

}
