package com.jawbr.customResponse;

public class UpdateResponse {
	
	private String id;
	private String url;

	public UpdateResponse(String id, String url) {
		this.id = id;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UpdateResponse [id=" + id + ", url=" + url + "]";
	}
	
}
