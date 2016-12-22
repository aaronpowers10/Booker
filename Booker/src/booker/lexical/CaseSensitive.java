package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class CaseSensitive implements StringComparer{

	@Override
	public boolean compare(String str1, String str2) {
		if(str1.equals(str2)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean startsWith(String str1, String str2) {
		return str1.contains(str2);
	}

}
