package displays;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import image_operations.Filter;
import logging.BaseLogger;
import posts.Post;
import users.*;

import javax.swing.JSlider;
import javax.swing.JProgressBar;

public class Filters extends JFrame implements ActionListener{
	Post post;
	private JPanel contentPane;
	ImageIcon image;
	JLabel image_label;
	JButton blur;
	JButton sharpen;
	JButton grayscale;
	JButton edge;
	JButton brightness;
	JButton contrast;
	JSlider blur_slider;
	JSlider sharpen_slider;
	JSlider brightness_slider;
	JSlider contrast_slider;
	JSlider edge_slider;
	JSlider grayscale_slider;
	int blur_intensity;
	ImageIcon icon;
	ImageIcon filtered_image;
	private JButton save;
	private JButton save_new;
	MyProfile myProfile;
	MyProfile new_profile;
	BaseLogger baseLogger;

	public Filters(MyProfile myProfile,Post post, ImageIcon icon) {
		this.myProfile = myProfile;
		this.post= post;
		this.icon = icon;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				
			    Image resized_image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			    icon = new ImageIcon(resized_image);
			    
			    image_label = new JLabel();
			    image_label.setBounds(119,8,200,200);
			    image_label.setIcon(icon);
			    image_label.setOpaque(true);
			    panel.add(image_label);
			    
			    blur = new JButton("Blur");
				blur.setBounds(300, 220, 100, 20);
				blur.setForeground(new Color(0, 0, 0));
				blur.setBackground(new Color(255, 255, 255));
				blur.addActionListener(this);
				panel.add(blur);
				
				sharpen = new JButton("Sharpen");
				sharpen.setBounds(300, 255, 100, 20);
				sharpen.setForeground(new Color(0, 0, 0));
				sharpen.addActionListener(this);
				panel.add(sharpen);
				
				grayscale = new JButton("GrayScale");
				grayscale.setBounds(300, 360, 100, 20);
				grayscale.setForeground(new Color(0, 0, 0));
				grayscale.addActionListener(this);
				panel.add(grayscale);
				
				edge = new JButton("Edge Detection");
				edge.setBounds(300, 395, 100, 20);
				edge.setForeground(new Color(0, 0, 0));
				edge.addActionListener(this);
				panel.add(edge);
				
				brightness = new JButton("Brightness");
				brightness.setBounds(300, 290, 100, 20);
				brightness.setForeground(new Color(0, 0, 0));
				brightness.addActionListener(this);
				panel.add(brightness);
				
				contrast = new JButton("Contrast");
				contrast.setBounds(300, 325, 100, 20);
				contrast.setForeground(new Color(0, 0, 0));
				contrast.addActionListener(this);
				panel.add(contrast);
				
				save = new JButton("Save");
				save.setBounds(30, 440, 130, 40);
				save.addActionListener(this);
				panel.add(save);
				
				save_new = new JButton("Save as New");
				save_new.setBounds(270, 440, 130, 40);
				save_new.addActionListener(this);
				panel.add(save_new);
				
				//SLIDERS
				blur_slider = new JSlider(0,200,0);
				blur_slider.setBounds(30, 215, 275, 30);
				panel.add(blur_slider);
				
				sharpen_slider = new JSlider(0,100,0);
				sharpen_slider.setBounds(30, 250, 275, 30);
				panel.add(sharpen_slider);
				
				grayscale_slider = new JSlider(0,100,0);
				grayscale_slider.setBounds(30, 355, 275, 30);
				panel.add(grayscale_slider);
				
				edge_slider = new JSlider(0,100,0);
				edge_slider.setBounds(30, 390, 275, 30);
				panel.add(edge_slider);
				
				brightness_slider= new JSlider(0,100,0);
				brightness_slider.setBounds(30, 285, 275, 30);
				panel.add(brightness_slider);
				
				contrast_slider = new JSlider(0,100,0);
				contrast_slider.setBounds(30, 320, 275, 30);
				panel.add(contrast_slider);
				
	
				
				
				if (post.getUser() instanceof Free || post.getUser() instanceof Hobbyist ) {
					grayscale.setVisible(false);
					grayscale_slider.setVisible(false);
				}
				if (post.getUser() instanceof Free || post.getUser() instanceof Hobbyist ) {
					edge.setVisible(false);
					edge_slider.setVisible(false);
				}
				if (post.getUser() instanceof Free) {
					brightness.setVisible(false);
					brightness_slider.setVisible(false); 
				}
				if (post.getUser() instanceof Free) {
					contrast.setVisible(false);
					contrast_slider.setVisible(false);
				}
			   
				
				this.setVisible(true);
			     
		
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {	 
		if (e.getSource() == blur) {
		    ImageIcon filtered_image = Filter.applyBlur((ImageIcon)image_label.getIcon(), blur_slider.getValue()); 
		    image_label.setIcon(filtered_image);
		    BaseLogger.logInfo("Blur applied to " + post.getPath());
	        this.repaint();
	        this.revalidate();
		 }
		else if (e.getSource() == sharpen) {
			ImageIcon filtered_image = Filter.applySharpen((ImageIcon)image_label.getIcon(), sharpen_slider.getValue()); 
		    image_label.setIcon(filtered_image);
		    BaseLogger.logInfo("Sharpening applied to " + post.getPath());
	        this.repaint();
	        this.revalidate();
		 }
		else if (e.getSource() == brightness) {
			ImageIcon filtered_image = Filter.applyBrightness((ImageIcon)image_label.getIcon(), brightness_slider.getValue()); 
		    image_label.setIcon(filtered_image);
		    BaseLogger.logInfo("Brightening applied to " + post.getPath());
	        this.repaint();
	        this.revalidate();
		 }
		else if (e.getSource() == contrast) {
			ImageIcon filtered_image = Filter.applyContrast((ImageIcon)image_label.getIcon(), contrast_slider.getValue()); 
		    image_label.setIcon(filtered_image);
		    BaseLogger.logInfo("Contrast filter applied to " + post.getPath());
	        this.repaint();
	        this.revalidate();
		 }
		else if (e.getSource() == grayscale) {
			ImageIcon filtered_image = Filter.applyGrayScale((ImageIcon)image_label.getIcon(), grayscale_slider.getValue()); 
		    image_label.setIcon(filtered_image);
		    BaseLogger.logInfo("GrayScaling applied to " + post.getPath());
	        this.repaint();
	        this.revalidate();
		 }
		else if (e.getSource() == edge) {
			ImageIcon filtered_image = Filter.applyEdgeDetection((ImageIcon)image_label.getIcon(), edge_slider.getValue()); 
		    image_label.setIcon(filtered_image);
		    BaseLogger.logInfo("Edge detection applied to " + post.getPath());
	        this.repaint();
	        this.revalidate();
		 }
		
		/*else if (e.getSource() == save) {
			Image resized_image = ((ImageIcon)image_label.getIcon()).getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
			ImageIcon final_image = new ImageIcon(resized_image);
			boolean found = false;
			for (int i= 0; i< myProfile.getPost_buttons().size(); i++) {
				for (int j= 0; j< myProfile.getChosen_files().size(); j++) {
					if (myProfile.getChosen_files().get(j).getAbsolutePath().equals(post.getPath())) {
						System.out.println("SAME");
						myProfile.getPost_buttons().get(i).setIcon(final_image);
						//myProfile.getPost_buttons().get(i).repaint();
						//myProfile.getPost_buttons().get(i).revalidate();
						post.setImageicon(final_image);
						myProfile.repaint();
						myProfile.revalidate();
						found = true;
						break;
					}
				if (found) break;
				}
			}
			//myProfile.repaint();
			//myProfile.revalidate();
			this.setVisible(false);
			//MyProfile new_profile = new MyProfile(myProfile.getApp(),myProfile.getUser());
			myProfile.setVisible(true);
			
		 } */
		else if (e.getSource() == save) {
		    Image resized_image = ((ImageIcon)image_label.getIcon()).getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		    ImageIcon final_image = new ImageIcon(resized_image);
		    for (int i= 0; i< myProfile.getPost_buttons().size(); i++) {
		        if (myProfile.getChosen_files().get(i).getAbsolutePath().equals(post.getPath())) {
		            myProfile.getPost_buttons().get(i).setIcon(final_image);
		            post.setImageicon(final_image);
		            myProfile.repaint();
		            myProfile.revalidate();
		            break;
		        }
		    }
		    this.setVisible(false);
		    myProfile.setVisible(true);
		}


		else if (e.getSource() == save_new) {
			Image resized_image = ((ImageIcon)image_label.getIcon()).getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
			ImageIcon final_image = new ImageIcon(resized_image);
			myProfile.addPostButton(final_image);
			Post new_post = new Post(myProfile.getUser(), post.getPath(), false, final_image);
			myProfile.getUser().getPosts().add(new_post);
			myProfile.getApp().getAll_posts().add(new_post);
			myProfile.repaint();
			myProfile.revalidate();
			this.setVisible(false);
			MyProfile new_profile = new MyProfile(myProfile.getApp(),myProfile.getUser());
		}
		

	}
}
