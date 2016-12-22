package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class AnyCharacterParser implements Parser{

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		String string = in.token();
		in.increment();
		return string;
	}

}
