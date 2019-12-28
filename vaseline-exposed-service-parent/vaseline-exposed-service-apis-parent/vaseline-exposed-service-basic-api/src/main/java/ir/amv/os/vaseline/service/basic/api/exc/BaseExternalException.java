package ir.amv.os.vaseline.service.basic.api.exc;

import java.io.Serializable;

public class BaseExternalException extends Exception implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String identifier;

    /**
	 * all client exceptions have one constructor only cause that's what.
    */
	public BaseExternalException(String messageKey) {
		super(messageKey);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}