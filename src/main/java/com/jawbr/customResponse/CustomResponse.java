package com.jawbr.customResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomResponse {
	
	private int status;
	private String url;
	private String timeStamp;

	public CustomResponse(int status, String url, long timeStamp) {
		this.status = status;
		this.url = url;
		this.timeStamp = formatTimestamp(timeStamp);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "UpdateResponse [url=" + url + ", timestamp=" + timeStamp + "]";
	}
	
	private String formatTimestamp(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss a");
        return formatter.format(date);
    }
	
}
