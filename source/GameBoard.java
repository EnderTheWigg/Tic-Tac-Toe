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
    public boolean botEnabled = false;;

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
        //No win if random AI?
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
                return true;
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
                return true;
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
            return true;
        }

        return false;
    }
    public String getBotDifficulty(){
        return botDifficulty;
    }

    public JButton[][] getButtons(){
        return buttons;
    }

}

class CellClickListener implements ActionListener{
    GameBoard board;
    String botDifficulty;
    JButton[][] buttons;
    boolean isPlayerXTurn;
    public CellClickListener(GameBoard x){
        this.board = x;
        botDifficulty = board.getBotDifficulty();
        buttons = board.getButtons();
        //isPlayerXTurn = board.isPlayerXTurn();
        isPlayerXTurn = GameBoard.playerXTurn;
        //System.out.println(isPlayerXTurn);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();


        //I Think I shouldn't be using AI here, because it won't trigger as it won't ever move? Or make it so ai auto runs after player moves.
        if(botDifficulty.equals("None") || botDifficulty.equals("AI Difficulty")){

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(buttons[i][j] == clickedButton){
                        if(buttons[i][j].getText().equals("")){
                            buttons[i][j].setText(GameBoard.playerXTurn ? "X" : "O");
                            board.checkForWin();
                            GameBoard.playerXTurn = !GameBoard.playerXTurn;
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
                            GameBoard.playerXTurn = !GameBoard.playerXTurn;
                        }
                    }
                }
            }

            //and we can just have the ai go right after
            GameAI bot = new GameAI(buttons, botDifficulty);
            int gridNum = bot.makeMove();
            int k = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(k == gridNum){
                        buttons[i][j].setText("O");
                        board.checkForWin();
                        GameBoard.playerXTurn = !GameBoard.playerXTurn;
                    }
                    k++;
                }
            }
        }
    }
}