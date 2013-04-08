

 
//package events;
 
/*
* KeyEventDemo
*/
 
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
 
public class RogueLikeGui extends JFrame
        implements KeyListener,
        ActionListener
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextField typingArea;
	
	JTextArea infoDisplayArea;
    JTextArea mapDisplayArea;
    JTextArea actionDisplayArea;
    
    static final String newline = System.getProperty("line.separator");
    
    private static Dungeon d = new Dungeon();
	Player player1 = new Player("Link");
	Monster monster1 = new Monster("Moblin",'M');
	
	public static String currentMessage = "";
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        RogueLikeGui frame = new RogueLikeGui("Roguelike");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Set up the content pane.
        frame.addComponentsToPane();
         
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        d.setMap();
        frame.startMap();
        
    }
     
    private void startMap() {
    	
    	player1.setHitPoints(12);
		player1.setBaseDamage(1);
		monster1.setHitPoints(10);
		
		monster1.setCurrentLevel(d.lvlList[0]);
		monster1.setPosition(2,2);
		monster1.currentTile.monster=monster1;
		
		player1.setCurrentLevel(d.lvlList[0]);
		player1.setPosition(4,4);
		player1.currentTile.monster=player1;
		
		mapDisplayArea.setText(d.getMap());
		infoDisplayArea.setText(player1.getInfo());
	}

	private void addComponentsToPane() {
         
        JButton button = new JButton("Clear");
        button.addActionListener(this);
         
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
        
        infoDisplayArea = new JTextArea();
        infoDisplayArea.setEditable(false);
        
        mapDisplayArea = new JTextArea();
        mapDisplayArea.setEditable(false);
        
        actionDisplayArea = new JTextArea();
        actionDisplayArea.setEditable(false);
         
        //Uncomment this if you wish to turn off focus
        //traversal.  The focus subsystem consumes
        //focus traversal keys, such as Tab and Shift Tab.
        //If you uncomment the following line of code, this
        //disables focus traversal and the Tab events will
        //become available to the key event listener.
        //typingArea.setFocusTraversalKeysEnabled(false);
         
        
        
        JScrollPane scrollPane = new JScrollPane(mapDisplayArea);
        scrollPane.setPreferredSize(new Dimension(125,320 ));
         
        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(infoDisplayArea,BorderLayout.LINE_START);
        
        getContentPane().add(scrollPane, BorderLayout.LINE_END);
        getContentPane().add(actionDisplayArea, BorderLayout.PAGE_END);
        
        
        
       // typingArea.setVisible(false);
    }
     
    public RogueLikeGui(String name) {
        super(name);
    }
     
     
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        displayInfo(e, "KEY TYPED: ");
    }
     
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        displayInfo(e, "KEY PRESSED: ");
    }
     
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        displayInfo(e, "KEY RELEASED: ");
    }
     
    /** Handle the button click. */
    /*public void actionPerformed(ActionEvent e) {
        //Clear the text components.
       displayArea.setText("");
       typingArea.setText("");
         
        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
    }*/
     
    /*
     * We have to jump through some hoops to avoid
     * trying to print non-printing characters
     * such as Shift.  (Not only do they not print,
     * but if you put them in a String, the characters
     * afterward won't show up in the text area.)
     */
    private void displayInfo(KeyEvent e, String keyStatus){
   
         
        //You should only rely on the key char if the event
        //is a key typed event.
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            player1.move(c);
            mapDisplayArea.setText(d.getMap());
            typingArea.setText("");
            actionDisplayArea.setText(currentMessage);
            infoDisplayArea.setText(player1.getInfo());
        } 
        
        
        /*else {
            int keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")";
        }*/
         
        int modifiersEx = e.getModifiersEx();
        String modString = "extended modifiers = " + modifiersEx;
        String tmpString = KeyEvent.getModifiersExText(modifiersEx);
        if (tmpString.length() > 0) {
            modString += " (" + tmpString + ")";
        } else {
            modString += " (no extended modifiers)";
        }
         
        String actionString = "action key? ";
        if (e.isActionKey()) {
            actionString += "YES";
        } else {
            actionString += "NO";
        }
         
        String locationString = "key location: ";
        int location = e.getKeyLocation();
        if (location == KeyEvent.KEY_LOCATION_STANDARD) {
            locationString += "standard";
        } else if (location == KeyEvent.KEY_LOCATION_LEFT) {
            locationString += "left";
        } else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
            locationString += "right";
        } else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {		//important(numpad)
            locationString += "numpad";
        }
         
      
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}


