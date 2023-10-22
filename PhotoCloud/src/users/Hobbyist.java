package users;

import javax.swing.ImageIcon;


public class Hobbyist extends User {
	
	/**Hobbyist is a sub-class of user
	 * 
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 * @param profile_picture
	 */
	public Hobbyist(String nickname, char[] password, String name, String surname, int age, String email, ImageIcon profile_picture) {
			super(nickname, password,name,surname,age,email,profile_picture);
			this.tier = "Hobbyist";
			
			// TODO Auto-generated constructor stub
		}
	
	public String toString() {
		return super.toString();
	}

}
