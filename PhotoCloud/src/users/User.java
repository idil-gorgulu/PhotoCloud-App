package users;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import displays.*;
import posts.Post;

public abstract class User {
	
	String nickname;
	char[] password;
	String name;
	String surname;
	int age;
	String email;
	ImageIcon profile_picture;
	String tier;
	ArrayList <Post> posts = new ArrayList <Post>();
	
	

	/** Each user has a nickname, password, name, surname, age, email and profile_picture
	 * 
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 * @param profile_picture
	 */
	public User(String nickname, char[] password, String name, String surname, int age, String email, ImageIcon profile_picture) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.profile_picture = profile_picture;
		this.tier = null;
		//app.getUsers().add(this);
	}	

	/**This method is for changing a password which is in char array form to its string value.
	 * 
	 * @param password
	 * @return
	 */
	public String passwordToString(char[] password) {
		String password_string = "";
		for (int i = 0; i < password.length ; i++) { 
			password_string += password[i];
		}
		return password_string;
	}
	
	/**
	 * User info toString
	 */
	@Override
	public String toString() {
		return  nickname + " " +
				passwordToString(password)  +  " " +
				name + " " +  surname +  " " 
				+ age + " " + email + " ";
	}

	//GETTERS AND SETTERS
	public String getNickname() {
		return nickname;
	}

	public String getName() {
		return name;
	}

	
	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	
	public void setProfile_picture(ImageIcon profile_picture) {
		this.profile_picture = profile_picture;
	}

	public ImageIcon getProfile_picture() {
		return profile_picture;
	}

	public char[] getPassword() {
		return password;
	}

	public String getTier() {
		return tier;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public int getAge() {
		return age;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
	

	
	
	
	
	
	
	
}

