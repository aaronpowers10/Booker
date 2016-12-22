package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class ConsecutiveColumnParser implements Parser{

	private int numColumns;

	public ConsecutiveColumnParser(int numColumns){
		this.numColumns = numColumns;

	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		StringBuilder output = new StringBuilder();
		for(int i=0; i<numColumns; i++){
			output.append(in.token());
			in.increment();
		}
		return output.toString();
	}

}
