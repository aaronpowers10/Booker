package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class NextToCharacterParser implements Parser{

	private String character;

	public NextToCharacterParser(String character){
		this.character = character;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		if(in.peek(1).equals(character)){
			String output = in.token();
			in.increment();
			return output;
		} else {
			throw new CannotParseException();
		}
	}

}
