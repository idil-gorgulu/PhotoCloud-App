package displays;

import java.awt.EventQueue;
import logging.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logging.BaseLogger;
import users.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LogIn extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nickname;
	private JPasswordField password;
	JButton login;
	JButton signup;
	PhotoCloudApp app;
	
	/**
	 * If the user clicks on the login button at the welcome page, this JFrame will appear.
	 * It takes a PhotoCloudApp (contains the main method) object as a parameter.
	 * @param app
	 */
	public LogIn(PhotoCloudApp app) {
		this.app = app;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		panel.setBounds(6, 6, 438, 510);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//LABELS
		JLabel nickname_label = new JLabel("Nickname: ");
		nickname_label.setBounds(68, 187, 99, 21);
		nickname_label.setForeground(new Color(255, 255, 255));
		panel.add(nickname_label);
		
		JLabel password_label = new JLabel("Password: ");
		password_label.setBounds(68, 231, 89, 16);
		password_label.setForeground(new Color(255, 255, 255));
		panel.add(password_label);
		
		//TEXT AND PASSWORD FIELDS
		nickname = new JTextField();
		nickname.setBounds(179, 184, 156, 26);
		panel.add(nickname);
		nickname.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(179, 226, 156, 26);
		panel.add(password);
		
		//BUTTONS
		login = new JButton("Log In");
		login.setBounds(155, 343, 117, 29);
		login.addActionListener(this);
		panel.add(login);
		
		signup = new JButton("Sign Up");
		signup.setBounds(315, 6, 117, 29);
		signup.addActionListener(this);
		panel.add(signup);
		
		this.setVisible(true);
	}
	
	/**If the value entered in nickname or password field does not belong to a user in the users array list in the 
	 * PhotoCloudApp class, an error message will be displayed and the user will have to try again. Otherwise, his/her
	 * private profile page will appear. 
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==signup) {
			this.setVisible(false);
			SignUp signup_page = new SignUp(app);
		}
		else if (e.getSource()==login) {
			boolean loggedIn = false;
			for (int i = 0; i < app.getUsers().size(); i++) {
				if (app.getUsers().get(i).getNickname().equals(nickname.getText())) {
					if (Arrays.equals(app.getUsers().get(i).getPassword(),password.getPassword())) {	
							loggedIn = true;
							this.setVisible(false);
							MyProfile profile_page = new MyProfile(app, app.getUsers().get(i));
							BaseLogger.logInfo(app.getUsers().get(i).getNickname() + " Logged In.");
							break;
						}
				}
			}
			if (loggedIn == false) {
			JOptionPane.showMessageDialog(null,"Wrong Username or Password,Try Again","User Could Not Be Found",JOptionPane.ERROR_MESSAGE);
			BaseLogger.logError("Invalid Information for LogIn.");
			}
		}
		
	}

}
