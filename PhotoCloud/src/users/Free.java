package users;

import javax.swing.ImageIcon;

import displays.MyProfile;

public class Free extends User {
	
	/**Free is a subclass of user
	 * 
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 * @param profile_picture
	 */
	public Free(String nickname, char[] password, String name, String surname, 
			int age, String email, ImageIcon profile_picture) {
		super(nickname, password,name,surname,age,email,profile_picture);
		this.tier = "Free";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
