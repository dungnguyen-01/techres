package vn.aloapp.training.springboot.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectList<T> {
	@JsonProperty("total_record")
	private int totalRecord;

	@JsonProperty("list")
	private List<T> list;

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
