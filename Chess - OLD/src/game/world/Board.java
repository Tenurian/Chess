package game.world;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import greenfoot.Greenfoot;
import greenfoot.World;
import game.manager.GameManager;

public class Board extends World {

	public static final int WIDTH = 8, HEIGHT = 8, CELL_SIZE = 100;
	public static enum player{ WHITE , BLACK };
	public static GameManager gm;
	
	public Board(){
		super(WIDTH,HEIGHT,CELL_SIZE);
		gm = new GameManager(this);
		new MainMenu();
	}

	@SuppressWarnings({"rawtypes","serial"})
	private class MainMenu extends JFrame implements ActionListener{
		
		
		JComboBox diff;
		JRadioButton SP, MP;
		
		@SuppressWarnings("unchecked")
		public MainMenu(){
			super("Chess -- Main Menu");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setAlwaysOnTop(true);
			Dimension d = new Dimension(200,175);
			this.setPreferredSize(d);
			this.setLayout(new GridBagLayout());
			GridBagConstraints c1 = new GridBagConstraints();
			
			JPanel MPP = new JPanel();
			MP = new JRadioButton("Two Player", true);
			MP.addActionListener(this);
			MP.setActionCommand("mp");
			MP.setMnemonic(KeyEvent.VK_T);
			MPP.add(MP);
			
			JPanel SPP = new JPanel();
			SPP.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridheight = 1;
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy = 0;
			SP = new JRadioButton("Single Player", false);
			SP.addActionListener(this);
			SP.setActionCommand("sp");
			SP.setMnemonic(KeyEvent.VK_S);
			SPP.add(SP, c);
			c.gridx = 1;
			c.gridwidth = 2;
			c.gridx = 0;
			c.gridy = 1;
			String[] diffs = {"Easy","Medium","Hard"};
			diff = new JComboBox(diffs);
			diff.setEnabled(false);
			SPP.add(diff,c);
			
			ButtonGroup modes = new ButtonGroup();
			modes.add(MP);
			modes.add(SP);
			
			JPanel bp = new JPanel();
			JButton start = new JButton("Start");
			start.addActionListener(this);
			start.setActionCommand("start");
			bp.add(start);
			JButton exit = new JButton("Exit");
			exit.addActionListener(this);
			exit.setActionCommand("exit");
			bp.add(exit);
			
			c1.gridheight = 1;
			c1.gridwidth = 2;
			c1.gridx = 0;
			c1.gridy = 0;
			this.add(MPP, c1);
			c1.gridy = 1;
			this.add(SPP, c1);
			c1.gridy = 2;
			this.add(bp, c1);
			
			this.pack();
			this.setResizable(false);
			this.setLocation((1920/2)-(this.getWidth()/2), (1080/2)-(this.getHeight()/2));
			this.setVisible(true);
			this.setAlwaysOnTop(false);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "sp"){
				diff.setFocusable(true);
				diff.setEnabled(true);
			}
			
			if(e.getActionCommand() == "mp"){
				diff.setFocusable(false);
				diff.setEnabled(false);
			}
			
			if(e.getActionCommand() == "exit"){
				System.exit(1);
			}
			
			if(e.getActionCommand() == "start"){
				if(SP.isSelected()){
					switch(diff.getSelectedIndex()){
					case 0:
						break;
					case 1:
						break;
					case 2:
						break;
					default:
						break;
					}
					JOptionPane.showMessageDialog(new JFrame(), "Multiplayer not built yet.");
				}else if(MP.isSelected()){
					gm.gameInit();
					Greenfoot.start();
					this.dispose();
				}
			}
		}
		
	}
	
}
