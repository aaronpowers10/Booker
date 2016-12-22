package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class NextToListParser implements Parser {

	private Parser[] parsers;

	public NextToListParser(Parser[] parsers){
		this.parsers = parsers;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		int mark = in.pos();
		for(int i=0; i<parsers.length; i++){
			if(i < parsers.length - 1){
				try{
					parsers[i].parse(in);
					in.moveTo(mark);
					return in.token();
				} catch (CannotParseException e){
				}
			} else {
				return parsers[i].parse(in);
			}
		}
		throw new CannotParseException();
	}



}
