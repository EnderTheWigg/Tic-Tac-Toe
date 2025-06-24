package source;

import java.util.ArrayList;

public class MiniMaxGameTree{
    TNode root;
    boolean isXTurn;
    String[] currentBoard;


    public MiniMaxGameTree(String[] x){
        //root = new TNode(GameAI.parseBoard(x.getButtons()));
        isXTurn = true; 
    }

    public int miniMax(String[] board, String player){
        int iterationResult = 0;
        int greatest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int index = -1;

        isXTurn = !isXTurn; //starting recursion as false
        ArrayList<Integer> emptyList = getEmptyIndicies(board);
        if(emptyList.size() == 0)
            return iterationResult += 0;
        
        else if(checkForWin(board, "X"))
            return iterationResult += -1;
        
        else if(checkForWin(board, "O"))
            return iterationResult += 1;

        for (int i = 0; i < emptyList.size()-1; i++) {
            String[] newBoard = board;
            newBoard[emptyList.get(i)] = (isXTurn ? "X" : "O");
            iterationResult = miniMax(newBoard, (isXTurn ? "O" : "X"));
            if(isXTurn && iterationResult > greatest){
                greatest = iterationResult;
                index = emptyList.get(i);
            }
            else if (!isXTurn && iterationResult < smallest) {
                smallest = iterationResult;
                index = emptyList.get(i);
            }
        }
        return index;
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
