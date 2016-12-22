package booker.lexical;

/**
 * 
 * @author Aaron Powers
 *
 */

public class CaseInsensitive implements StringComparer {
	@Override
	public boolean compare(String str1, String str2) {
		if(str1.toLowerCase().equals(str2.toLowerCase())){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean startsWith(String str1, String str2) {
		return str1.toLowerCase().startsWith(str2.toLowerCase());
	}
}
