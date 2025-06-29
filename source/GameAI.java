package source;

import javax.swing.*;

public class GameAI {
    private String botDifficulty;
    private String[][] botBoard;
    int iter = 0;
    public GameAI(JButton[][] board, String botDifficulty){
        this.botDifficulty = botDifficulty;
        botBoard = parseBoard(board);
    }

    public int[] makeMove(){
        if(botDifficulty.equals("Random")){
            return getRandomMove();
        }
        else if(botDifficulty.equals("Semi-Random")){
            if (Math.random() < 0.5) 
                return getPerfectMove();
            else
                return getRandomMove();
        }
        else if(botDifficulty.equals("Perfect")){
            return getPerfectMove();
        }
        System.out.println("AI difficulty selection Error");
        return null;

    }

    public static String[][] parseBoard(JButton[][] gameBoard){
        //Convert board into a 1D array for ease of use
        String[][] parsedBoard = new String[3][3];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                parsedBoard[i][j] = gameBoard[i][j].getText();
            }
        }

        return parsedBoard;
    }

    private int[] getRandomMove(){
        //Return random valid spot on board
        boolean validMove = false;
        boolean hasMove = false;
        hasMove = hasPossibleMove(botBoard);
        if(!hasMove){
            return null;
        }
        while(validMove == false){
            int num = (int)Math.ceil(Math.random() * 8);
            int k = 0;
            for (int i = 0; i < botBoard.length; i++) {
                for (int j = 0; j < botBoard.length; j++) {
                    if(k == num && botBoard[i][j].equals(""))
                        return new int[]{i, j};
                    k++;
                }
                }
            }
        
        return null;
    }

    private int[] getPerfectMove(){
        int bestVal = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(botBoard[i][j].equals("")){
                    botBoard[i][j] = "O";

                    int moveVal = minimax(botBoard, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

                    botBoard[i][j] = "";
                    //iter++;

                    if(moveVal > bestVal){
                        bestRow = i;
                        bestCol = j;
                        bestVal = moveVal;
                    }
                }
            }
            
        }
        System.out.println(iter);
        return new int[]{bestRow, bestCol};
    }


    private boolean hasPossibleMove(String[][] board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j].equals(""))
                    return true;
            }
        }
        return false;
    }
    public int minimax(String[][] board, int depth, boolean isMaximizing, int alpha, int beta){
        int bestVal;
        
        if(!hasPossibleMove(board))
            return 0;
        
        int score = checkForWin(board);
        if(score != 0)
            return score;
        
        if(isMaximizing){
            bestVal = Integer.MIN_VALUE;
            outerLoop:
            for (int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++){
                    if(board[i][j].equals("")){
                        board[i][j] = "O";
                        int val = minimax(board, depth + 1, false, alpha, beta);
                        iter++;
                        board[i][j] = "";
                        bestVal = Math.max(bestVal, val);
                        alpha = Math.max(alpha, bestVal);
                        if(beta <= alpha)
                            break outerLoop;
                    }
                }
    
            }
            return bestVal;
        }
        else{
            bestVal = Integer.MAX_VALUE;
            outerLoop:
            for (int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++){
                    if(board[i][j].equals("")){
                        board[i][j] = "X";
                        int val = minimax(board, depth + 1, true, alpha, beta);
                        iter++;
                        board[i][j] = "";
                        bestVal = Math.min(bestVal, val);
                        beta = Math.min(beta, bestVal);
                        if(beta <= alpha)
                            break outerLoop;
                    }
                }
    
            }
            return bestVal;
        }
    }

    public int checkForWin(String[][] board){
        for (int i = 0; i < 3; i++) {
            if(board[i][0].equals("X") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return -10;
            if(board[i][0].equals("O") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return 10;
        }
        for (int i = 0; i < 3; i++) {
            if(board[0][i].equals("X") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
                return -10;
            if(board[0][i].equals("O") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
                return 10;
        }
        if(board[0][0].equals("X") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return -10;
        if(board[0][0].equals("O") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return 10;
        if(board[0][2].equals("X") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
            return -10;
        if(board[0][2].equals("O") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
            return 10;
        
        return 0;
    }
}
