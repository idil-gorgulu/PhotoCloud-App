package posts;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import displays.MyProfile;
import users.User;

public class Post {
	
	User user;
	String path;
	boolean is_public;
	int likes = 0;
	int dislikes = 0;
	ArrayList<Comment> comments;
	ImageIcon imageicon;
	String caption;
	
	/** A post object has the user who has created the post, a path to the image it represents,
	 * a boolean is_public value to determined whether it is public or private and an imageicon
	 * of the image it represents. It also has comment objects which are stored in an arraylist.
	 * 
	 * @param user
	 * @param path
	 * @param is_public
	 * @param imageicon
	 */
	public Post(User user, String path, boolean is_public,ImageIcon imageicon) {
		this.user = user;
		this.path = path;
		this.is_public = is_public;
		this.imageicon = imageicon;
		this.comments = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	/**A post can optionally have a caption that can be determined by the owner of the post.
	 * 
	 * @param user
	 * @param path
	 * @param is_public
	 * @param imageicon
	 * @param caption
	 */
	public Post(User user, String path, boolean is_public,ImageIcon imageicon, String caption) {
		this.user = user;
		this.path = path;
		this.is_public = is_public;
		this.imageicon = imageicon;
		this.caption = caption;
		this.comments = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	
	//GETTERS AND SETTERS
	public String getPath() {
		return path;
	}

	public ImageIcon getImageIcon() {
		return imageicon;
	}


	public int getLikes() {
		return likes;
	}


	public int getDislikes() {
		return dislikes;
	}


	public String getComments() {
		String s = "";
		if (comments!= null) {
			for (Comment c: comments) {
				s += c.toString() + "\n";
			}
		}
		return s;
	}
	
	public void addComment(Comment c) {
		comments.add(c);
	}


	public boolean getIs_public() {
		return is_public;
	}


	public void setIs_public(boolean is_public) {
		this.is_public = is_public;
	}


	public User getUser() {
		return user;
	}


	public void setImageicon(ImageIcon imageicon) {
		this.imageicon = imageicon;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}


	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	
}
