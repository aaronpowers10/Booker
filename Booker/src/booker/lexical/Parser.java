package booker.lexical;

/**
 * 
 * @author Aaron Powers
 * 
 */

import java.util.Scanner;

public interface Parser {

	public String parse(InputSequence in) throws CannotParseException;

	public default InputSequence parseAsInputSequence(InputSequence in) throws CannotParseException{
		InputSequence resultSequence = new InputSequence(in.maxColumn());
		String parserResult = parse(in);
		Scanner scanner = new Scanner(parserResult);
		scanner.useDelimiter("");
		while(scanner.hasNext()){
			resultSequence.addChar(scanner.next());
		}
		scanner.close();

		return resultSequence;

	}

}
