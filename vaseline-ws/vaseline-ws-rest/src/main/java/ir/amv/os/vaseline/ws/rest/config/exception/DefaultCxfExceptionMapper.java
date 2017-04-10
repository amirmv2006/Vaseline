package ir.amv.os.vaseline.ws.rest.config.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DefaultCxfExceptionMapper implements ExceptionMapper<Exception> {

//	public static class CxfErrorObject {
//		private String errorMessage;
//		private Boolean success = false;
//		public CxfErrorObject() {
//		}
//		public CxfErrorObject(String errorMessage) {
//			super();
//			this.errorMessage = errorMessage;
//		}
//		public String getErrorMessage() {
//			return errorMessage;
//		}
//		public void setErrorMessage(String errorMessage) {
//			this.errorMessage = errorMessage;
//		}
//		public Boolean getSuccess() {
//			return success;
//		}
//		public void setSuccess(Boolean success) {
//			this.success = success;
//		}
//	}
	@Override
	public Response toResponse(Exception exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception).build();
	}

}
