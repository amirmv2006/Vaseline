package ir.amv.os.vaseline.basics.apis.core.shared.base.exc;

import java.io.Serializable;

public class BaseVaselineClientException extends Exception implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String identifier;

    /*
    planning to make all client exceptions have one constructor only cause that's what
    ir.amv.os.vaseline.basics.apis.core.server.base.exc.converter.defimpl.IBaseImplementedExceptionConverter.createClientException
    is using anyway
    */
	//	@Deprecated
//	public BaseVaselineClientException() {
//	}

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