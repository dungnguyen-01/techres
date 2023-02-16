/**
 * 
 */
package vn.aloapp.training.springboot.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author longnguyen
 *
 */
public class BaseListDataResponse<T> {

	private int limit;

	@JsonProperty("total_record")
	private long totalRecord;

	private List<T> list;

	public BaseListDataResponse() {
	}

	public BaseListDataResponse(int limit, long totalRecord) {
		super();
		this.limit = limit;
		this.totalRecord = totalRecord;
	}

	/**
	 * @return the data
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the data to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @return the index
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param index the index to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the totalRecord
	 */
	public long getTotalRecord() {
		return totalRecord;
	}

	/**
	 * @param totalRecord the totalRecord to set
	 */
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

}
