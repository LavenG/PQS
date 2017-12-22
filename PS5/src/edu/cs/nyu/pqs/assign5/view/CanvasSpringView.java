package edu.cs.nyu.pqs.assign5.view;

import edu.cs.nyu.pqs.assign5.ModelColor;
import edu.cs.nyu.pqs.assign5.exception.IllegalOperationException;
import edu.cs.nyu.pqs.assign5.model.CanvasModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is a implementation of {@link CanvasListener}.
 * In this class we create and render the UI based on the brush strokes.
 * The UI in this class is created using Java Swing.
 * This class just has UI implementation and model calls.
 */
public class CanvasSpringView implements CanvasListener {
  private final Canvas canvas;
  private final CanvasModel model;
  private final JFrame frame;
  private final JPanel buttonPanel;

  /**
   * A package private constructor for invoking an instance of this class. This could only
   * be done by using {@link ViewFactory}.
   *
   * @param model The model the listener needs to be registered to.
   */
  CanvasSpringView(CanvasModel model) {
    this.model = model;
    model.registerListener(this);
    canvas = new Canvas(model.getDimension());
    buttonPanel = new JPanel();
    frame = new JFrame();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void firePaintStartedEvent() {
    buttonPanel.setLayout(new GridLayout(1, 8));
    createAndAddButtonsToPanel();
    instantiateMouseListeners();
    frame.setSize(new Dimension(750, 730));
    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.add(canvas);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  private void instantiateMouseListeners() {
    canvas.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        try {
          model.mousePressEventHandlerAtModel(e.getPoint());
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(canvas, ex.getMessage());
        }
      }
    });

    canvas.addMouseMotionListener(new MouseMotionAdapter() {

      @Override
      public void mouseDragged(MouseEvent e) {
        try {
          try {
            model.mouseDragEventHandlerAtModel(e.getX(), e.getY());
          } catch (IllegalOperationException ex) {
            JOptionPane.showMessageDialog(canvas, ex.getMessage());
          }
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(canvas, ex.getMessage());
        }
      }
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void drewOnCanvas(int x, int y, int lineWidth) {
    canvas.recordBrushStroke(x, y, lineWidth);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearCanvasAndResetBrushColor() {
    canvas.clearCanvas();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void changeBrushColorInUse(Color color) {
    canvas.updateBrushColor(color);
  }


  private void createAndAddButtonsToPanel() {
    createAndAddTextButton();
    createAndAddColorButton();
  }

  private void createAndAddTextButton() {
    JButton clear = createClearButton("Clear");
    buttonPanel.add(clear);

    JButton incrementLineWidthButton = createIncrementButton("+");
    buttonPanel.add(incrementLineWidthButton);

    JButton decrementLineWidthButton = createDecrementButton("-");
    buttonPanel.add(decrementLineWidthButton);

    JButton eraseButton = createEraseButton("Erase");
    buttonPanel.add(eraseButton);
  }

  private void createAndAddColorButton() {
    JButton blackButton = createColorButton(Color.BLACK);
    buttonPanel.add(blackButton);

    JButton redButton = createColorButton(Color.RED);
    buttonPanel.add(redButton);

    JButton blueButton = createColorButton(Color.BLUE);
    buttonPanel.add(blueButton);

    JButton greenButton = createColorButton(Color.GREEN);
    buttonPanel.add(greenButton);

    JButton yellowButton = createColorButton(Color.YELLOW);
    buttonPanel.add(yellowButton);
  }

  private JButton createEraseButton(String text) {
    JButton eraseButton = new JButton(text);
    eraseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.setColor(ModelColor.WHITE);
      }
    });
    return eraseButton;
  }

  private JButton createDecrementButton(String text) {
    JButton decrementButton = new JButton(text);
    decrementButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        decrementBrushWidth();
      }
    });
    return decrementButton;
  }

  private void decrementBrushWidth() {
    try {
      model.decreaseBrushWidth();
    } catch (IllegalOperationException e) {
      JOptionPane.showMessageDialog(canvas, e.getMessage());
    }
  }

  private JButton createClearButton(String text) {
    JButton clearButton = new JButton(text);
    clearButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.clearCanvas();
      }
    });
    return clearButton;
  }

  private JButton createIncrementButton(String text) {
    JButton incrementButton = new JButton(text);
    incrementButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        incrementBrushWidth();
      }
    });
    return incrementButton;
  }

  private void incrementBrushWidth() {
    try {
      model.increaseBrushWidth();
    } catch (IllegalOperationException e) {
      JOptionPane.showMessageDialog(canvas, e.getMessage());
    }
  }

  private JButton createColorButton(Color color) {
    JButton colorButton = new JButton("");
    colorButton.setBackground(color);
    colorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.setColor(ModelColor.getModelColor(color));
      }
    });
    return colorButton;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Canvas Spring View";
  }
}