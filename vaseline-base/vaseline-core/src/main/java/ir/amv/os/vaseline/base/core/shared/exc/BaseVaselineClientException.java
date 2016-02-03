package ir.amv.os.vaseline.base.core.shared.exc;

import java.io.Serializable;

public class BaseVaselineClientException extends Exception implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String identifier;

	public BaseVaselineClientException() {
	}

	public BaseVaselineClientException(String messageKey) {
		super(messageKey);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}