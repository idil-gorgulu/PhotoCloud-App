package displays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import logging.BaseLogger;
import users.*;
import javax.swing.JPasswordField;

public class Edit extends JFrame implements ActionListener{

	private JPanel contentPane;


	User user;
	JLabel pp;
	JButton change_name;
	JButton change_surname;
	JButton change_age;
	JButton change_pp;
	private JTextField name_input;
	private JTextField surname_input;
	private JButton change_password;
	JComboBox<Integer> age_box;
	JRadioButton free;
	JRadioButton hobbyist;
	JRadioButton pro;
	ButtonGroup group;
	private JPasswordField password_input;
	private JTextField mail_input;
	private JButton change_mail;
	private JButton done;
	PhotoCloudApp app;
	
	public Edit(PhotoCloudApp app, User user) {
		this.app = app;
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 510);
		panel.setBackground(new Color(128, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);
		
		//LABELS
		JLabel name = new JLabel("Name:");
		name.setBounds(34, 36, 74, 16);
		name.setForeground(new Color(255, 255, 255));
		name.setBackground(new Color(255, 255, 255));
		panel.add(name);
		
		JLabel surname = new JLabel("Surname:");
		surname.setBounds(34, 74, 74, 16);
		surname.setForeground(new Color(255, 255, 255));
		panel.add(surname);
		
		JLabel nickname = new JLabel("Password: ");
		nickname.setBounds(34, 117, 74, 16);
		nickname.setForeground(new Color(255, 255, 255));
		panel.add(nickname);
		
		JLabel age = new JLabel("Age:");
		age.setBounds(34, 158, 74, 16);
		age.setForeground(Color.WHITE);
		panel.add(age);
		
		JLabel tier = new JLabel("Tier: ");
		tier.setBounds(34, 197, 74, 16);
		tier.setForeground(Color.WHITE);
		panel.add(tier);
		
		JLabel email = new JLabel("Email: ");
		email.setBounds(34, 283, 74, 16);
		email.setForeground(new Color(255, 255, 255));
		panel.add(email);
		
		pp = new JLabel();
		pp.setBounds(102, 311, 90, 90);
		pp.setPreferredSize(new Dimension(90,90));
		pp.setIcon(user.getProfile_picture());
		panel.add(pp);
		
		
		//BUTTONS
		change_name = new JButton("Change");
		change_name.setBounds(315, 31, 102, 29);
		change_name.addActionListener(this);
		panel.add(change_name);
		
		change_surname = new JButton("Change");
		change_surname.setBounds(315, 69, 102, 29);
		change_surname.addActionListener(this);
		panel.add(change_surname);
		
		change_password = new JButton("Change");
		change_password.setBounds(315, 112, 102, 29);
		change_password.addActionListener(this);
		panel.add(change_password);
		
		change_age = new JButton("Change");
		change_age.setBounds(315, 153, 102, 29);
		change_age.addActionListener(this);
		panel.add(change_age);
		
		change_mail = new JButton("Change");
		change_mail.setBounds(315, 278, 102, 29);
		change_mail.addActionListener(this);
		panel.add(change_mail);
		
		change_pp = new JButton("Change");
		change_pp.setBounds(225, 338, 102, 29);
		change_pp.addActionListener(this);
		panel.add(change_pp);
		
		done = new JButton("Save Changes");
		done.setBounds(159, 429, 130, 48);
		done.addActionListener(this);
		panel.add(done);
		
		
		//TEXTFIELDS AND PASSWORDFIELD
		name_input = new JTextField();
		name_input.setBounds(159, 31, 130, 26);
		panel.add(name_input);
		name_input.setColumns(10);
		
		surname_input = new JTextField();
		surname_input.setBounds(159, 69, 130, 26);
		panel.add(surname_input);
		surname_input.setColumns(10);
		
		mail_input = new JTextField();
		mail_input.setBounds(159, 278, 130, 26);
		mail_input.setColumns(10);
		panel.add(mail_input);
		
		password_input = new JPasswordField();
		password_input.setBounds(159, 112, 130, 26);
		panel.add(password_input);
		
	
		//COMBOBOX FOR AGE
		age_box = new JComboBox<>();
		age_box.setBounds(159, 155, 130, 27);
		age_box.setBackground(new Color(135, 206, 235));
		for (int i = 13; i<=80; i++) age_box.addItem(i);
		panel.add(age_box);
		
		//RADIOBUTTONS AND BUTTON GROUP FOR TIER
		free = new JRadioButton("Free");
		free.setBounds(159, 193, 61, 23);
		free.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		if (user instanceof Free) free.setSelected(true);
		panel.add(free);
		
		hobbyist = new JRadioButton("Hobbyist");
		hobbyist.setBounds(159, 217, 85, 23);
		hobbyist.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		if (user instanceof Hobbyist) free.setSelected(true);
		panel.add(hobbyist);
		
		pro = new JRadioButton("Professional");
		pro.setBounds(159, 243, 101, 23);
		pro.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		if (user instanceof Professional) free.setSelected(true);
		panel.add(pro);
		
		group = new ButtonGroup();
		group.add(free);
		group.add(hobbyist);
		group.add(pro);
		
		this.setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==change_name) {
			user.setName(name_input.getText());
		}
		else if (e.getSource()==change_surname) {
			user.setSurname(surname_input.getText());
		}
		else if (e.getSource()==change_password) {
			if (password_input.getPassword().length < 8) {
				JOptionPane.showMessageDialog(null,"Your password must contain at least 8 characters","Invalid Password",JOptionPane.ERROR_MESSAGE);
			}
			else user.setPassword(password_input.getPassword());
		}
		else if (e.getSource()==change_age) {
			user.setAge((Integer)age_box.getSelectedItem());
		}
		else if (e.getSource()==change_mail) {
			for (int i =0; i<app.getUsers().size();i++) {
				if (mail_input.getText().equals(app.getUsers().get(i).getEmail())) {
					JOptionPane.showMessageDialog(null,"Please choose another email address","Email Address Taken",JOptionPane.ERROR_MESSAGE);
					BaseLogger.logError("Taken mail adress called for changing info");
				break;
				}
			}
			if (mail_input.getText().contains("@") == false) {
				JOptionPane.showMessageDialog(null,"Please write your email in the correct form","Invalid Email",JOptionPane.ERROR_MESSAGE);
				BaseLogger.logError("Invalid mail adress called for changing info");
			}
			else user.setEmail(mail_input.getText());
		
		}
		else if (e.getSource()==change_pp) {
			JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
            	File selected_file = fileChooser.getSelectedFile();
            	ImageIcon selected_image = new ImageIcon(selected_file.getAbsolutePath());
            	Image resized_image = selected_image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        		ImageIcon resized_pp = new ImageIcon(resized_image);
        		user.setProfile_picture(resized_pp);
        		pp.setIcon(resized_pp);
            }
		}
		else if (e.getSource()==done) {
			this.setVisible(false);
			MyProfile profile = new MyProfile(app, user);
			/*for (int i = 0; i < user.getPosts().size(); i++) {
				 ImageIcon image = user.getPosts().get(i).getImageIcon();
				 profile.addPostButton(image); */
			//}
		}
			
	}

}
