package source;

import java.util.ArrayList;

public class MiniMaxGameTree{
    TNode root;
    boolean isXTurn;
    String[] currentBoard;

//Find empty slots
//Go through every empty space, moving there
//check for win
//recurse
//seperate method to score?
//track whether it should be the ai or players turn
    public MiniMaxGameTree(GameBoard x){
        root = new TNode(GameAI.parseBoard(x.getButtons()));
        isXTurn = true; //because it needs to start false in the recursion
    }

    private void miniMax(String[] board){
        int iterationResult = 0;

        isXTurn = !isXTurn; //starting recursion as false
        ArrayList<Integer> emptyList = getEmptyIndicies(board);
        if(emptyList.size() == 0)
            iterationResult += 0;
        
        else if(checkForWin(board, "X"))
            iterationResult += -1;
        
        else if(checkForWin(board, "O"))
            iterationResult += 1;
        

        for (int i = 0; i < emptyList.size()-1; i++) {
            String[] newBoard = board;
            newBoard[emptyList.get(i)] = (isXTurn ? "X" : "O");
            miniMax(newBoard);
        }
    }

    private ArrayList<Integer> getEmptyIndicies(String[] board){
        ArrayList empty = new ArrayList<>();
        for (int i = 0; i < currentBoard.length; i++) {
                if (currentBoard[i].equals("")) {
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
