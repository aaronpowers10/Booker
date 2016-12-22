package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class UpToParser implements Parser {

	private Parser upToParser;

	public UpToParser(Parser upToParser){
		this.upToParser = upToParser;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		boolean continueParsing = true;
		while(continueParsing){
			try{
				int mark = in.pos();
				upToParser.parse(in);
				in.moveTo(mark);
				continueParsing = false;
			} catch (CannotParseException e){
				output.append(new AnyCharacterParser().parse(in));
			}
		}
		return output.toString();
	}


}