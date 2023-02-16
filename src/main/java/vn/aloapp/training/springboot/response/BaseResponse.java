/**
 * 
 */
package vn.aloapp.training.springboot.response;

import org.springframework.http.HttpStatus;

/**
 * @author longnguyen
 *
 */
public class BaseResponse <T> {
	
	private int status;
    private String message;
    private T data;
    
    public BaseResponse() {
		this.setStatus(HttpStatus.OK);
		this.setMessage(HttpStatus.OK);
		this.setData(null);
    }
   

	public BaseResponse(int status) {
		super();
		this.status = status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getStatus() {
		return status;
	}
	public void setStatus(HttpStatus statusEnum) {
		this.status = statusEnum.value();
		this.message = statusEnum.name();
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(HttpStatus statusEnum) {
		this.message = statusEnum.name();
	}
	
	public void setMessageError(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}

}
