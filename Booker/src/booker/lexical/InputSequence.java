package booker.lexical;

/**
 * @author Aaron Powers
 */

import java.util.ArrayList;

public class InputSequence {

	private ArrayList<InputToken> chars;
	private int readIndex;
	private int maxColumn;
	private int lastColumn;
	private int lastLine;
	private MoreCharactersSource moreChars;

	public InputSequence(int maxColumn){
		this.maxColumn = maxColumn;
		chars = new ArrayList<InputToken>();
		readIndex = 0;
		lastColumn = 1;
		lastLine = 1;
		moreChars = new NullCharactersSource();
	}

	public InputSequence(String fileName, int maxColumn) {
		this.maxColumn = maxColumn;
		chars = new ArrayList<InputToken>();
		lastColumn = 1;
		lastLine = 1;
		readIndex = 0;
		moreChars = new FileLink(fileName);
	}

	public void addChar(String newChar){
		if (lastColumn <= maxColumn || newChar.equals("\r") || newChar.equals("\n")) {
			chars.add(new InputToken(newChar,lastLine,lastColumn));
		}

		if (newChar.equals("\n")) {
			lastColumn = 1;
			lastLine++;
		} else {
			lastColumn++;
		}
	}

	public void reset(){
		readIndex = 0;
	}

	protected String token() {
		while(readIndex > chars.size() -1){
			addChar(moreChars.nextChar());
		}
		return chars.get(readIndex).token();
	}

	public int lineNumber() {
		return chars.get(readIndex).lineNumber();
	}

	public int col(){
		return chars.get(readIndex).col();
	}

	protected void increment() {
		readIndex++;
	}

	protected String peek(int offset){
		return chars.get(readIndex + offset).token();
	}

	protected int pos(){
		return readIndex;
	}

	protected void moveTo(int pos){
		this.readIndex = pos;
	}

	public int maxColumn(){
		return maxColumn;
	}

	class NullCharactersSource implements MoreCharactersSource{

		@Override
		public String nextChar() {
			throw new EndOfSequenceException("The end of the sequence was reached.");
		}

	}

}
