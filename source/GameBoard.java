package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class GameBoard {
    private JFrame frame;
    public static JButton[][] buttons;
    public static boolean playerXTurn = true;
    public static String botDifficulty;
    public boolean botEnabled = false;
    public boolean isGameOver = false;

    public GameBoard(String botDiff){
        botDifficulty = botDiff;
        if(botDifficulty.equals("None") || botDifficulty.equals("AI Difficulty"))
            botEnabled = true;
        else
            botEnabled = false;
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
                buttons[i][j].addActionListener(new CellClickListener(this));
                buttons[i][j].setBackground(Color.WHITE);
                board.add(buttons[i][j]);
            }
        }
        frame.add(board, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public boolean checkForWin(){
        for (int i = 0; i < buttons.length; i++) {
            if((buttons[0][i].getText() == buttons[1][i].getText()) && (buttons[1][i].getText() == buttons[2][i].getText()) && !buttons[0][i].getText().equals("")){
                if(playerXTurn){
                    buttons[0][i].setBackground(Color.GREEN);
                    buttons[1][i].setBackground(Color.GREEN);
                    buttons[2][i].setBackground(Color.GREEN);
                }
                else{
                    buttons[0][i].setBackground(Color.RED);
                    buttons[1][i].setBackground(Color.RED);
                    buttons[2][i].setBackground(Color.RED);
                }
                showMessageDialog(null, "Player " + (playerXTurn ? "X": "O") + " Wins!");
                return gameOver();
            }
        }
        for (int i = 0; i < buttons.length; i++) {
            if((buttons[i][0].getText() == buttons[i][1].getText()) && (buttons[i][1].getText() == buttons[i][2].getText()) && !buttons[i][0].getText().equals("")){
                if(playerXTurn){
                    buttons[i][0].setBackground(Color.GREEN);
                    buttons[i][1].setBackground(Color.GREEN);
                    buttons[i][2].setBackground(Color.GREEN);
                }
                else{
                    buttons[i][0].setBackground(Color.RED);
                    buttons[i][1].setBackground(Color.RED);
                    buttons[i][2].setBackground(Color.RED);
                }
                showMessageDialog(null, "Player " + (playerXTurn ? "X": "O") + " Wins!");
                return gameOver();
            }
        }
        if((buttons[0][0].getText() == buttons[1][1].getText()) && (buttons[1][1].getText() == buttons[2][2].getText()) && !buttons[0][0].getText().equals("")){

                if(playerXTurn){
                    buttons[0][0].setBackground(Color.GREEN);
                    buttons[1][1].setBackground(Color.GREEN);
                    buttons[2][2].setBackground(Color.GREEN);
                }
                else{
                    buttons[0][0].setBackground(Color.RED);
                    buttons[1][1].setBackground(Color.RED);
                    buttons[2][2].setBackground(Color.RED);
                }
                showMessageDialog(null, "Player " + (playerXTurn ? "X": "O") + " Wins!");
                return gameOver();
            }
        if((buttons[0][2].getText() == buttons[1][1].getText()) && (buttons[1][1].getText() == buttons[2][0].getText()) && !buttons[0][2].getText().equals("")){
            if(playerXTurn){
                    buttons[0][2].setBackground(Color.GREEN);
                    buttons[1][1].setBackground(Color.GREEN);
                    buttons[2][0].setBackground(Color.GREEN);
                }
                else{
                    buttons[0][2].setBackground(Color.RED);
                    buttons[1][1].setBackground(Color.RED);
                    buttons[2][0].setBackground(Color.RED);
                }
                showMessageDialog(null, "Player " + (playerXTurn ? "X": "O") + " Wins!");
            return gameOver();
        }

        return false;
    }
    public String getBotDifficulty(){
        return botDifficulty;
    }

    public JButton[][] getButtons(){
        return buttons;
    }

    public boolean getIsGameOver(){
        return isGameOver;
    }

    public boolean getIsPlayerXTurn(){
        return playerXTurn;
    }

    public boolean nextTurn(){
        playerXTurn = !playerXTurn;
        return playerXTurn;
    }

    public boolean gameOver(){
        isGameOver = true;
        return isGameOver;
    }

}

class CellClickListener implements ActionListener{
    GameBoard board;
    String botDifficulty;
    JButton[][] buttons;
    public CellClickListener(GameBoard x){
        this.board = x;
        botDifficulty = board.getBotDifficulty();
        buttons = board.getButtons();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();

        if(board.getIsGameOver() == false){

            if(botDifficulty.equals("None") || botDifficulty.equals("AI Difficulty")){

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(buttons[i][j] == clickedButton){
                            if(buttons[i][j].getText().equals("")){
                                buttons[i][j].setText(board.getIsPlayerXTurn() ? "X" : "O");
                                board.checkForWin();
                                board.nextTurn();
                            }
                        }
                    }
                }
            }
            else{
                //Theoretically this will trigger when a player moves, so we know it's the player
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(buttons[i][j] == clickedButton){
                            if(buttons[i][j].getText().equals("")){
                                buttons[i][j].setText("X");
                                board.checkForWin();
                                board.nextTurn();
                            }
                        }
                    }
                }
                if(!board.getIsGameOver()){
                    //and we can just have the ai go right after
                    GameAI bot = new GameAI(buttons, botDifficulty);
                    int[] gridNum = bot.makeMove();
                    if(gridNum != null){
                        buttons[gridNum[0]][gridNum[1]].setText("O");
                        board.checkForWin();
                        board.nextTurn();
                    }
                }
            }
        }
    }
}