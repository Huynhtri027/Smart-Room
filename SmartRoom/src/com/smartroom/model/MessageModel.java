package com.smartroom.model;

import android.graphics.Bitmap;

public class MessageModel {

	private String senderID, message, advertReference, postDate;
	private Bitmap messageIcon;

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAdvertReference() {
		return advertReference;
	}

	public void setAdvertReference(String advertReference) {
		this.advertReference = advertReference;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public Bitmap getMessageIcon() {
		return messageIcon;
	}

	public void setMessageIcon(Bitmap messageIcon) {
		this.messageIcon = messageIcon;
	}

}
