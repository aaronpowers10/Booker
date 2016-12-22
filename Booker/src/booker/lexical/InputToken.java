package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class InputToken {

	private int col;
	private int lineNumber;
	private String token;

	public InputToken(String token, int lineNumber, int col){
		this.token = token;
		this.lineNumber = lineNumber;
		this.col = col;
	}


	public int col(){
		return col;
	}

	public int lineNumber(){
		return lineNumber;
	}

	public String token(){
		return token;
	}

}
