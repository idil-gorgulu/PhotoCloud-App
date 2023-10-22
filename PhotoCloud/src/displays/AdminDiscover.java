package displays;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.color.ProfileDataException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import displays.*;
import posts.Post;
import users.User;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class AdminDiscover extends JFrame  implements ActionListener{
	
		private JPanel contentPane;
		JComboBox <String> search_box;
		JMenuBar menubar;
		JButton my_profile;
		JScrollPane scrollpane;
		JPanel content;
		User user;
		PhotoCloudApp app;
		JButton search;
		AdminDiscover discover = this;
		

		public AdminDiscover(PhotoCloudApp app, User user) {
			this.app = app;
			this.user = user;
			
			setTitle("Admin Discover");
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 470, 550);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(0, 0, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null); 
			
			JPanel panel = new JPanel();
			panel.setBounds(0, 6, 464, 510);
			contentPane.add(panel);
			panel.setLayout(null);
			
		
			
			//MENUBAR
			menubar = new JMenuBar();
			menubar.setBackground(new Color(128, 128, 128));
			menubar.setBounds(6, 6, 460, 30);
			menubar.setForeground(new Color(255, 255, 255));
			panel.add(menubar);
			
			my_profile = new JButton("My Profile");
			my_profile.setBounds(550, 6, 100, 30);
			my_profile.setForeground(new Color(0, 0, 0));
			my_profile.setBackground(new Color(128, 128, 128));
			my_profile.addActionListener(this);
			
			search = new JButton("Search");
			search.setBounds(450, 6, 100, 30);
			search.setForeground(new Color(0, 0, 0));
			search.setBackground(new Color(128, 128, 128));
			search.addActionListener(this);
			
			search_box = new JComboBox<String>();
			search_box.setBounds(6, 6, 150, 30);
			search_box.setBackground(new Color(135, 206, 235));
			search_box.setSelectedItem("User Search");
			for (int i= 0; i< app.getUsers().size();i++) {
				search_box.addItem(app.getUsers().get(i).getNickname());
			}
			
			System.out.println(app.getAll_posts().size());
			
			menubar.add(search_box);
			menubar.add(search);
			menubar.add(my_profile);
			//panel.add(my_profile);
			
			
			//SCROLLPANE FOR POSTS
	        
	        content = new JPanel();
	        content.setLayout(new FlowLayout(FlowLayout.LEADING,2,2));
	        scrollpane = new JScrollPane();
	        scrollpane.add(content);
	        scrollpane.setViewportView(content);
			scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollpane.setBounds(6,41,460,463);
	        panel.add(scrollpane);
	        
	        addPublicPosts();
	        
	        contentPane.add(panel);
	        
	        //posts = new ArrayList <Post>();
			
			this.setVisible(true);
		}

		public void addPublicPosts() {
			for (int i = 0;i < app.getAll_posts().size(); i++) {
				if (app.getAll_posts().get(i).getIs_public()){
					System.out.println("I am here");
					System.out.println("I AM HERE");
					content.setPreferredSize(new Dimension(440, 5000));
					JPanel post_panel = PostPanel(app.getAll_posts().get(i).getUser(), app.getAll_posts().get(i));
					post_panel.setVisible(true);
					scrollpane.setViewportView(content);
					content.setVisible(true);
					content.add(post_panel);
			        content.revalidate();
			        content.repaint();
			        content.setVisible(true);
			        System.out.println("i am here");
				}
			}
		}
		
		
		public JPanel PostPanel(User u, Post post) {
			
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(215, 280));
			//panel.setBounds(6,326 + app.getAll_posts().size()*215, 215, 280);
			panel.setBackground(Color.LIGHT_GRAY);
			//content.add(panel);
			panel.setLayout(null);
			
			JButton user_button = new JButton(u.getNickname() + ", " + u.getTier());
			user_button.setBounds(1, 1, 190, 30);
			panel.add(user_button);
			
			JLabel image_label = new JLabel();
			Image resized_image =post.getImageIcon().getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);        
			image_label.setBounds(6, 30, 200,200);
			image_label.setIcon(new ImageIcon(resized_image));
			panel.add(image_label);
			
			JButton like = new JButton(Integer.toString(post.getLikes()));
			like.setBounds(3, 230, 70, 50);
			like.setIcon(new ImageIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/like.png"));
			panel.add(like);
			
			JButton dislike = new JButton(Integer.toString(post.getDislikes()));
			dislike.setBounds(73, 230, 70, 50);
			dislike.setIcon(new ImageIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/dislike.png"));
			panel.add(dislike);
			
			JButton comment = new JButton();
			comment.setBounds(143, 230, 70, 50);
			comment.setIcon(new ImageIcon("/Users/idilgorgulu/eclipse-workspace/PhotoCloud/src/files/com.png"));
			panel.add(comment);
			
			JButton delete = new JButton(" X ");
			delete.setBounds(180, 1, 30, 30);
			panel.add(delete);
			
			
			ActionListener actionListener = new ActionListener() { 
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == like) {
		        		post.setLikes(post.getLikes() + 1);
		        		like.setText(Integer.toString(post.getLikes()));
		        		like.repaint();
		        		like.revalidate();
		        	}
					else if (e.getSource() == dislike) {
		        		post.setDislikes(post.getDislikes() + 1);
		        		dislike.setText(Integer.toString(post.getDislikes()));
		        		dislike.repaint();
		        		dislike.revalidate();
		        	}
					else if (e.getSource() == user_button) {
								discover.setVisible(false);
								Profile profile = new Profile(app,u);
		        	}
					else if (e.getSource() == comment) {
						CommentWindow commentWindow = new CommentWindow(post,user);
					}
					else if (e.getSource()==delete) {
						app.getAll_posts().remove(post);
						u.getPosts().remove(post);
						content.remove(panel);
						content.repaint();
						content.revalidate();
						System.out.println("REMOVED");
					}
				}
				
			};
			
			like.addActionListener(actionListener);
			dislike.addActionListener(actionListener);
			comment.addActionListener(actionListener);
			user_button.addActionListener(actionListener);
			delete.addActionListener(actionListener);
			panel.setVisible(true);
			System.out.println("HOOP BURDAYIM");
			
			return panel;
		}
			
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == search) {
				for (int i =0; i<app.getUsers().size();i++) {
					if (app.getUsers().get(i).getNickname().equals(search_box.getSelectedItem().toString())) {
						this.setVisible(false);
						Profile profile = new Profile(app,app.getUsers().get(i));
					}
				}
			}
			else if (e.getSource() == my_profile) {
				this.setVisible(false);
				MyProfile my_profile = new MyProfile(app,user);
			}
			// TODO Auto-generated method stub
			
		}
		
		
		public User getUser() {
			return user;
		}

		

		public PhotoCloudApp getApp() {
			return app;
		}


		

		//public ArrayList<Post> getPosts() {
			//return posts;
		//}	*/
		
		
	}

