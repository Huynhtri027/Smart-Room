package com.smartroom.model;

import java.io.Serializable;

public class PendingMessageModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message = "", messageID = "", sendeID = "",
			advertReferenceNumber = "";
	private boolean notification = false;

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getSendeID() {
		return sendeID;
	}

	public void setSendeID(String sendeID) {
		this.sendeID = sendeID;
	}

	public String getAdvertReferenceNumber() {
		return advertReferenceNumber;
	}

	public void setAdvertReferenceNumber(String advertReferenceNumber) {
		this.advertReferenceNumber = advertReferenceNumber;
	}

}
