package org.ram.testwebservice.messengers.exception;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ram.testwebservice.messengers.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exp) {
		ErrorMessage errorMessage = new ErrorMessage(exp.getMessage(), 404, "http://javatpoint.com");
		return Response.status(Status.NOT_FOUND).
				entity(errorMessage).
				build();
	}

}
