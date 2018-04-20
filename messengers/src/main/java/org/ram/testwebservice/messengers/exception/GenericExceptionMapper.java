package org.ram.testwebservice.messengers.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ram.testwebservice.messengers.model.ErrorMessage;


public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exp) {
		ErrorMessage errorMessage = new ErrorMessage(exp.getMessage(), 500, "http://javatpoint.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).
				entity(errorMessage).
				build();
	}

}
