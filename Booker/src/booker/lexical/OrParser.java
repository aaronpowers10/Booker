package booker.lexical;
/**
 * 
 * @author Aaron Powers
 *
 */

public class OrParser implements Parser {

	private Parser[] parsers;

	public OrParser(Parser[] parsers){
		this.parsers = parsers;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		for(int i=0;i<parsers.length; i++){
			try{
				return parsers[i].parse(in);
			} catch (CannotParseException e){
			}
		};
		throw new CannotParseException();
	}


}
