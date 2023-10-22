package posts;

import users.User;

/**
 * 
 * A comment has a user (the user who sent it) and a message.
 *
 */
public class Comment {
	User user;
	String message;
	String nickname;
	public Comment(User user,String message) {
		this.user = user;
		this.message = message;
		this.nickname = user.getNickname();
	}
	
	//GETTERS AND SETTERS
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	//TOSTRING
	@Override
	public String toString() {
		return nickname + ": " + message + "\n";
	}
	
	

}
