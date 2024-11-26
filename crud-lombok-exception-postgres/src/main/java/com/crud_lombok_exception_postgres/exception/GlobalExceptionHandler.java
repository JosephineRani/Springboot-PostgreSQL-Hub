package com.crud_lombok_exception_postgres.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
		Map<String, Object> errorBody = new HashMap<String, Object>();

		errorBody.put("Message", "Unexpected Error Occur");
		errorBody.put("TimeStamp", LocalDate.now());
		errorBody.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorBody.put("Details", ex.getMessage());

		return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		Map<String, Object> errorBody = new HashMap<String, Object>();

		errorBody.put("Message", "Resource not found");
		errorBody.put("TimeStamp", LocalDate.now());
		errorBody.put("Details", ex.getMessage());
		errorBody.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<Map<String, Object>>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
