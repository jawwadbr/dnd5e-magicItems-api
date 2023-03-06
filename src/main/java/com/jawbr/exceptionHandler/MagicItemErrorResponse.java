package com.jawbr.exceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MagicItemErrorResponse {

	private int status;
	private String message;
	private String timeStamp;
	
	public MagicItemErrorResponse() {}

	public MagicItemErrorResponse(int status, String message, long timeStamp) {
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
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss a");
        return formatter.format(date);
    }
	
}
