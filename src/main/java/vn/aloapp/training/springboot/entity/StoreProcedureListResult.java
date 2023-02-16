package vn.aloapp.training.springboot.entity;

import java.math.BigDecimal;
import java.util.List;

public class StoreProcedureListResult<T> {

	private int statusCode;
	private String messageError;
	private List<T> result;
	private long totalRecord;

	public StoreProcedureListResult(int statusCode, String messageError) {
		this.statusCode = statusCode;
		this.messageError = messageError;
	}

	public StoreProcedureListResult(int statusCode, String messageError, List<T> result) {
		this.statusCode = statusCode;
		this.messageError = messageError;
		this.result = result;
	}

	public StoreProcedureListResult(int statusCode, String messageError, long totalRecord, List<T> result) {
		this.statusCode = statusCode;
		this.messageError = messageError;
		this.totalRecord = totalRecord;
		this.result = result;
	}

	public StoreProcedureListResult(int statusCode, String messageError, BigDecimal totalAmount, List<T> result) {
		this.statusCode = statusCode;
		this.messageError = messageError;
		this.result = result;
	}

	public StoreProcedureListResult(int statusCode, String messageError, long totalRecord) {
		this.statusCode = statusCode;
		this.messageError = messageError;
		this.totalRecord = totalRecord;
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

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

}
