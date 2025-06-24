package source;
import java.util.ArrayList;

import javax.swing.*;

public class GameAI {
    private String botDifficulty;
    private String[] botBoard;
    int iter = 0;
    public GameAI(JButton[][] board, String botDifficulty){
        this.botDifficulty = botDifficulty;
        botBoard = parseBoard(board);
    }

    public int makeMove(){
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
        return -1;

    }

    public static String[] parseBoard(JButton[][] gameBoard){
        //Convert board into a 1D array for ease of use
        int k = 0;
        String[] parsedBoard = new String[9];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                parsedBoard[k] = gameBoard[i][j].getText();
                k++;
            }
        }

        return parsedBoard;
    }

    private int getRandomMove(){
        //Return random valid spot on board
        boolean validMove = false;
        boolean hasPossibleMove = false;
        for (String s : botBoard) {
            if(s.equals(""))
                hasPossibleMove = true;
        }
        if(!hasPossibleMove){
            return -1;
        }
        while(validMove == false){
            int move = (int)Math.ceil(Math.random() * 8);
            if(botBoard[move].equals("")){
                return move;
            }
        }
        return -1;
    }

    private int getPerfectMove(){
        int bestVal = -1000;
        int bestMove = -1;

        for (int i = 0; i < botBoard.length; i++) {
            if(botBoard[i].equals("")){
                botBoard[i] = "O";

                int moveVal = miniMax(botBoard, "X");

                botBoard[i] = "";

                if(moveVal > bestVal){
                    bestMove = i;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove;
    }

    public int miniMax(String[] board, String player){
        int bestVal;

        ArrayList<Integer> emptyList = getEmptyIndicies(board);
        if(emptyList.size() == 0)
            return 0;
        
        else if(checkForWin(board, "X"))
            return  -1;
        
        else if(checkForWin(board, "O"))
            return 1;
        
        if(player.equals("O")){
            bestVal = -1000;
            for (int i = 0; i < emptyList.size()-1; i++) {
                board[emptyList.get(i)] = "O";
                bestVal = Math.max(bestVal, miniMax(board, "X"));
                iter++;
                board[emptyList.get(i)] = "";
            }
            return bestVal;
        }
        else{
            bestVal = 1000;
                for (int i = 0; i < emptyList.size()-1; i++) {
                board[emptyList.get(i)] = "X";
                bestVal = Math.min(bestVal, miniMax(board, "O"));
                iter++;
                board[emptyList.get(i)] = "";
            }
            return bestVal;
        }
    }

    private ArrayList<Integer> getEmptyIndicies(String[] board){
        ArrayList<Integer> empty = new ArrayList<Integer>();
        for (int i = 0; i < board.length; i++) {
                if (board[i].equals("")) {
                    empty.add(i);
                }
        }
        return empty;
    }

    public boolean checkForWin(String[] board, String player){
        for (int i = 0; i < board.length; i++) {
            if(i <= 2){
                if((!board[i].equals("") && board[i].equals(player)) && board[i].equals(board[i+3]) && board[i+3].equals(board[i+6])){
                    return true;
                }
            }
            if(i == 0 || i == 3 || i == 6){
                if((!board[i].equals("") && board[i].equals(player)) && board[i].equals(board[i+1]) && board[i+1].equals(board[i+2])){
                    return true;
                }
            }
            if(i == 0){
                if((!board[i].equals("") && board[i].equals(player)) && board[i].equals(board[i+4]) && board[i+4].equals(board[i+8])){
                    return true;
                }
            }
            if(i == 2){
                if((!board[i].equals("") && board[i].equals(player)) && board[i].equals(board[i+2]) && board[i+2].equals(board[i+4])){
                    return true;
                }
            }
        }
        return false;
    }
}
