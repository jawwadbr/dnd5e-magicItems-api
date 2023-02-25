package com.jawbr.exceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MagicItemsErrorResponse {

	private int status;
	private String message;
	private String timeStamp;
	
	public MagicItemsErrorResponse() {}

	public MagicItemsErrorResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = formatTimestamp(timeStamp);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = formatTimestamp(timeStamp);
	}
	
	private String formatTimestamp(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        return formatter.format(date);
    }
	
}
