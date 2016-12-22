package booker.lexical;

/**
 * 
 * @author Aaron Powers
 * 
 */

import java.util.Scanner;

public class StringParser implements Parser{

	private String string;
	private StringComparer comparer;

	public StringParser(String string){
		this.string = string;
		comparer = new CaseSensitive();
	}

	public StringParser(String string, CompareType compareType){
		this.string = string;
		if(compareType == CompareType.CASE_SENSITIVE){
			comparer = new CaseSensitive();
		} else if (compareType == CompareType.CASE_INSENSITIVE){
			comparer = new CaseInsensitive();
		}
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException{
		int mark = in.pos();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter("");
		StringBuilder output = new StringBuilder();

		while(scanner.hasNext()){
			if(comparer.compare(in.token(), scanner.next())){
				output = output.append(in.token());
				in.increment();
			} else {
				in.moveTo(mark);
				scanner.close();
				throw new CannotParseException();
			}
		}
		scanner.close();
		return output.toString();
	}

	private enum CompareType{
		CASE_SENSITIVE, CASE_INSENSITIVE;
	}

	public static CompareType CASE_SENSITIVE(){
		return CompareType.CASE_SENSITIVE;
	}

	public static CompareType CASE_INSENSITIVE(){
		return CompareType.CASE_INSENSITIVE;
	}

}
