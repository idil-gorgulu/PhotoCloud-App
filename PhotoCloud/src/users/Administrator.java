package users;

import javax.swing.ImageIcon;

import displays.MyProfile;

public class Administrator extends User {
		
		/**Administrator is a subclass of user. Administrator has his/her own discover page JFrame
		 * that enables him/her to remove images posted by any tier of user from the discover page.
		 * The details are explained in AdminDiscovery.
		 * 
		 * @param nickname
		 * @param password
		 * @param name
		 * @param surname
		 * @param age
		 * @param email
		 * @param profile_picture
		 */
		public Administrator(String nickname, char[] password, String name, String surname, int age, String email, ImageIcon profile_picture) {
			super(nickname, password,name,surname,age,email,profile_picture);
			this.tier = "Administrator";
			// TODO Auto-generated constructor stub
		}
		public String toString() {
			return super.toString();
		}
}
