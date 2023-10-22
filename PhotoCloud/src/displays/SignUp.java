package displays;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import logging.BaseLogger;
import users.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;

public class SignUp extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField name_input;
	private JTextField surname_input;
	private JTextField nickname_input;
	private JPasswordField password_input;
	PhotoCloudApp app;
	private JLabel email;
	private JLabel age;
	private JTextField email_input;
	private JButton add_pp;
	private JButton signup;
	private JLabel pp;
	private JLabel tier;
	JRadioButton free;
	JRadioButton hobbyist;
	JRadioButton pro;
	JComboBox<Integer> age_box;
	ButtonGroup group;
	ImageIcon default_pp;
	User user;
	File selected_file;
	BaseLogger baseLogger;

	/**This is the JFrame class for the SignUp page. It asks the user of the application to
	 * enter some values for his/her name, surname, nickname, age, password and tier info. 
	 * There also is a part for choosing a profile picture, which sets an image icon that
	 * of the selected image by the user as the icon of the users profile picture label.
	 * 
	 * @param app
	 */
	public SignUp(PhotoCloudApp app) {
		this.app = app;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(6, 6, 438, 510);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//LABELS
		JLabel name = new JLabel("Name:");
		name.setBounds(90, 36, 74, 16);
		name.setForeground(new Color(255, 255, 255));
		name.setBackground(new Color(255, 255, 255));
		panel.add(name);
		
		JLabel surname = new JLabel("Surname:");
		surname.setBounds(90, 74, 74, 16);
		surname.setForeground(new Color(255, 255, 255));
		panel.add(surname);
		
		JLabel nickname = new JLabel("Nickname:");
		nickname.setBounds(90, 115, 74, 16);
		nickname.setForeground(new Color(255, 255, 255));
		panel.add(nickname);
		
		JLabel password = new JLabel("Password:");
		password.setBounds(90, 181, 74, 16);
		password.setForeground(new Color(255, 255, 255));
		panel.add(password);
		
		pp = new JLabel();
		pp.setBounds(34, 343, 90, 90);
		pp.setPreferredSize(new Dimension(90,90));
		ImageIcon default_= new ImageIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/Defaultpp.jpg");
		Image resized_image = default_.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		selected_file = new File("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/Defaultpp.jpg");
		default_pp = new ImageIcon(resized_image);
		pp.setIcon(default_pp);
		if (user != null) user.setProfile_picture(default_pp);
		panel.add(pp);
		
		email = new JLabel("Email: ");
		email.setBounds(90, 153, 61, 16);
		email.setForeground(new Color(255, 255, 255));
		panel.add(email);
		
		age = new JLabel("Age: ");
		age.setBounds(90, 224, 61, 16);
		age.setForeground(new Color(255, 255, 255));
		panel.add(age);
		
		tier = new JLabel("User Tier: ");
		tier.setBounds(90, 257, 85, 16);
		tier.setForeground(new Color(255, 255, 255));
		panel.add(tier);
		
		//TEXT AND PASSWORD FIELDS
		name_input = new JTextField();
		name_input.setBounds(187, 31, 130, 26);
		panel.add(name_input);
		name_input.setColumns(10);
		
		surname_input = new JTextField();
		surname_input.setBounds(187, 69, 130, 26);
		panel.add(surname_input);
		surname_input.setColumns(10);
		
		nickname_input = new JTextField();
		nickname_input.setBounds(187, 110, 130, 26);
		panel.add(nickname_input);
		nickname_input.setColumns(10);
		
		password_input= new JPasswordField();
		password_input.setBounds(187, 176, 130, 26);
		panel.add(password_input);
		
		
		email_input = new JTextField();
		email_input.setBounds(187, 148, 130, 26);
		panel.add(email_input);
		email_input.setColumns(10);
		
		//COMBOBOX FOR AGE INPUT
		age_box = new JComboBox<>();
		age_box.setBounds(187, 220, 130, 27);
		age_box.setBackground(new Color(135, 206, 235));
		for (int i = 13; i<=80; i++) age_box.addItem(i);
		panel.add(age_box);
		
		//RADIO BUTTONS FOR TIER INPUT
		free = new JRadioButton("Free");
		free.setBounds(169, 253, 61, 23);
		free.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		panel.add(free);
		
		hobbyist = new JRadioButton("Hobbyist");
		hobbyist.setBounds(232, 253, 85, 23);
		hobbyist.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		panel.add(hobbyist);
		
		pro = new JRadioButton("Professional");
		pro.setBounds(320, 253, 101, 23);
		pro.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		panel.add(pro);
		
		//BUTTON GROUP so that only one tier can be selected
		group = new ButtonGroup();
		group.add(free);
		group.add(hobbyist);
		group.add(pro);
		
		
		
		//BUTTONS
		add_pp = new JButton("Add a Profile Picture (Optional)");
		add_pp.setBounds(160, 350, 247, 29);
		add_pp.addActionListener(this);
		panel.add(add_pp);
		
		signup = new JButton("Sign Up");
		signup.setBounds(160, 391, 117, 29);
		signup.addActionListener(this);
		panel.add(signup);
		
		this.setVisible(true);

	}
	/** When add_pp is clicked, a file chooser is created to select an image file. If a file is selected, 
	 *  the program reads and resizes the selected image and sets it as the profile picture of the user.
	 *  When signup is clicked, if the requirements are met, a new user is created and the program directs
	 *  the user to his/her private profile page.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==add_pp) {
			JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
            	selected_file = fileChooser.getSelectedFile();
            	ImageIcon selected_image = new ImageIcon(selected_file.getAbsolutePath());
            	Image resized_image = selected_image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        		ImageIcon resized_pp = new ImageIcon(resized_image);
        		pp.setIcon(resized_pp);
        		user.setProfile_picture(resized_pp);
        		BaseLogger.logInfo("File read: " + selected_file.getAbsolutePath());
            }
		}	
		
		else if (e.getSource()==signup) {
			for (int i =0; i<app.getUsers().size();i++) {
				if  (nickname_input.getText().equals(app.getUsers().get(i).getNickname())) {
					JOptionPane.showMessageDialog(null,"Please choose another nickname","Nickname Taken",JOptionPane.ERROR_MESSAGE);
					BaseLogger.logError("Nickname taken for SignUp.");
					break;
				}
				else if (email_input.getText().equals(app.getUsers().get(i).getEmail())) {
					JOptionPane.showMessageDialog(null,"Please choose another email address","Email Address Taken",JOptionPane.ERROR_MESSAGE);
					BaseLogger.logError("Email taken for SignUp.");
					break;
				}	
			}
			if (group.getSelection()==null) {
				JOptionPane.showMessageDialog(null,"Please select your user tier","Missing Tier Selection",JOptionPane.ERROR_MESSAGE);
				BaseLogger.logError("No Tier Selected for SignUp.");
			}
			else if (password_input.getPassword().length < 8) {
				JOptionPane.showMessageDialog(null,"Your password must contain at least 8 characters","Invalid Password",JOptionPane.ERROR_MESSAGE);
				BaseLogger.logError("Password Too Short for SignUp.");
			}
			else if (email_input.getText().contains("@") == false) {
				JOptionPane.showMessageDialog(null,"Please write your email in the correct form","Invalid Email",JOptionPane.ERROR_MESSAGE);
				BaseLogger.logError("Invalid Mail for SignUp.");
			}
			else {
				if (free.isSelected()) {
					user = new Free(nickname_input.getText(),password_input.getPassword(),name_input.getText(),surname_input.getText(),
						(Integer)age_box.getSelectedItem(),email_input.getText(), (ImageIcon)pp.getIcon());
				}
				else if (hobbyist.isSelected()) {
					user = new Hobbyist(nickname_input.getText(),password_input.getPassword(),name_input.getText(),surname_input.getText(),
							(Integer)age_box.getSelectedItem(),email_input.getText(),(ImageIcon)pp.getIcon());	
				}
				else if (pro.isSelected()) {
					user = new Professional(nickname_input.getText(),password_input.getPassword(),name_input.getText(),surname_input.getText(),
							(Integer)age_box.getSelectedItem(),email_input.getText(),(ImageIcon)pp.getIcon());
				}
				app.getUsers().add(user);
				this.setVisible(false);
				BaseLogger.logInfo("A new user signed up!" + user.toString());
				MyProfile profile = new MyProfile(app,user);
			}
		}
	}		
}
