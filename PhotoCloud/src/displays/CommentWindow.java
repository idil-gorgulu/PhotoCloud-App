package displays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
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
import javax.swing.border.EmptyBorder;

import posts.Comment;
import posts.Post;
import users.User;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CommentWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	Post post;
	ImageIcon image;
	ImageIcon pp_image;
	JButton comment_button;
	ButtonGroup group;
	MyProfile myProfile;
	JButton back;
	JTextField textField;
	JScrollPane scrollpane;
	JPanel content;
	JLabel caption;
	//JLabel comments;
	JTextArea comments;
	User user;
	JLabel caption_1;
	JLabel owner;
	
	/**This JFrame class is for displaying the details of a post that is made public by its owner.
	 * This Frame can only be accessed from the discover page and it includes the comments and caption
	 * of the post, as well as the nickname and profile picture of its owner. The comment button gives the 
	 * user of the application the ability to make a comment on the post.
	 * 
	 * @param post
	 * @param user
	 */
	public CommentWindow(Post post, User user) {
		this.post = post;
		this.user = user;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 444, 544);
		panel.setBackground(new Color(128, 128, 128));

		image = post.getImageIcon();
	    Image resized_image = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    ImageIcon final_image = new ImageIcon(resized_image);
	    panel.setLayout(null);
	    
	    JLabel image_label = new JLabel();
	    image_label.setBounds(8, 5, 200, 200);
	    image_label.setIcon(final_image);
	    image_label.setOpaque(true);
	    panel.add(image_label);
	    
	    comment_button = new JButton("Comment");
	    comment_button.setBounds(327, 470, 102, 29);
		comment_button.addActionListener(this);
		panel.add(comment_button);
		
		back = new JButton("Back");
		back.setBounds(334, 5, 102, 29);
		back.addActionListener(this);
		panel.add(back);
		
		JLabel lblNewLabel = new JLabel("Comments: ");
		lblNewLabel.setBounds(8, 217, 102, 16);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel.add(lblNewLabel);
		
		
		
		textField = new JTextField();
		textField.setBounds(8, 470, 322, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		
	    scrollpane = new JScrollPane();
	    scrollpane.setBounds(6, 237, 430, 223);
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
	   
	    
	    caption = new JLabel("Caption: ");
	    caption.setBounds(220, 114, 61, 16);
	    caption.setFont(new Font("Lucida Grande", Font.BOLD, 13));
	    panel.add(caption);
		
	    contentPane.add(panel);
	    
	   
	    if (post.getCaption() != null) {
		    caption_1 = new JLabel(post.getCaption());
		    caption_1.setBounds(220, 142, 205, 63);
		    caption_1.setVerticalAlignment(SwingConstants.TOP);
		    caption_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		    panel.add(caption_1);
	    }
	    
	    pp_image = post.getUser().getProfile_picture();
	    Image resized = pp_image.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	    ImageIcon icon = new ImageIcon(resized);
	    
	    owner = new JLabel(post.getUser().getNickname());
	    owner.setBounds(220, 36, 209, 66);
	    owner.setIcon(icon);
	    panel.add(owner);
	    this.setVisible(true);
	     
	}
	
	/**The comment button adds a comment to the post which has the input from the text field as its message string.
	 * When back is clicked, this frame disappears and the user is back at the discovery page.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==comment_button) {
			post.addComment(new Comment(user, textField.getText()));
			comments.setText(post.getComments());
			comments.repaint();
			comments.revalidate();
		}
		else if (e.getSource()==back) {
			this.setVisible(false);
		}
		
	}

}

