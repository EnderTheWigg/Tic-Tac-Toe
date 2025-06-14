package source;
import javax.swing.*;

public class GameAI {
    private String botDifficulty;
    private String[] botBoard;
    public GameAI(JButton[][] board, String botDifficulty){
        this.botDifficulty = botDifficulty;
        botBoard = parseBoard(board);
    }

    public int makeMove(){
        if(botDifficulty.equals("Random")){
            return getRandomMove();
        }
        else if(botDifficulty.equals("Semi-Random")){
            //half chance random move, half perfect
        }
        else if(botDifficulty.equals("Perfect")){
            //return perfect move
        }
        System.out.println("AI difficulty selection Error");
        return -1;

    }

    public String[] parseBoard(JButton[][] gameBoard){
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

    private int perfectMove(){
        

        //recursively determine best possible move
        return -1;
    }
}
