import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lily extends Flower implements Runnable{
	private int width;
	private int height;


	public Lily(int x, int y){
		super(x,y);
		width = 40;
		height = 60;
		type = "Lily";
	}
	@Override
	public void run() {
		try{
			while(waterLevel > 0 && alive){
				Thread.sleep(100);
				waterLevel--;
			}
		}
		catch(Exception e){
			
		}
		finally{
			System.out.println("Your " + type + " died!");
			alive = false;
		}
	}
	public boolean contains(Point mousePt) {
		return (p.x <= mousePt.x && mousePt.x <= (p.x + width + 1)	&&	p.y <= mousePt.y && mousePt.y <= (p.y + height + 1));
	}

	
	public void draw(Graphics g) {
		BufferedImage audi = null;
		try {
			audi = ImageIO.read(GUI.class.getResource("lily.png"));
		} catch (IOException e) {}
		g.drawImage(audi, p.x, p.y, width, height, null);
		g.drawString(Integer.toString(waterLevel), p.x, p.y);
	}
	
}