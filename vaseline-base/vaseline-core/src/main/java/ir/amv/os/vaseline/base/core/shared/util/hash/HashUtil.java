package ir.amv.os.vaseline.base.core.shared.util.hash;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

	public static String sha1Hash(String str) {
		return DigestUtils.shaHex(str);
	}
	
}
