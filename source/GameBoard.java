package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard {
    private JFrame frame;
    public static JButton[][] buttons;
    public static boolean playerXTurn = true;

    public GameBoard(){
        init();
    }

    private void init() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        JPanel board = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[i][j].addActionListener(new CellClickListener());
                buttons[i][j].setBackground(Color.WHITE);
                board.add(buttons[i][j]);
                
            }
        }
        frame.add(board, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static boolean checkForWin(){
        for (int i = 0; i < buttons.length; i++) {
            if((buttons[0][i].getText() == buttons[1][i].getText()) && (buttons[1][i].getText() == buttons[2][i].getText()) && !buttons[0][i].getText().equals("")){
                System.out.println("Win");
                return true;
            }
            if((buttons[i][0].getText() == buttons[i][1].getText()) && (buttons[i][1].getText() == buttons[i][2].getText()) && !buttons[i][0].getText().equals("")){
                System.out.println("Win");
                return true;
            }
        }
        if((buttons[0][0].getText() == buttons[1][1].getText()) && (buttons[1][1].getText() == buttons[2][2].getText()) && !buttons[0][0].getText().equals("")){
                System.out.println("Win");
                return true;
            }
        if((buttons[0][2].getText() == buttons[1][1].getText()) && (buttons[1][1].getText() == buttons[2][0].getText()) && !buttons[0][2].getText().equals("")){
            System.out.println("Win");
            return true;
        }

        return false;
    }

    
}

class CellClickListener implements ActionListener{

    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(GameBoard.buttons[i][j] == clickedButton){
                    if(GameBoard.buttons[i][j].getText().equals("")){
                        GameBoard.buttons[i][j].setText(GameBoard.playerXTurn ? "X" : "O");
                        GameBoard.playerXTurn = !GameBoard.playerXTurn;
                        GameBoard.checkForWin();
                    }
                }
            }
        }
    }
    

}