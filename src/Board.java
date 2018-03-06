import static java.lang.StrictMath.floor;

public class Board {
    char board[];
    Board(){
        board = new char[9];
        for(int i=0;i<9;i++)
            board[i]=' ';
    }

    public boolean emptyPosition(int position){
        if(position<0 || position>8)
            return false;
        if(board[position]==' ')
            return true;
        return false;
    }

    public void play(int position,char player){
        board[position]=player;
    }

    public int playRandom(){
        while(true) {
            int guess= (int) Math.floor(Math.random() * 9);
            if(emptyPosition(guess)) {
                //play(guess, 'O');
                return guess;
            }

        }
        /*for(int i=0;i<9;i++){
            if(board[i]==' ') {
                board[i] = 'O';
                return;
            }
        }
        */
    }

    public void printBoard(){
        System.out.println(" -------------");
        for(int i = 0;i<9;i++){
            if(board[i]==' ')
                System.out.print(" | "+i);
            else
                System.out.print(" | "+board[i]);
            if(i==2 || i==5 || i==8) {
                System.out.println(" |");
                System.out.println(" -------------");
            }
            if(i==8)
                System.out.println();
        }
    }

    public boolean isFull() {
        for(int i=0;i<9;i++)
            if(board[i]==' ')
                return false;
        return true;
    }

    public char whoWon(){
        char result;
        if((result=checkHorizontal())!='n' && result!=' ')
            return result;
        else if((result=checkVertical())!='n' && result!=' ')
            return result;
        else if((result=checkDiagonal())!='n' && result!=' ')
            return result;
        else
            return 'n';

    }

    private char checkHorizontal(){
        if(board[0]==board[1] && board[1]==board[2])
            return board[0];
        else if(board[3]==board[4] && board[4]==board[5])
            return board[3];
        else if(board[6]==board[7] && board[7]==board[8])
            return board[6];
        return 'n';
    }

    private char checkVertical(){
        if(board[0]==board[3] && board[3]==board[6])
            return board[0];
        else if(board[1]==board[4] && board[4]==board[7])
            return board[1];
        else if(board[2]==board[5] && board[5]==board[8])
            return board[2];
        return 'n';
    }

    private char checkDiagonal(){
        if(board[0]==board[4] && board[4]==board[8])
            return board[0];
        else if(board[2]==board[4] && board[4]==board[6])
            return board[2];
        return 'n';
    }
}



