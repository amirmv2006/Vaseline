package ir.amv.os.vaseline.base.core.shared.util.string;

public class StringUtils {

	public static String removeTailString(String mainStr, String toBeRemoved) {
		String substring = mainStr.substring(0, mainStr.length()
				- toBeRemoved.length());
		return substring;
	}
	
	public static int countToken(String str, String token) {
		return org.springframework.util.StringUtils.countOccurrencesOf(str, token);
	}
}
