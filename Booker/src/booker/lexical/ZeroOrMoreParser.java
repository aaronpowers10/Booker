package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class ZeroOrMoreParser implements Parser{

	private OptionalParser zeroOrMoreParser;

	public ZeroOrMoreParser(Parser parser){
		zeroOrMoreParser = new OptionalParser(new OneOrMoreParser(parser));
	}

	@Override
	public String parse(InputSequence in) {
		return zeroOrMoreParser.parse(in);
	}

}
