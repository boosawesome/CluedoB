package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButton extends JPanel implements ActionListener {
	
	public JRadioButton scarlettButton;
	public JRadioButton whiteButton; 
	public JRadioButton mustardButton; 
	public JRadioButton greenButton;
	public JRadioButton peacockButton; 
	public JRadioButton plumButton; 
	public ButtonGroup group;
	
	public ArrayList<JRadioButton> buttons;
	
	JLabel picture;

	public RadioButton(){
		String scarlett = "Miss Scarlett";
		String white = "Mrs White";
		String mustard = "Colonel Mustard";
		String green = "The Reverend Green";
		String peacock = "Mrs Peacock";
		String plum = "Professor Plum";
		
		picture = new JLabel();
		//In initialization code:
	    //Create the radio buttons.
	    scarlettButton = new JRadioButton(scarlett);
	    scarlettButton.setMnemonic(KeyEvent.VK_S);
	    scarlettButton.setActionCommand(scarlett);
	    scarlettButton.setSelected(true);

	    whiteButton = new JRadioButton(white);
	    whiteButton.setMnemonic(KeyEvent.VK_W);
	    whiteButton.setActionCommand(white);

	    mustardButton = new JRadioButton(mustard);
	    mustardButton.setMnemonic(KeyEvent.VK_M);
	    mustardButton.setActionCommand(mustard);

	    greenButton = new JRadioButton(green);
	    greenButton.setMnemonic(KeyEvent.VK_G);
	    greenButton.setActionCommand(green);

	    peacockButton = new JRadioButton(peacock);
	    peacockButton.setMnemonic(KeyEvent.VK_P);
	    peacockButton.setActionCommand(peacock);
	    
	    plumButton = new JRadioButton(plum);
	    plumButton.setMnemonic(KeyEvent.VK_L);
	    plumButton.setActionCommand(plum);

	    //Group the radio buttons.
	    group = new ButtonGroup();
	    group.add(scarlettButton);
	    group.add(whiteButton);
	    group.add(mustardButton);
	    group.add(greenButton);
	    group.add(peacockButton);
	    group.add(plumButton);
	    
	    buttons = new ArrayList<JRadioButton>();
	    buttons.add(scarlettButton);
	    buttons.add(whiteButton);
	    buttons.add(mustardButton);
	    buttons.add(greenButton);
	    buttons.add(peacockButton);
	    buttons.add(plumButton);

	    //Register a listener for the radio buttons.
	    scarlettButton.addActionListener(this);
	    whiteButton.addActionListener(this);
	    mustardButton.addActionListener(this);
	    greenButton.addActionListener(this);
	    peacockButton.addActionListener(this);
	    plumButton.addActionListener(this);
	    
	  //Set up the picture label.
      
		updatePicture();
        	
       

        //The preferred size is hard-coded to be the width of the
        //widest image and the height of the tallest image.
        //A real program would compute this.
        picture.setPreferredSize(new Dimension(150, 200));


        //Put the radio buttons in a column in a panel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(scarlettButton);
        radioPanel.add(whiteButton);
        radioPanel.add(mustardButton);
        radioPanel.add(greenButton);
        radioPanel.add(peacockButton);
        radioPanel.add(plumButton);

        add(radioPanel, BorderLayout.LINE_START);
        add(picture, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		  try {
			picture.setIcon(createImageIcon("src/images/"
			          + e.getActionCommand()
			          + ".jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void updatePicture(){
		
			for(JRadioButton rb : buttons){
				
				if(rb.isEnabled()){ 
					 try {
							picture.setIcon(createImageIcon("src/images/"
							          + getName(rb)
							          + ".jpg"));
							rb.setSelected(true);
							return;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					
				}
				
			}
		
		
	}
		
		
	
	
	// Returns an ImageIcon, or null if the path was invalid. 
    protected static ImageIcon createImageIcon(String path) throws IOException {
    	 File f = new File(path);
         BufferedImage imgURL = ImageIO.read(f);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("RadioButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new RadioButton();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public String getSelectedButton()
    {  
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    return button.getText();
                }
            }
    return null;
    }
    
    
    public JRadioButton getRadioButton(String s){
    	switch(s){
    	case "Miss Scarlett":
    		return scarlettButton;
    	case "Mrs White":
    		return whiteButton;
    	case "Colonel Mustard":
    		return mustardButton;
    	case "The Reverend Green":
    		return greenButton;
    	case "Mrs Peacock":
    		return peacockButton;
    	case "Professor Plum":
    		return plumButton;
    	}
    	return null;
    	
    	
    }
    
    public String getName(JRadioButton s){
    	if(s.equals(scarlettButton)){
    		return "Miss Scarlett";
    	}else if(s.equals(whiteButton)){
    		return "Mrs White";
    	}
    	else if(s.equals(mustardButton)){
    		return "Colonel Mustard";
    	}else if(s.equals(greenButton)){
    		return "The Reverend Green";
    	}else if(s.equals(peacockButton)){
    		return "Mrs Peacock";
    	}else if(s.equals(plumButton)){
    		return "Professor Plum";
    	}
    	return null;
    	
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
