package displays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logging.BaseLogger;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome extends JFrame implements ActionListener {

	private JPanel contentPane;
	JButton login;
	JButton signup;
	PhotoCloudApp app;
	BaseLogger baseLogger;
		
	/**
	 *  This class is a JFrame Welcome Page that has buttons for logging in and signing up.
	 *  This is the starting page of the PhotoCloud App.
	 * @param app
	 */
	public Welcome(PhotoCloudApp app) {
		this.app=app;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 511);
		panel.setForeground(new Color(221, 160, 221));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel welcome_message = new JLabel("Welcome to PhotoCloud!");
		welcome_message.setBounds(137, 21, 158, 16);
		panel.add(welcome_message);
		
		JLabel question = new JLabel("Don't have an account?");
		question.setBounds(287, 280, 145, 16);
		panel.add(question);
		
		JLabel logo = new JLabel();
		logo.setBounds(119, 65, 200, 200);
		logo.setIcon(new ImageIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/PhotoCloud.png"));
		logo.setOpaque(true);
		panel.add(logo);
		
		login = new JButton("Log In");
		login.setBounds(30, 303, 83, 29);
		login.addActionListener(this);
		panel.add(login);
		
		signup = new JButton("Sign Up");
		signup.setBounds(313, 303, 92, 29);
		signup.addActionListener(this);
		panel.add(signup);
		
	}

	/**
	 * A new login or signup frame will appear depending on the button clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==login) {
			this.setVisible(false);
			LogIn login_page = new LogIn(app);
		}
		
		else if (e.getSource()==signup) {
			this.setVisible(false);
			SignUp signup_page = new SignUp(app);
		}
	
	}
	
	
	
}
