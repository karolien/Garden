
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GardenPanel extends JComponent implements Runnable {
	private Thread animationThread = null;	// the thread for animation
	private int currentButton = 0;
	private ArrayList<Flower> plants = new ArrayList<Flower>();
	private int delay = 30;
	private int currentPlantType = 0;
	private JTextArea outputArea;

	public GardenPanel(JTextArea output) {
		this.outputArea = output;
		start();

		addMouseListener( new MouseAdapter() {
			public void mouseClicked( MouseEvent e ) {
				if (animationThread != null) {	
					boolean found = false;
					for(Flower f: plants){
						if ( f.contains( e.getPoint()) ) { 
							found = true;
							if (currentButton == 1 && f.alive){
								f.water();
								outputArea.append("You watered your " + f.getType() + "\n");
								//System.out.println("You watered your " + f.getType());
							}
						}
					}
					if (! found & currentButton == 0) addNewPlant(e.getX(), e.getY()); 
				}
			}
		});
	}

	public void updateButton(int button){
		currentButton = button;
	}

	protected void addNewPlant(int x, int y) {
		Thread t;
		switch(currentPlantType){
			case 0:
				Lily f = new Lily(x, y, outputArea);
				t = new Thread(f);
				t.start();
				plants.add(f);
				outputArea.append("You planted a lily seedling!\n");
				break;
			case 1:
				Rose r = new Rose(x, y, outputArea);
				t = new Thread(r);
				t.start();
				plants.add(r);
				outputArea.append("You planted a rose seedling!\n");
				break;
		}
		
	}

	public void update(Graphics g){
		paint(g);
	}

	public void paintComponent(Graphics g) {
		for(Flower f: plants){
			if(f.isAlive()){
				f.draw(g);
			}
		}
	}

	public void start() {
		animationThread = new Thread(this);
		animationThread.start();
	}

	public void stop() {
		if (animationThread != null) {
			animationThread = null;
		}
	}

	public void run() {
		Thread myThread = Thread.currentThread();
		while(animationThread==myThread) {
			repaint();
			pause(delay);
		}
	}

	private void pause(int milliseconds) {
		try {
			Thread.sleep((long)milliseconds);
		} catch(InterruptedException ie) {}
	}

	public void setCurrentPlantType(int selectedIndex) {
		currentPlantType = selectedIndex;		
	}
}
