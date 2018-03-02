package ir.amv.os.vaseline.basics.apis.core.shared.util.date;

import java.util.Date;

public class DefaultDateProvider implements IBaseDateProvider {

	@Override
	public Date getCurrentDate() {
		return new Date();
	}

}
