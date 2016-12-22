package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */
public class AndParser implements Parser{

	private Parser[] parsers;

	public AndParser(Parser[] parsers){
		this.parsers = parsers;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		int mark = in.pos();

		for(int i=0; i<parsers.length; i++){
			try{
				output.append(parsers[i].parse(in));
			} catch (CannotParseException e){
				in.moveTo(mark);
				throw new CannotParseException();
			}
		}

		return output.toString();
	}

}
