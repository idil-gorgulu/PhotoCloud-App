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
import users.Administrator;
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


public class MyProfile extends JFrame implements ActionListener{

	private JPanel contentPane;
	JLabel pp;
	JLabel nickname;
	JLabel name;
	JLabel tier;
	JLabel email;
	JLabel age;
	JButton add_post;
	JMenuBar menubar;
	JButton edit;
	JButton discover;
	JScrollPane scrollpane;
	JPanel content;
	User user;
	PhotoCloudApp app;
	MyProfile myProfile = this;
	ArrayList <JButton> post_buttons;
	ArrayList <File> chosen_files;
	MyProfile profile = this;
	

	/**This is the JFrame for the private profile page of the user of the app which is only accesible by her/himself.
	 * It includes a scrollpane that contains post buttons. And buttons for accessing the discover page and editing
	 * personal information.
	 * 
	 * @param app
	 * @param user
	 */
	public MyProfile(PhotoCloudApp app, User user) {
		this.app = app;
		this.user = user;
		this.post_buttons = new ArrayList<>();
		this.chosen_files = new ArrayList<>();
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
		add_post = new JButton("Add a post");
		add_post.setBounds(163, 473, 97, 20);
		add_post.addActionListener(this);
		panel.add(add_post);
		
		//MENUBAR
		menubar = new JMenuBar();
		menubar.setBounds(6, 6, 430, 30);
		menubar.setForeground(new Color(255, 255, 255));
		
		
		discover = new JButton("Discover");
		discover.setBounds(146, 6, 140, 30);
		discover.setForeground(new Color(0, 0, 0));
		discover.setBackground(new Color(128, 128, 128));
		discover.addActionListener(this);
		menubar.add(discover);
		
		edit = new JButton("Edit Profile");
		edit.setBounds(292, 6, 140, 30);
		edit.setBackground(new Color(128, 128, 128));
		edit.setForeground(new Color(0, 0, 0));
		edit.addActionListener(this);
		menubar.add(edit);
		panel.add(menubar);
	
		
		//LABELS
		
		//profile pic
		pp = new JLabel();
		pp.setBounds(40, 50, 90, 90);
		if (user != null && user.getProfile_picture()!=null) {
			pp.setIcon((ImageIcon)user.getProfile_picture());
		}
		panel.add(pp);
		
		//name and surname
		name = new JLabel();
		name.setBounds(150, 40, 200 , 15);
		if (user != null && user.getProfile_picture()!=null) {
			name.setText(user.getName() + " " + user.getSurname());
		}
		panel.add(name);
		
		//nickname
		nickname = new JLabel();
		nickname.setBounds(150, 60, 200, 15);
		if (user != null && user.getNickname()!=null) {
			nickname.setText("Nickname: " + user.getNickname());
		}
		panel.add(nickname);
		
		//email
		email = new JLabel();
		email.setBounds(150, 80, 200, 15);
		if (user != null && user.getEmail()!=null) {
			email.setText("Email: " + user.getEmail());
		}
		panel.add(email);
		
		//age
		age = new JLabel();
		age.setBounds(150, 100, 200, 15);
		if (user != null) {
			age.setText("Age: " + user.getAge());
		}
		panel.add(age);
		
		//tier info
		tier = new JLabel();
		tier.setBounds(150, 120, 200 , 15);
		if (user != null && user.getTier()!=null) {
			tier.setText("Tier: " + user.getTier());
		}
		panel.add(tier);
		
		
		//SCROLLPANE FOR POSTS
        
        content = new JPanel();
        content.setLayout(new FlowLayout(FlowLayout.LEADING,3,3));
        content.setPreferredSize(new Dimension(430,2000));
        scrollpane = new JScrollPane();
        scrollpane.setViewportView(content);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setBounds(6,160,430,300);
        panel.add(scrollpane);
        
        
        
        if (user.getPosts() != null) {
        	for (int i= 0; i< user.getPosts().size();i++) {
        		addPostButton(user.getPosts().get(i).getImageIcon());
        		chosen_files.add(new File(user.getPosts().get(i).getPath()));
        	}
        }
		
		this.setVisible(true);
	}
	
	/**This method is for creating a button that has an image chosen by the user as its icon.
	 * So, the buttons in the scrollpanes are visual representations of the post.
	 * When clicked on a post button, a details page that includes info like like and dislike
	 * numbers and comments will be displayed.
	 *  
	 * @param image
	 */
	void addPostButton(ImageIcon image) {
		 	Image resized_image = image.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		 	ImageIcon final_image = new ImageIcon(resized_image);
		 	JButton post = new JButton(final_image);
	        post.setSize(110,110);
	        post.setIcon(final_image);
	        post_buttons.add(post);
	        
	        content.add(post);
	        content.revalidate();
	        content.repaint();
	        
	        post.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		for (int i=0;i<user.getPosts().size();i++){
	  	        	   if (user.getPosts().get(i).getImageIcon().equals(image)) {
	  	        		   profile.setVisible(false);
	  	        		   Details details = new Details(myProfile,user.getPosts().get(i));
	        			}
	        		}
	        	}
	        });
	    } 


	
	/**This method is for the user to choose an image to post. A file chooser is created to select an image file. If a file is selected, 
	 *  the program reads the selected image and creates a new Post object.
	 * 
	 */
	@Override 
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add_post) {
			JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
            	File selected_file = fileChooser.getSelectedFile();
            	BaseLogger.logInfo("File read: " + selected_file.getAbsolutePath());
            	chosen_files.add(selected_file);
            	ImageIcon selected_image = new ImageIcon(selected_file.getAbsolutePath());
        		addPostButton(selected_image);
        		Post posted_image = new Post(this.getUser(), selected_file.getAbsolutePath(), false,selected_image);
            	user.getPosts().add(posted_image);
            	app.getAll_posts().add(posted_image);
            	//content.setPreferredSize(new Dimension(430,200 + user.getPosts().size()*50));

            }
		}
		else if (e.getSource() == discover) {
			this.setVisible(false);
			if (user instanceof Administrator) {
				AdminDiscover a_discover = new AdminDiscover(app,user);
			}
			else {
				Discover discover = new Discover(app,user);
			}
		}
		else if (e.getSource() == edit) {
			this.setVisible(false);
			Edit edit = new Edit(app,user); 
		}
	}
	//GETTERS AND SETTERS
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


