//Karolien Koorts

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame {
    
    GardenPanel garden;
    Thread thread;
    Container cp = getContentPane();
    int currentButton;  //0 = add plant, 1 = water plant
    String[] plantList = {"Lily", "Rose", "Cactus"};
	public GUI() {
        setTitle("My Garden");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        BoxLayout boxlayout = new BoxLayout(cp, BoxLayout.Y_AXIS);
        cp.setLayout(boxlayout);
        
        final JButton AddPlantButton = new JButton("Planter Tool");
        AddPlantButton.setBackground(Color.green);
        AddPlantButton.setToolTipText("Click to add a new plant to your garden");
        final JButton waterButton = new JButton("Watering Can");
        waterButton.setToolTipText("Click to use watering can");

        JPanel buttonPanel = new JPanel();
        JTextArea outputArea = new JTextArea();
        
        garden = new GardenPanel(outputArea);
        garden.setPreferredSize(new Dimension(600, 400));
        cp.add(garden);
        
        
		JComboBox plantsComboBox = new JComboBox(plantList);
		plantsComboBox.setToolTipText("Select plant type");
		plantsComboBox.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				garden.setCurrentPlantType(cb.getSelectedIndex());
			}
		});
        buttonPanel.add(plantsComboBox);
        //~~~~~~~~~~~~~~~~~~~~~~ADD PLANT BUTTON~~~~~~~~~~~~~~
        AddPlantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	garden.updateButton(0);
            	AddPlantButton.setBackground(Color.green);;
            	waterButton.setBackground(null);;
            }
        });
        buttonPanel.add(AddPlantButton);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        
        //~~~~~~~~~~~~~~~~~~~~~~WATER PLANT BUTTON~~~~~~~~~~~~~~
        waterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	garden.updateButton(1);
            	waterButton.setBackground(Color.green);
            	AddPlantButton.setBackground(null);;
            }
        });
        buttonPanel.add(waterButton);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        cp.add(buttonPanel);
        
        JPanel bottomPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setPreferredSize(new Dimension(600,200));
        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
            public void adjustmentValueChanged(AdjustmentEvent e) {  
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
            }
        });
        bottomPanel.add(scroll);
        setResizable(false);
        cp.add(bottomPanel);
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
