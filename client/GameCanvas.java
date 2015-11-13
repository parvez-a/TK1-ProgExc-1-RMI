package client;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameCanvas extends JPanel{

	/**Program to prepare the canvas. JPanel is a Swing container which is used to hold containers
	 * like background image, object etc. 
	 * 
	 */
	private static final long serialVersionUID = -6851785130639961178L;

	private BufferedImage imgBG, imgFly;
	
	public static final String PATH_TO_BG = "images/bg.png";
	public static final String PATH_TO_FLY = "images/fly.png";
	
	private Point flyPosition;
	
	private boolean canDraw;
	
	//Read and load background image and fly
	public GameCanvas(){
		try {
			imgBG = ImageIO.read(this.getClass().getResourceAsStream(PATH_TO_BG));
			imgFly = ImageIO.read(this.getClass().getResourceAsStream(PATH_TO_FLY));
			flyPosition = null;
			canDraw = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Refresh the view whenever there is change in the component
	public void toggleCanDraw(boolean toggle){
		canDraw = toggle;
		this.repaint();
	}
	
	//To get fly dimension
	public Rectangle getFlyDimension(){
		if (flyPosition != null && imgFly != null)
			return new Rectangle(flyPosition.x, flyPosition.y, imgFly.getWidth(), imgFly.getHeight());
		return null;
	}
	
	//To set new fly position and refresh the view
	public void setFlyPosition(Point p){
		flyPosition = p;
		this.repaint();
	}

	@Override
	//Get the screen with the background and fly
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		if (!canDraw) return;
		
		if (imgBG != null){
			g.drawImage(imgBG, 
				0, 
				0,
				this);
		}
		
		if (flyPosition != null && imgFly != null){
			g.drawImage(imgFly, 
					(flyPosition.x), 
					(flyPosition.y),
					this);
		}
	}
	
	
	
}
