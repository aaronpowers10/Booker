package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class CharacterParser implements Parser {

	private String character;
	private StringComparer comparer;

	public CharacterParser(String character){
		this.character = character;
		comparer = new CaseSensitive();
	}

	public CharacterParser(String character, CompareType compareType){
		this.character = character;
		if(compareType == CompareType.CASE_SENSITIVE){
			comparer = new CaseSensitive();
		} else if (compareType == CompareType.CASE_INSENSITIVE){
			comparer = new CaseInsensitive();
		}
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException{
		if(comparer.compare(in.token(), character)){
			in.increment();
			return character;
		} else {
			throw new CannotParseException();
		}
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
