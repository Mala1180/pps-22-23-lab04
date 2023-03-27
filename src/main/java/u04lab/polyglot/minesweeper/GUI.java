package u04lab.polyglot.minesweeper;


import u04lab.polyglot.minesweeper.logics.Logics;
import u04lab.polyglot.minesweeper.logics.LogicsImpl;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private final Logics logics;

    public GUI(int size, int numberOfMines) {
        this.logics = new LogicsImpl(size, numberOfMines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, panel);

        ActionListener onClick = (e) -> {
            final JButton bt = (JButton) e.getSource();
            final Pair<Integer, Integer> pos = buttons.get(bt);
            // call the logic here to tell it that cell at 'pos' has been selected
            boolean aMineWasFound = this.logics.hit(pos.getX(), pos.getY());
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                drawBoard();
            }
            // call the logic here to ask if there is victory
            boolean isThereVictory = this.logics.isGameWon();
            if (isThereVictory) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton) e.getSource();
                if (bt.isEnabled()) {
                    final Pair<Integer, Integer> pos = buttons.get(bt);
                    // call the logic here to put/remove a flag
                    logics.toggleFlag(pos.getX(), pos.getY());
                }
                drawBoard();
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb, new Pair<>(i, j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }

    private void quitGame() {
        this.drawBoard();
        for (var entry : this.buttons.entrySet()) {
            // call the logic here
            var isMine = this.logics.isMine(entry.getValue().getX(), entry.getValue().getY());
            // if this button is a mine, draw it "*"
            if (isMine) {
                entry.getKey().setText("*");
                entry.getKey().setEnabled(false);
            }
        }
    }

    private void drawBoard() {
        for (var entry : this.buttons.entrySet()) {
            // call the logic here
            if (this.logics.isShown(entry.getValue().getX(), entry.getValue().getY())) {
                entry.getKey().setEnabled(false);
                var adjacentMinesNumber = this.logics.getAdjacentMinesNumber(entry.getValue().getX(), entry.getValue().getY());
                // if this button is a cell with counter, put the number
                entry.getKey().setText(adjacentMinesNumber + "");
                // if this button has a flag, put the flag
            } else {
                if (this.logics.isFlag(entry.getValue().getX(), entry.getValue().getY())) {
                    entry.getKey().setText("F");
                } else {
                    entry.getKey().setText(" ");
                }
            }
        }
    }

}
