package displays;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import logging.BaseLogger;
import posts.Post;
import users.User;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Profile extends JFrame implements ActionListener{

	private JPanel contentPane;
	JLabel pp;
	JLabel nickname;
	JLabel name;
	JLabel tier;
	JButton back;
	JMenuBar menubar;
	JButton edit;
	JButton discover;
	JScrollPane scrollpane;
	JPanel content;
	User user;
	User visitor;
	PhotoCloudApp app;
	Profile profile = this;
	ArrayList <JButton> post_buttons;
	ArrayList <File> chosen_files;
	BaseLogger baseLogger; Profile(PhotoCloudApp app, User user) {
		
		this.app = app;
		this.user = user;
		this.post_buttons = new ArrayList<>();
		this.chosen_files = new ArrayList<>();
		this.visitor = app.getUser();
		setTitle(user.getNickname());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 510);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Add post button
		back = new JButton("Back");
		back.setBounds(163, 473, 97, 20);
		back.addActionListener(this);
		panel.add(back);
		
	
		
		//LABELS
		
		//profile pic
		pp = new JLabel();
		pp.setBounds(40, 40, 90, 90);
		if (user != null && user.getProfile_picture()!=null) {
			pp.setIcon((ImageIcon)user.getProfile_picture());
		}
		panel.add(pp);
		
		//name and surname
		name = new JLabel();
		name.setBounds(150, 40, 150 , 30);
		if (user != null && user.getProfile_picture()!=null) {
			name.setText(user.getName() + " " + user.getSurname());
		}
		panel.add(name);
		
		//nickname
		nickname = new JLabel();
		nickname.setBounds(150, 70, 150, 30);
		if (user != null && user.getNickname()!=null) {
			nickname.setText("Nickname: " + user.getNickname());
		}
		panel.add(nickname);
		
		//tier info
		tier = new JLabel();
		tier.setBounds(150, 100, 150 , 30);
		if (user != null && user.getTier()!=null) {
			tier.setText("Tier: " + user.getTier());
		}
		panel.add(tier);
		
		
		//SCROLLPANE FOR POSTS
        
        content = new JPanel();
        content.setLayout(new FlowLayout(FlowLayout.LEADING,3,3));
        scrollpane = new JScrollPane();
        scrollpane.setViewportView(content);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setBounds(6,160,430,300);
        panel.add(scrollpane);
        
        addPostButtons();
        
		this.setVisible(true);
		
	}
	
	 void addPostButtons() {
		 for (int i = 0; i< user.getPosts().size();i++) {
			 if (user.getPosts().get(i).getIs_public()) {
				 ImageIcon image = user.getPosts().get(i).getImageIcon();
				 Image resized_image = image.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
				 	ImageIcon final_image = new ImageIcon(resized_image);
				 	JButton post = new JButton(final_image);
			        post.setSize(110,110);
			        post.setIcon(final_image);
			        post_buttons.add(post);
			        
			        content.add(post);
			        content.revalidate();
			        content.repaint();
			 	}
		 	}
	    } 


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.setVisible(false);
			//Discover discover = new Discover(app, visitor);
		}
	}
	
	public User getUser() {
		return user;
	}

	public ArrayList<JButton> getPost_buttons() {
		return post_buttons;
	}

	public PhotoCloudApp getApp() {
		return app;
	}

	public void setPost_buttons(ArrayList<JButton> post_buttons) {
		this.post_buttons = post_buttons;
	}

	public ArrayList<File> getChosen_files() {
		return chosen_files;
	}
	
}

