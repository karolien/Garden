//Karolien Koorts

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
    
    GardenPanel garden;
    Thread thread;
    Container cp = getContentPane();
    int currentButton;  //0 = add plant, 1 = water plant
    
	public GUI() {
        setTitle("My Garden");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        final JButton AddPlantButton = new JButton("Add New Plant");
        AddPlantButton.setToolTipText("Click to add a new plant to your garden");
        final JButton waterButton = new JButton("Water Plant");
        waterButton.setToolTipText("Click to use watering can");

        JPanel bottomPanel = new JPanel();

        garden = new GardenPanel();
        garden.setPreferredSize(new Dimension(600, 600));
        cp.add(garden, BorderLayout.NORTH);

        //~~~~~~~~~~~~~~~~~~~~~~ADD PLANT BUTTON~~~~~~~~~~~~~~
        AddPlantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	garden.updateButton(0);
            }
        });
        bottomPanel.add(AddPlantButton);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        
        //~~~~~~~~~~~~~~~~~~~~~~WATER PLANT BUTTON~~~~~~~~~~~~~~
        waterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	garden.updateButton(1);
            }
        });
        bottomPanel.add(waterButton);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        cp.add(bottomPanel, BorderLayout.SOUTH);

        pack();
        
        thread = new Thread(garden);
        thread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
	
}
