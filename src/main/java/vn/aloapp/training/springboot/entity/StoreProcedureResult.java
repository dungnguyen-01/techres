package vn.aloapp.training.springboot.entity;

import java.math.BigDecimal;
import java.util.List;

public class StoreProcedureResult<T> {

	private int statusCode;
	private String messageError;
	private T result;

	public StoreProcedureResult(int statusCode, String messageError) {
		this.statusCode = statusCode;
		this.messageError = messageError;
	}

	public StoreProcedureResult(int statusCode, String messageError, T result) {
		this.statusCode = statusCode;
		this.messageError = messageError;
		this.result = result;
	}

	public StoreProcedureResult(int statusCode, String messageError, BigDecimal totalAmount, T result) {
		this.statusCode = statusCode;
		this.messageError = messageError;
		this.result = result;
	}
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
