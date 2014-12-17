/* Jordan Bossman
 * CSC 360
 * Programming Assignment #4
 * Due: November 30, 2011
 * Class file to create and manage the 
 * GUI for the game "Minesweeper".
 */

package Minesweeper;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MinesweeperGui extends JFrame
{	
	private boolean firstMove = true;
	private JButton[][] jbtSpaces;
	private JLabel[][] jlabSpaces;
	private Integer numMines = 0;
	private JLabel mineResultsLabel = new JLabel();
	private JRadioButton easyRadioBtn;
	private JRadioButton medRadioBtn;
	private JRadioButton hardRadioBtn;
	private ImageIcon icon = new ImageIcon("minesweeperMine.gif");
	private static JFrame difficultyFrame;
	private static JFrame gameGui;
	private static Minesweeper minesweeper;

	public MinesweeperGui(String title, int numRows, int numCols, final Minesweeper minesweeper, int difficulty)
	{
		//Creates the main game GUI.
		super(title);
		setIconImage(icon.getImage());
		
		//Starts the arrays for the buttons and labels. 
		//Then sets the number of mines to that of the difficulty.
		jbtSpaces = new JButton[numRows][numCols];
		jlabSpaces = new JLabel[numRows][numCols];
		numMines = difficulty;
				
		JPanel playableArea = new JPanel(new BorderLayout());
		
		//Creates the buttons and action listeners for "Rules", "Controls" and "Undo Last Move".
		JPanel infoPanel = new JPanel();
		JButton jbtRules = new JButton("Rules");
		jbtRules.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						StringBuffer rules = new StringBuffer();
						rules.append("Minesweeper rules:\n");
						rules.append("-Click on a square to display numbers displaying the amount ");
						rules.append("of mines in the surround eight squares.\n");
						rules.append("-If the player hits a mine, the game is an automatic loss.\n");
						rules.append("-The player does have a choice of an \"undo button\" which will ");
						rules.append("undo the last move made.\n");
						rules.append("-If the player right clicks once, a flag will appear.\n");
						rules.append("-If the player right clicks two times a question mark will appear. ");
						rules.append("This is only for help to the player - does not do anything to the game at all.\n");
						rules.append("-At the end of the game, it will display whether the player as won or loss,");
						rules.append("the amount of time it took and it will display all of the mines.\n If the player ");
						rules.append("won the game then all of the mines should have a green checkmark,\n otherwise ");
						rules.append("the player has lost and is just showing the mines as a reference to where they were.");
						JOptionPane.showMessageDialog(null, rules.toString(), "Rules", 
								  JOptionPane.INFORMATION_MESSAGE);
					}
				});
		JButton jbtControls = new JButton("Controls");
		jbtControls.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						StringBuffer contString = new StringBuffer();
						contString.append("Controls:\nLeft click - Move to location\n");
						contString.append("One right click - Add a flag\n");
						contString.append("Two right clicks - Add a question mark\n");
						contString.append("Three right clicks - Remove any flags and question marks");
						JOptionPane.showMessageDialog(null, contString.toString(), "Controls", 
													  JOptionPane.INFORMATION_MESSAGE);
					}
				});
		
		JButton jbtUndo = new JButton("Undo Last Move");
		jbtUndo.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							minesweeper.undoMove(jbtSpaces);
						}
						catch(NullPointerException exception)
						{
							JOptionPane.showMessageDialog(gameGui,"Make sure to make a move first!",
									"Make a Move", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
		infoPanel.add(jbtRules);
		infoPanel.add(jbtControls);
		infoPanel.add(jbtUndo);
		playableArea.add(infoPanel, BorderLayout.NORTH);

		//Creates a panel with OverlayLayout which will be used for the buttons
		//on top of labels.
		JPanel playingArea = new JPanel();
		playingArea.setLayout(new OverlayLayout(playingArea));
		
		//Creates and places all of the labels with the gray background and black border.
		JPanel mineNumberPanel = new JPanel(new GridLayout(numRows, numCols));

		for(int i = 0; i < jlabSpaces.length; i++)
		{
			for(int k = 0; k < jlabSpaces[i].length; k++)
			{
				jlabSpaces[i][k] = new JLabel();
				jlabSpaces[i][k].setOpaque(true);
				jlabSpaces[i][k].setBackground(Color.LIGHT_GRAY);
				jlabSpaces[i][k].setBorder(new LineBorder(Color.BLACK, 1));
				mineNumberPanel.add(jlabSpaces[i][k]);
			}
		}
		
		//Generates the mines and calculates the numbers at each of the 8
		//squares surrounding a mine.
		minesweeper.fillMines(minesweeper.getNumMines(), jlabSpaces);

		//Panel for all the buttons.
		JPanel minePanel = new JPanel(new GridLayout(numRows, numCols));
		minePanel.setOpaque(false);
		
		//Adds the buttons on top of the labels.
		playingArea.add(minePanel);
		playingArea.add(mineNumberPanel);
		playableArea.add(playingArea, BorderLayout.CENTER);
		
		//Creates and adds all of the buttons plus their action listeners.
		for(int row = 0; row < jbtSpaces.length; row++)
		{
			for(int col = 0; col < jbtSpaces[row].length; col++)
			{
				jbtSpaces[row][col] = new JButton();
				jbtSpaces[row][col].setBackground(Color.BLUE);
				jbtSpaces[row][col].addMouseListener(
						new MouseAdapter()
						{
							public void mouseClicked(MouseEvent e)
							{
								//If its a right click and the button does not have a flag on it.
								if(!e.isMetaDown())
								{		
									if(!minesweeper.isFlag(jbtSpaces, (JButton)e.getSource()))
									{
										//If it is the first move, then the timer starts. 
										if(firstMove)
										{
											minesweeper.timerStart();
											firstMove = false;
										}
										
										((JButton)e.getSource()).setVisible(false);
										
										minesweeper.updateUndo((JButton)e.getSource());
										
										//If the button is a mine, end; otherwise continue with the game.
										if(minesweeper.isMine(jbtSpaces, (JButton)e.getSource()))
										{
											minesweeper.doEndProcess(gameGui, jbtSpaces, jlabSpaces, false);
											return;
										}
										else
										{
											minesweeper.removeButton(jbtSpaces, (JButton)e.getSource());
											minesweeper.showSpaces((JButton)e.getSource(), jlabSpaces, jbtSpaces);
										}
									}
								}
								else
								{
									//If the current spot is a flag, change to a question mark.
									if(minesweeper.isFlag(jbtSpaces, (JButton)e.getSource()))
									{
										minesweeper.deleteFlag(jbtSpaces, (JButton)e.getSource());
										((JButton)e.getSource()).setText("?");
										((JButton)e.getSource()).setForeground(Color.WHITE);
										((JButton)e.getSource()).setFont(((JButton)e.getSource()).getFont().deriveFont(Font.BOLD));
										((JButton)e.getSource()).setHorizontalAlignment(JButton.CENTER);
										((JButton)e.getSource()).setVerticalAlignment(JButton.CENTER);
										numMines++;
										mineResultsLabel.setText(String.format("- %s",numMines.toString()));
									}
									//If the current spot is a question mark change it to nothing.
									else if(minesweeper.isQuestionMark((JButton)e.getSource()))
									{
										((JButton)e.getSource()).setText("");
										((JButton)e.getSource()).setHorizontalAlignment(JButton.CENTER);
										((JButton)e.getSource()).setVerticalAlignment(JButton.CENTER);
									}
									//If the current spot is nothing, change it to a flag.
									else
									{	
										minesweeper.addFlag(jbtSpaces, (JButton)e.getSource());	
										numMines--;
										mineResultsLabel.setText(String.format("- %s",numMines.toString()));
									}
								}
								//After every click/right click of a button this checks to see if all the buttons
								//have flags and or have been clicked (signals the end of the game).
								if(minesweeper.isEnd())
									minesweeper.doEndProcess(gameGui, jbtSpaces, jlabSpaces, true);
							}
						});

				jbtSpaces[row][col].setVisible(true);
				minePanel.add(jbtSpaces[row][col]);
			}
		}
		
		//Creates the mine and flag numbers at the bottom of the GUI.
		JPanel resultsPanel = new JPanel();
		
		File file = new File("minesweeperMine.gif");
		ImageIcon icon = new ImageIcon(file.toString());
		Image image = icon.getImage();
		image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);

		JLabel mineLabel;
		//If the picture exists, display it; otherwise
		//display a text version of the picture (number of mines left).
		if(file.exists())
			mineLabel = new JLabel(icon);
		else
			mineLabel = new JLabel("Number of Mines Left");
		mineLabel.setToolTipText("Number of mines that are left in the grid");
		resultsPanel.add(mineLabel);
		resultsPanel.add(mineResultsLabel);
		
		playableArea.add(resultsPanel, BorderLayout.SOUTH);
		mineResultsLabel.setText(String.format("- %d", numMines));
		
		add(playableArea);
	}
	
	public MinesweeperGui(String title)
	{
		//Constructor to make the beginning GUI for choosing difficulty.
		super(title);
		
		JPanel difficultyPanel = new JPanel(new GridLayout(3,1));
		
		ButtonGroup group = new ButtonGroup();		
		
		//Creates and adds the radio buttons and action listeners for the 
		//easy, medium and hard difficulty settings.
		//Once one of the buttons have been chosen, the difficulty frame is disposed of
		//then the main game GUI is created with the correct size and number of mines.
		easyRadioBtn = new JRadioButton("Easy Difficulty - 9x9 tile grid, 10 mines");
		easyRadioBtn.setHorizontalAlignment(JRadioButton.LEFT);
		easyRadioBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						difficultyFrame.dispose();
						minesweeper = new Minesweeper(10,10,Minesweeper.EASY_MAZE_MINE_COUNT);
						gameGui = new MinesweeperGui("Minesweeper", 10, 10, minesweeper, Minesweeper.EASY_MAZE_MINE_COUNT);
						gameGui.pack();
						gameGui.setSize(500,500);
						gameGui.setLocationRelativeTo(null);
						gameGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						gameGui.setVisible(true);
					}
				});
		
		medRadioBtn = new JRadioButton("Medium Difficulty - 16x16 tile grid, 40 mines");
		medRadioBtn.setHorizontalAlignment(JRadioButton.LEFT);
		medRadioBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						difficultyFrame.dispose();
						minesweeper = new Minesweeper(16,16,Minesweeper.MED_MAZE_MINE_COUNT);
						gameGui = new MinesweeperGui("Minesweeper", 16, 16, minesweeper, Minesweeper.MED_MAZE_MINE_COUNT);
						gameGui.pack();
						gameGui.setSize(700,700);
						gameGui.setLocationRelativeTo(null);
						gameGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						gameGui.setVisible(true);
					}
				});
		
		hardRadioBtn = new JRadioButton("Hard Difficulty - 16x30 tile grid, 99 mines");
		hardRadioBtn.setHorizontalAlignment(JRadioButton.LEFT);
		hardRadioBtn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						difficultyFrame.dispose();
						minesweeper = new Minesweeper(16,30,Minesweeper.HARD_MAZE_MINE_COUNT);
						gameGui = new MinesweeperGui("Minesweeper", 16, 30, minesweeper, Minesweeper.HARD_MAZE_MINE_COUNT);
						gameGui.pack();
						gameGui.setSize(1100,700);
						gameGui.setLocationRelativeTo(null);
						gameGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						gameGui.setVisible(true);
					}
				});
		
		group.add(easyRadioBtn);
		group.add(medRadioBtn);
		group.add(hardRadioBtn);
		
		difficultyPanel.add(easyRadioBtn);
		difficultyPanel.add(medRadioBtn);
		difficultyPanel.add(hardRadioBtn);
		
		add(difficultyPanel);
		
	}
	
	public static void main(String[] args)
	{
		//Creates the difficulty settings GUI.
		difficultyFrame = new MinesweeperGui("Choose Difficulty");
		difficultyFrame.setIconImage(new ImageIcon("minesweeperMine.gif").getImage());
		difficultyFrame.pack();
		difficultyFrame.setSize(300,185);
		difficultyFrame.setLocationRelativeTo(null);
		difficultyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		difficultyFrame.setVisible(true);
	}
}
