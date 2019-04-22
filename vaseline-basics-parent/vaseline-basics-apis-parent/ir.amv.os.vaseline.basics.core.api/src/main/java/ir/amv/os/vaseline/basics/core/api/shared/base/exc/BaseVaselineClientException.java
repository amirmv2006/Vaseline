package ir.amv.os.vaseline.basics.core.api.shared.base.exc;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.converter.def.IDefaultExceptionConverter;

import java.io.Serializable;

public class BaseVaselineClientException extends Exception implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String identifier;

    /**
	 * all client exceptions have one constructor only cause that's what
	 * {@link IDefaultExceptionConverter#createClientException(Exception, String)} is using.
    */
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