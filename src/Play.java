import java.io.IOError;
import java.util.Scanner;

public class Play   {
    public static void main(String args[]) {
        Board board = new Board();
        Q q = new Q();
        Scanner input = new Scanner(System.in);
        Boolean firstTurn=true;
        char winner;
        int games = 0;
        while(true) {
            if (firstTurn) {                    //prints the board in the first turn a game
                board.printBoard();
                firstTurn = false;
            }

            if (games < 1000) { //just to train a bit
                board.play(board.playRandom(), 'X');
                //System.out.println("plays -> "+ plays);
            } else {
                //System.out.println("whats your play?");
                while (true){
                    int humanPlay = input.nextInt();
                    if (board.emptyPosition(humanPlay)) {
                        board.play(humanPlay, 'X');
                        break;
                    } //else
                        //System.out.println("Invalid play.");
                    }
            }
            q.savePlay(String.valueOf(board.board));
            board.printBoard();
            //
            //if human player ended game
            if(board.isFull() || board.whoWon()!='n'){
                //q.printThisGame();
                if(board.whoWon()!='n') {
                    System.out.println("X won the game.\n");
                    q.gameEnded(-1);
                }
                else {
                    System.out.println("It's a draw.\n");
                    q.gameEnded(1);
                }
                board = new Board();
                firstTurn=true;
                q.printQ();
                games++;
                System.out.println("games ->"+games);
            }
            //end of if the human player ended game
            //

            else{
                if(q.hasState(String.valueOf(board.board))){
                    int AIPlay = q.checkNext(board.board);
                    //System.out.println("AI play = " +AIPlay);
                    if(AIPlay==-1)
                        board.play(board.playRandom(),'O');
                    else
                        board.play(AIPlay,'O');
                }
                else {
                    board.play(board.playRandom(),'O');
                }
                q.savePlay(String.valueOf(board.board));
                board.printBoard();
                //
                //if AI ended game
                if(board.isFull() || board.whoWon()!='n'){
                    //q.printThisGame();
                    if(board.whoWon()!='n') {
                        System.out.println("O won the game.\n");
                        q.gameEnded(1);
                    }
                    else {
                        System.out.println("It's a draw.\n");
                        q.gameEnded(1);
                    }
                    board = new Board();
                    firstTurn=true;
                    q.printQ();
                    games++;
                    System.out.println("games ->"+games);
                }
                //end of if AI ended game
                //
            }

        }
    }
}
