/************** Pledge of Honor ******************************************

I hereby certify that I have completed this programming project on my own 
without any help from anyone else. The effort in the project thus belongs 
completely to me. I did not search for a solution, or I did not consult any 
program written by others or did not copy any program from other sources. 
I read and followed the guidelines provided in the project description.

SIGNATURE: <İdil Görgülü, 79323> 

*************************************************************************/
package displays;

import java.awt.Choice;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

import logging.BaseLogger;
import posts.Post;
import users.*;

public class PhotoCloudApp {
	
	ImageIcon default_= new ImageIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/Defaultpp.jpg");
	Image resized_image = default_.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	ImageIcon default_pp  = new ImageIcon(resized_image);
	
	String password_1 = "12345678";
	String password_2 = "password1234";
	String password_3 = "comp132project";
	
	
	User admin = new Administrator("user_123", password_1.toCharArray(), "Unknown", "Admin", 35, "admin@mail.com", default_pp);
	
	User idil = new Professional("idilgorgulu", password_1.toCharArray(), "İdil", "Görgülü", 20, "igorgulu21@ku.edu.tr", 
				ppIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/idilpp.jpg"));
	User damla = new Professional("damlagorgulu", password_2.toCharArray(), "Damla", "Görgülü", 20, "dgorgulu21@ku.edu.tr",
			ppIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/damlapp.jpg"));
	User emre = new Professional("emrekursun", password_3.toCharArray(), "Emre", "Kurşun", 21, "ekursun21@ku.edu.tr", default_pp);

	User yigit = new Hobbyist("yigit_pekcetin", password_1.toCharArray(), "Yiğit", "Pekçetin", 21, "ypekcetin21@ku.edu.tr", default_pp);
	User yagiz = new Hobbyist("yagiz_yalniz", password_2.toCharArray(), "Yağız", "Yalnız", 23, "yyalniz20@ku.edu.tr", default_pp);
	User dila = new Hobbyist("dilatosun", password_3.toCharArray(), "Dila", "Tosun", 19, "dilatosun@gmail.com", 
			ppIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/dilapp.jpg"));
	
	User eren= new Free("erenyavuz", password_1.toCharArray(), "Eren", "Yavuz", 21, "eyavuz21@ku.edu.tr", default_pp);
	User koc = new Free("koc_uni", password_2.toCharArray(), "Koç", "Üniversitesi", 30, "kocuni@ku.edu.tr", 
			ppIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/kocpp.jpg"));
	User karya = new Free("karya_uner", password_3.toCharArray(), "Karya", "Üner", 20, "karyauner@yahoo.com",
			ppIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/karyapp.JPG"));
	
	
	Welcome welcome_page;
	LogIn login_page;
	SignUp signup_page;
	MyProfile profile_page;
	Discover discover_page;
	User user;
	BaseLogger baseLogger;
	static ArrayList <User> users = new ArrayList<User>();
	static ArrayList <Post> all_posts = new ArrayList<Post>();;

	/**
	 * Users are aded to the users arraylist of the app and their posts are added to the arraylist for all posts.
	 */
	public PhotoCloudApp() {
		this.welcome_page = new Welcome(this);
		for (int i = 0; i>users.size();i++) {
			for (int j = 0; j>users.get(i).getPosts().size();j++) {
				all_posts.add(users.get(i).getPosts().get(j));
			}
		}
		//idil.getPosts().add();
		users.add(idil);
		users.add(damla);
		users.add(emre);
		users.add(yigit);
		users.add(yagiz);
		users.add(dila);
		users.add(eren);
		users.add(koc);
		users.add(karya);
		users.add(admin);
		
		addPostToUser(idil, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/idilpost.jpg",false);
		addPostToUser(idil, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/idilpost2.jpg",true);
		addPostToUser(damla, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/damlapost.jpg",true);
		addPostToUser(emre, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/emrepost.jpg",true);
		addPostToUser(karya, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/karyapost.jpg",true);
		addPostToUser(yagiz, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/yagizpost.jpg",true);
		addPostToUser(yigit, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/yigitpost.jpg",true);
		addPostToUser(dila, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/dilapost.jpg",true);
		addPostToUser(eren, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/erenpost.jpg",false);
		addPostToUser(koc, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/kocpost.jpg",true);
		addPostToUser(admin, "/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/adminpost.jpg",true);
		
		
		
	}

	
	
	public void addPostToUser(User u, String path,Boolean b) {
		ImageIcon icon = new ImageIcon(path);
		Post post = new Post(u, path, b, icon);
		u.getPosts().add(post);
		all_posts.add(post);
		
	}
	
	public ImageIcon ppIcon(String path) {
		ImageIcon initial = new ImageIcon(path);
		Image resized_image = initial.getImage().getScaledInstance(90,90, Image.SCALE_SMOOTH);
		ImageIcon icon  = new ImageIcon(resized_image);
		return icon;
	}
	
	/**
	 * This is for initializing the application. A welcome page will appear.
	 */
	public void start() {
		welcome_page.setVisible(true);
	}
	
	
	/**
	 * Main method that initializes  the program.
	 */
	public static void main(String[] args) throws NumberFormatException {
		PhotoCloudApp main = new PhotoCloudApp();
		main.start();
		BaseLogger.logInfo("Application started.");
	}
	
	//GETTERS AND SETTERS

	public ArrayList<User> getUsers() {
		return users;
	}

	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ArrayList<Post> getAll_posts() {
		return all_posts;
	}
	
}
