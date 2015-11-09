package client;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameCanvas extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6851785130639961178L;

	private BufferedImage imgBG, imgFly;
	
	public static final String PATH_TO_BG = "images/bg.png";
	public static final String PATH_TO_FLY = "images/fly.png";
	
	private Point flyPosition;
	
	private boolean canDraw;
	
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
	
	public void toggleCanDraw(boolean toggle){
		canDraw = toggle;
		this.repaint();
	}
	
	public Rectangle getFlyDimension(){
		if (flyPosition != null && imgFly != null)
			return new Rectangle(flyPosition.x, flyPosition.y, imgFly.getWidth(), imgFly.getHeight());
		return null;
	}
	
	public void setFlyPosition(Point p){
		flyPosition = p;
		this.repaint();
	}

	@Override
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
