package displays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import posts.Post;

public class Details extends JFrame implements ActionListener {

	private JPanel contentPane;
	Post post;
	ImageIcon image;
	JButton edit;
	JRadioButton make_public;
	JRadioButton make_private;
	ButtonGroup group;
	MyProfile myProfile;
	JButton back;
	JButton add_cap;
	JTextField cap_input;
	JButton delete;
	JScrollPane scrollpane;
	JPanel content;
	JTextArea comments;
	
	public Details(MyProfile myProfile,Post post) {
		this.myProfile = myProfile;
		this.post = post;
		
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 444, 544);
		panel.setBackground(new Color(128, 128, 128));
		panel.setLayout(null);
		contentPane.add(panel);
		
		image = post.getImageIcon();
	    Image resized_image = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    ImageIcon final_image = new ImageIcon(resized_image);
	    
	    JLabel image_label = new JLabel();
	    image_label.setBounds(8,5,200,200);
	    image_label.setIcon(final_image);
	    image_label.setOpaque(true);
	    panel.add(image_label);
	    
	    JLabel likes = new JLabel("Likes: " + post.getLikes());
	    likes.setBounds(220, 10, 49, 16);
	    JLabel dislikes = new JLabel("Dislikes: " + post.getDislikes() );
	    dislikes.setBounds(220, 30, 67, 16);
	    panel.add(likes);
	    panel.add(dislikes);
	    
	    edit = new JButton("Apply Filter");
		edit.setBounds(267, 123, 102, 29);
		edit.addActionListener(this);
		panel.add(edit);
		
		make_public = new JRadioButton("Public");
		make_public.setBounds(327, 88, 107, 23);
		make_public.addActionListener(this);
		if (post.getIs_public()) make_public.setSelected(true); 

		
		make_private = new JRadioButton("Private");
		make_private.setBounds(220, 88, 95, 23);
		make_private.addActionListener(this);
		if (!post.getIs_public()) make_private.setSelected(true); 
		
		group = new ButtonGroup();
		group.add(make_public);
		group.add(make_private);
		

		panel.add(make_public);
		panel.add(make_private);
		
		back = new JButton("Back");
		back.setBounds(327, 5, 102, 29);
		back.addActionListener(this);
		panel.add(back);
		
		add_cap = new JButton("Add Caption");
		add_cap.setBounds(327, 217, 102, 29);
		add_cap.addActionListener(this);
		panel.add(add_cap);
		
		cap_input = new JTextField();
		cap_input.setBounds(6, 217, 322, 29);
		panel.add(cap_input);
		cap_input.setColumns(10);
		
		delete = new JButton("Delete Post");
		delete.setBounds(267, 164, 102, 29);
		delete.addActionListener(this);
		panel.add(delete);
		
		JLabel comment = new JLabel("Comments");
		comment.setBounds(6, 250, 95, 16);
		panel.add(comment);
		 
		scrollpane = new JScrollPane();
		scrollpane.setBounds(6, 270, 430, 223);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    

		 panel.add(scrollpane);
		   
		 content = new JPanel();
		 content.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 content.setPreferredSize(scrollpane.getSize());
		 content.setPreferredSize(new Dimension(440, 2000));
		 scrollpane.setViewportView(content);

		    
		 comments = new JTextArea();
		 comments.setPreferredSize(scrollpane.getSize());
		 comments.setEditable(false); // to ensure users can't modify the displayed comments
		 comments.setLineWrap(true); 
		 comments.setWrapStyleWord(true);

		 if (post.getComments() != null)
			 comments.setText(post.getComments());
		 else
			 comments.setText("");
		 
		 content.add(comments);
		 scrollpane.setViewportView(content);
		
	    this.setVisible(true);
	     
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == make_public) {
		        post.setIs_public(true);
		    } 
		else if (e.getSource() == make_private) {
		        post.setIs_public(false);
		    }
		if (e.getSource()==edit) {
			this.setVisible(false);
			Filters filters = new Filters(myProfile, post,post.getImageIcon());
		}
		else if (e.getSource()==back) {
			this.setVisible(false);
			MyProfile my_profile = new MyProfile(myProfile.getApp(),myProfile.getUser());
		}
		else if (e.getSource()==add_cap) {
			post.setCaption(cap_input.getText());
		}
		else if (e.getSource()==delete) {
			for (int i= 0; i< myProfile.getPost_buttons().size(); i++) {
				for (int j= 0; j< myProfile.getChosen_files().size(); j++) {
					if (myProfile.getChosen_files().get(j).getAbsolutePath().equals(post.getPath())) {
						myProfile.getPost_buttons().remove(i);
						break;
					}
				}
			}
			myProfile.getUser().getPosts().remove(post);
			MyProfile my_profile = new MyProfile(myProfile.getApp(),myProfile.getUser());
			this.setVisible(false);
		}
		
	}

}
