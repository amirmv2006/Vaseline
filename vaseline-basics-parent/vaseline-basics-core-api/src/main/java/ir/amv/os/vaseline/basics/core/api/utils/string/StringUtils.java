package ir.amv.os.vaseline.basics.core.api.utils.string;

public class StringUtils {

	public static String removeTailString(String mainStr, String toBeRemoved) {
		String substring = mainStr.substring(0, mainStr.length()
				- toBeRemoved.length());
		return substring;
	}
	
	public static int countToken(String str, String sub) { // copied from Spring!
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}
}
