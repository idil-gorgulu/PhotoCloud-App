package image_operations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.Kernel;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.ImageIcon;

import posts.Post;


/**

The Filter class includes static methods for applying various image filters such as blur, sharpen, brightness, contrast, grayscale, and edge detection.
*/
public class Filter {
	
	
	public Filter() {}
	

	/**
	 * A value between the range of 0 and 255 is clamped.
	The value to be clamped
	 * @param value the value to be clamped
	 * @return The clamped value
	 */
	private static int  clamp(int value) {
		return Math.max(0, Math.min(255, value));
	}
	

	/**The blurring filter is applied to the given ImageIcon.
	@param icon The original image icon
	@param blurDegree The intensity of the blur filter
	@return The image icon with the applied blur filter
	*/
	public static ImageIcon applyBlur(ImageIcon icon, int blurDegree) {
	    int kernelSize = (int) (blurDegree / 30) + 1;
	    Image image = icon.getImage();
	    BufferedImage originalImage = new BufferedImage(image.getWidth(null),
	            image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D graphics = originalImage.createGraphics();
	    graphics.drawImage(image, 0, 0, null);
	    graphics.dispose();

	    BufferedImage blurredImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    for (int y = 0; y < originalImage.getHeight(); y++) {
	        for (int x = 0; x < originalImage.getWidth(); x++) {
	            // Calculate average color value for neighboring pixels
	            int totalRed = 0;
	            int totalGreen = 0;
	            int totalBlue = 0;
	            int count = 0;

	            for (int j = -kernelSize; j <= kernelSize; j++) {
	                for (int i = -kernelSize; i <= kernelSize; i++) {
	                    int newX = x + i;
	                    int newY = y + j;
	                    if (newX >= 0 && newX < originalImage.getWidth() && newY >= 0 && newY < originalImage.getHeight()) {
	                        Color pixelColor = new Color(originalImage.getRGB(newX, newY));
	                        totalRed += pixelColor.getRed();
	                        totalGreen += pixelColor.getGreen();
	                        totalBlue += pixelColor.getBlue();
	                        count++;
	                    }
	                }
	            }

	            int avgRed = totalRed / count;
	            int avgGreen = totalGreen / count;
	            int avgBlue = totalBlue / count;

	            Color blurredColor = new Color(avgRed, avgGreen, avgBlue);
	            blurredImage.setRGB(x, y, blurredColor.getRGB());
	        }
	    }
	    Image resized_image =blurredImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    ImageIcon blurredIcon = new ImageIcon(resized_image);
	    
	    return blurredIcon;
	}

	/**

	The sharpening filter is applied to an ImageIcon.
	*@param icon The original image icon
	*@param intensity The intensity of the sharpen filter
	@return The image icon with the applied sharpen filter
	*/
	public static ImageIcon applySharpen(ImageIcon icon, int intensity) {
	    Image image = icon.getImage();
	    BufferedImage originalImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Create a copy of the original image
	    Graphics2D originalGraphics = originalImage.createGraphics();
	    originalGraphics.drawImage(image, 0, 0, null);
	    originalGraphics.dispose();

	    // Apply blur to the original image
	    Image blurred = applyBlur(icon, intensity / 3).getImage();
	    BufferedImage blurredImage = new BufferedImage(blurred.getWidth(null), blurred.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D blurredGraphics = blurredImage.createGraphics();
	    blurredGraphics.drawImage(blurred, 0, 0, null);
	    blurredGraphics.dispose();

	    // Apply unsharp mask sharpening to the original image
	    BufferedImage sharpenedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    for (int y = 0; y < originalImage.getHeight(); y++) {
	        for (int x = 0; x < originalImage.getWidth(); x++) {
	            int originalRGB = originalImage.getRGB(x, y);
	            int blurredRGB = blurredImage.getRGB(x, y);

	            // Extract color channels
	            int originalRed = (originalRGB >> 16) & 0xFF;
	            int originalGreen = (originalRGB >> 8) & 0xFF;
	            int originalBlue = originalRGB & 0xFF;

	            int blurredRed = (blurredRGB >> 16) & 0xFF;
	            int blurredGreen = (blurredRGB >> 8) & 0xFF;
	            int blurredBlue = blurredRGB & 0xFF;

	            // Calculate the difference between original and blurred colors
	            int diffRed = originalRed - blurredRed;
	            int diffGreen = originalGreen - blurredGreen;
	            int diffBlue = originalBlue - blurredBlue;

	            // Apply the difference scaled by intensity to the original color
	            int sharpenedRed = clamp(originalRed + (intensity * diffRed) / 100);
	            int sharpenedGreen = clamp(originalGreen + (intensity * diffGreen) / 100);
	            int sharpenedBlue = clamp(originalBlue + (intensity * diffBlue) / 100);

	            Color sharpenedColor = new Color(sharpenedRed, sharpenedGreen, sharpenedBlue);
	            sharpenedImage.setRGB(x, y, sharpenedColor.getRGB());
	        }
	    }

	    // Scale down the sharpened image
	    Image resizedImage = sharpenedImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    ImageIcon sharpenedIcon = new ImageIcon(resizedImage);

	    return sharpenedIcon;
	}

	/**

	The brightness filter is applied to the given ImageIcon.
	@param icon The original image icon
	@param intensity The intensity of the brightness filter
	@return The image icon with the applied brightness filter
	*/
	 public static ImageIcon applyBrightness(ImageIcon icon, int intensity) {
		    Image image = icon.getImage();
		    BufferedImage originalImage = new BufferedImage(image.getWidth(null),
		            image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		    Graphics2D graphics = originalImage.createGraphics();
		    graphics.drawImage(image, 0, 0, null);
		    graphics.dispose();

		    BufferedImage brightenedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

		    for (int y = 0; y < originalImage.getHeight(); y++) {
		        for (int x = 0; x < originalImage.getWidth(); x++) {
		            Color pixelColor = new Color(originalImage.getRGB(x, y));
		            int red = pixelColor.getRed();
		            int green = pixelColor.getGreen();
		            int blue = pixelColor.getBlue();

		            // Increase RGB values by the specified intensity
		            red = clamp(red + intensity);
		            green = clamp(green + intensity);
		            blue = clamp(blue + intensity);

		            Color brightenedColor = new Color(red, green, blue);
		            brightenedImage.setRGB(x, y, brightenedColor.getRGB());
		        }
		    }

		    Image resizedImage = brightenedImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		    ImageIcon brightenedIcon = new ImageIcon(resizedImage);

		    return brightenedIcon;
		}

	 /**

	 The contrast filter is applied to the given ImageIcon.
	 @param icon The original image icon
	 @param intensity The intensity of the contrast filter
	 @return The image icon with the applied contrast filter
	 */
	public static ImageIcon applyContrast(ImageIcon icon, int intensity) {
		    Image image = icon.getImage();
		    BufferedImage originalImage = new BufferedImage(image.getWidth(null),
		            image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		    Graphics2D graphics = originalImage.createGraphics();
		    graphics.drawImage(image, 0, 0, null);
		    graphics.dispose();

		    BufferedImage contrastedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

		    // Calculate contrast adjustment factor based on intensity modifier
		    double contrastFactor = Math.pow((100 + intensity) / 100.0, 2);

		    for (int y = 0; y < originalImage.getHeight(); y++) {
		        for (int x = 0; x < originalImage.getWidth(); x++) {
		            Color pixelColor = new Color(originalImage.getRGB(x, y));
		            int red = pixelColor.getRed();
		            int green = pixelColor.getGreen();
		            int blue = pixelColor.getBlue();

		            // Apply contrast adjustment formula to each RGB value
		            red = clamp((int) (((red / 255.0 - 0.5) * contrastFactor + 0.5) * 255));
		            green = clamp((int) (((green / 255.0 - 0.5) * contrastFactor + 0.5) * 255));
		            blue = clamp((int) (((blue / 255.0 - 0.5) * contrastFactor + 0.5) * 255));

		            Color contrastedColor = new Color(red, green, blue);
		            contrastedImage.setRGB(x, y, contrastedColor.getRGB());
		        }
		    }

		    Image resizedImage = contrastedImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		    ImageIcon contrastedIcon = new ImageIcon(resizedImage);

		    return contrastedIcon;
		}

	/**

	GrayScaling filter is applied to the given ImageIcon.
	@param icon The original image icon
	@param scale_intensity The intensity of the grayscale filter
	@return The image icon with the applied grayscale filter
	*/
	public static ImageIcon applyGrayScale(ImageIcon icon, int scale_intensity) {
        Image image = icon.getImage();
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics(); 
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = bufferedImage.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int grayValue = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                int scaledGrayValue = grayValue * scale_intensity / 100;
                Color grayColor = new Color(scaledGrayValue, scaledGrayValue, scaledGrayValue);
                grayImage.setRGB(x, y, grayColor.getRGB());
            }
        }
        Image resized_image =grayImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon gray_icon = new ImageIcon(resized_image);
        return gray_icon;
    }

	/**

	Edge detection filter is applied to the given image icon.
	@param icon The original image icon
	@param intensity The intensity of the edge detection filter
	@return The image icon with the applied edge detection filter
	*/
    public static ImageIcon applyEdgeDetection(ImageIcon icon, int intensity) {
        // Convert the image to grayscale
        ImageIcon grayIcon = applyGrayScale(icon, 100);

        // Apply a slight blur
        ImageIcon blurredIcon = applyBlur(grayIcon, 10);

        // Get the blurred image
        Image blurredImage = blurredIcon.getImage();
        BufferedImage blurredBufferedImg = new BufferedImage(blurredImage.getWidth(null),
                blurredImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D blurredGraphics = blurredBufferedImg.createGraphics();
        blurredGraphics.drawImage(blurredImage, 0, 0, null);
        blurredGraphics.dispose();

        // Apply edge detection using Sobel operator
        BufferedImage edgeImage = new BufferedImage(blurredBufferedImg.getWidth(), blurredBufferedImg.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        int[][] sobelX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] sobelY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};

        for (int y = 1; y < blurredBufferedImg.getHeight() - 1; y++) {
            for (int x = 1; x < blurredBufferedImg.getWidth() - 1; x++) {
                int pixelX = (
                        (sobelX[0][0] * new Color(blurredBufferedImg.getRGB(x - 1, y - 1)).getRed()) +
                        (sobelX[0][1] * new Color(blurredBufferedImg.getRGB(x, y - 1)).getRed()) +
                        (sobelX[0][2] * new Color(blurredBufferedImg.getRGB(x + 1, y - 1)).getRed()) +
                        (sobelX[1][0] * new Color(blurredBufferedImg.getRGB(x - 1, y)).getRed()) +
                        (sobelX[1][1] * new Color(blurredBufferedImg.getRGB(x, y)).getRed()) +
                        (sobelX[1][2] * new Color(blurredBufferedImg.getRGB(x + 1, y)).getRed()) +
                        (sobelX[2][0] * new Color(blurredBufferedImg.getRGB(x - 1, y + 1)).getRed()) +
                        (sobelX[2][1] * new Color(blurredBufferedImg.getRGB(x, y + 1)).getRed()) +
                        (sobelX[2][2] * new Color(blurredBufferedImg.getRGB(x + 1, y + 1)).getRed())
                );

                int pixelY = (
                        (sobelY[0][0] * new Color(blurredBufferedImg.getRGB(x - 1, y - 1)).getRed()) +
                        (sobelY[0][1] * new Color(blurredBufferedImg.getRGB(x, y - 1)).getRed()) +
                        (sobelY[0][2] * new Color(blurredBufferedImg.getRGB(x + 1, y - 1)).getRed()) +
                        (sobelY[1][0] * new Color(blurredBufferedImg.getRGB(x - 1, y)).getRed()) +
                        (sobelY[1][1] * new Color(blurredBufferedImg.getRGB(x, y)).getRed()) +
                        (sobelY[1][2] * new Color(blurredBufferedImg.getRGB(x + 1, y)).getRed()) +
                        (sobelY[2][0] * new Color(blurredBufferedImg.getRGB(x - 1, y + 1)).getRed()) +
                        (sobelY[2][1] * new Color(blurredBufferedImg.getRGB(x, y + 1)).getRed()) +
                        (sobelY[2][2] * new Color(blurredBufferedImg.getRGB(x + 1, y + 1)).getRed())
                );

                int gradientMagnitude = (int) Math.sqrt((pixelX * pixelX) + (pixelY * pixelY));
                int edgeValue = clamp(gradientMagnitude * intensity / 100);

                Color edgeColor = new Color(edgeValue, edgeValue, edgeValue);
                edgeImage.setRGB(x, y, edgeColor.getRGB());
            }
        }

        // Scale down the edge-detected image
        Image resizedImage = edgeImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon edgeIcon = new ImageIcon(resizedImage);

        return edgeIcon;
    }

    

} 



