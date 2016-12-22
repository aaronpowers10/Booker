package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

import booker.dictionary.DictionaryLookup;

public class DictionaryParser implements Parser{

	private DictionaryLookup dictionary;

	public DictionaryParser(DictionaryLookup dictionary){
		this.dictionary = dictionary;
	}

	@Override
	public String parse(InputSequence in) throws CannotParseException {
		int mark = in.pos();
		StringBuilder word = new StringBuilder();

		boolean continueParsing = true;
		while(continueParsing){
			word.append(in.token());
			in.increment();
			if(!dictionary.contains(word.toString() + in.token())){
				continueParsing = false;
			}
		}

		if(dictionary.isMember(word.toString())){
			return word.toString();
		} else {
			in.moveTo(mark);
			throw new CannotParseException();
		}

	}

}
