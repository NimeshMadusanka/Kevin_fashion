/**
 * @author Uvindu Sanjana
 * KEVIN
 * kevin.model
 * Message.java
 */
package kevin.model;

public class Message {
	
	private int messageId;
	private String userId;
	private String message;
	private String messageDateTime;
	private boolean client;
	private boolean adminSeen;
	private boolean userSeen;
	/**
	 * @return the messageId
	 */
	public int getMessageId() {
		return messageId;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the messageDateTime
	 */
	public String getMessageDateTime() {
		return messageDateTime;
	}
	/**
	 * @param messageDateTime the messageDateTime to set
	 */
	public void setMessageDateTime(String messageDateTime) {
		this.messageDateTime = messageDateTime;
	}
	/**
	 * @return the client
	 */
	public boolean isClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(boolean client) {
		this.client = client;
	}
	/**
	 * @return the adminSeen
	 */
	public boolean isAdminSeen() {
		return adminSeen;
	}
	/**
	 * @param adminSeen the adminSeen to set
	 */
	public void setAdminSeen(boolean adminSeen) {
		this.adminSeen = adminSeen;
	}
	/**
	 * @return the userSeen
	 */
	public boolean isUserSeen() {
		return userSeen;
	}
	/**
	 * @param userSeen the userSeen to set
	 */
	public void setUserSeen(boolean userSeen) {
		this.userSeen = userSeen;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", userId=" + userId + ", message=" + message + ", messageDateTime="
				+ messageDateTime + ", client=" + client + ", adminSeen=" + adminSeen + ", userSeen=" + userSeen + "]";
	}
	
}
