package edu.cs.nyu.pqs.assign4.view;

import edu.cs.nyu.pqs.assign4.constants.ConstantsForGame;
import edu.cs.nyu.pqs.assign4.exception.IllegalMoveException;
import edu.cs.nyu.pqs.assign4.model.ConnectFourModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is a implementation of {@link ConnectFourListener}.
 * In this class we create and render the UI based on the moves.
 * The UI in this class is created using {@link javax.swing.Spring}.
 * This class just has UI implementation and model calls.
 */
public class ConnectFourSpringView implements ConnectFourListener {

  private final JFrame frame = new JFrame();
  private final JButton[][] buttonMatrix =
      new JButton[ConstantsForGame.numRows][ConstantsForGame.numColumns];
  private final GridLayout gridLayout =
      new GridLayout(ConstantsForGame.numRows, ConstantsForGame.numColumns);
  private JLabel label = new JLabel();
  private final JPanel gamePanel = new JPanel(gridLayout);
  private final ConnectFourModel model;
  private final int numberOfRows;
  private final int numberOfColumns;

  /**
   * A package private constructor for invoking an instance of this class. This could only
   * be done by using {@link ViewFactory}.
   *
   * @param model The model the listener needs to be registered to.
   */
  ConnectFourSpringView(ConnectFourModel model) {
    this.model = model;
    numberOfRows = ConstantsForGame.numRows;
    numberOfColumns = ConstantsForGame.numColumns;
    model.registerListner(this);
    initializePanel();
    JPanel topPanel = new JPanel(new BorderLayout());
    label.setVerticalAlignment(JLabel.BOTTOM);
    label.setHorizontalAlignment(JLabel.CENTER);
    topPanel.add(label, BorderLayout.CENTER);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(topPanel, BorderLayout.NORTH);
    frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 1200);
    frame.setVisible(true);
  }

  private void initializePanel() {
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfColumns; j++) {
        JButton button = createButtonForGrid(j);
        button.setBackground(Color.WHITE);
        gamePanel.add(button);
        buttonMatrix[i][j] = button;
      }
    }
  }

  private JButton createButtonForGrid(int columnNumber) {
    JButton button = new JButton();
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        moveMakeActionPerformed(columnNumber);
      };
    });
    return button;
  }

  private void moveMakeActionPerformed(int colIndex) {
    try {
      model.makeMove(colIndex);
    } catch (IllegalMoveException e) {
      JOptionPane.showMessageDialog(frame, e.getMessage());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameStarted(Color[][] layout) {
    displaySelectScreen("What do you want to do?");
  }

  private void displaySelectScreen(String message) {
    Object[] options =
        {"Play two player", "Play one player", "Quit"};
    int selection =
        JOptionPane.showOptionDialog(frame, message, "Connect Four",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, options, options[0]);
    switch (selection) {
      case -1:
        System.exit(0);
        break;
      case 0:
        String playerOneName = getPlayerName();
        String playerTwoName = getPlayerName();
        model.initiateNewTwoPlayerGame(playerOneName, playerTwoName);
        break;
      case 1:
        playerOneName = getPlayerName();
        model.initiateNewOnePlayerGame(playerOneName);
        break;
      case 2:
        System.exit(0);
        break;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameWon(Color[][] layout, String currentPlayer) {
    renderView(layout);
    displayGameWon(currentPlayer);
  }

  private void displayGameWon(String currentPlayer) {
    displaySelectScreen("Game was won by " + currentPlayer + ". What do you want to do?");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameTied(Color[][] layout) {
    renderView(layout);
    displayGameTied();
  }

  private void displayGameTied() {
    displaySelectScreen("Game was tied. What do you want to do?");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void moveCompletedSwitchPlayer(Color[][] layout, String name) {
    renderView(layout);
    changePlayerName(name);
  }

  private void renderView(Color[][] layout) {
    for (int i=0; i < layout.length; i++) {
      for (int j=0; j < layout[0].length; j++) {
        buttonMatrix[i][j].setBackground(layout[i][j]);
      }
    }
  }

  private void changePlayerName(String name) {
    label.setText(name);
  }

  private String getPlayerName() {
    String result = JOptionPane.showInputDialog(frame,"Enter player name:");
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Connect four spring view.";
  }
}
