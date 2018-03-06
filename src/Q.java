import java.util.*;

public class Q {
    ArrayList<Pair> qMatrix; //saves the boards configs and their values
    LinkedList<String> thisGame; //saves the configs of the current games

    Q(){
        qMatrix = new ArrayList<>();
        thisGame = new LinkedList<>();
    }

    public void savePlay(String board){ //saves a board
        thisGame.add(board);
    }

    public void printQ(){ //to see the current matrix
        for (Pair p : qMatrix) {
            System.out.println("String -> "+p.board.substring(0,3)+"|"+p.board.substring(3,6)+"|"+p.board.substring(6,9)+ "  Value -> "+p.value);
        }
    }

    public void printThisGame(){ //to see the game
        for (String s : thisGame) {
            System.out.println("String -> "+s);
        }
    }

    public int containsBoard(String board){ //checks if the config already exists in the Q Matrix
        for(int i=0;i<qMatrix.size();i++){
            if(qMatrix.get(i).board.compareTo(board)==0)
                return i;
        }
        return -1;
    }


    public void gameEnded(double reward){//+1 if won, -1 if lost, 0 if draw
        String board;
        int first=0,pos;
        double prevValue =0;
        //thisGame.pollLast();
        while(!thisGame.isEmpty()) { //analise the last game
            if ((pos=containsBoard(board = thisGame.pollLast()))!=-1) {//if this config has been seen before
                if(first==0) {
                    first++;
                    prevValue=reward;
                }
                //update its value
                double value = qMatrix.get(pos).value;
                qMatrix.get(pos).value=value+0.9*(prevValue-value);
                prevValue=value+0.9*(prevValue-value);
            }
            else{ //if this config has not been seen before, add it to the Q Matrix
                if(first==0){
                    first++;
                    qMatrix.add(new Pair(board,reward));
                    prevValue=reward;
                }
                else{//give it a value
                    qMatrix.add(new Pair(board,0.9*(prevValue)));
                    prevValue=0.9*prevValue;
                }
                if(pos!=-1)
                    System.out.println("String -> "+board+" Value -> "+qMatrix.get(pos).value);
            }
        }
    }

    public boolean hasState(String board){//check if the Q Matrix has this config
        return !qMatrix.isEmpty() && containsBoard(board)!=-1;
    }

    public int checkNext(char[] board){//choosing what to play
        char[] tmp;
        int bestPos=-1;
        double value=Double.MIN_VALUE,best=Double.MIN_VALUE;
        for(int i=0;i<board.length;i++){
            if(board[i] ==' '){
                tmp = board.clone();
                tmp[i]='O';
                int pos;
                if((pos = containsBoard(String.valueOf(tmp)))!=-1){//checks if this config is in Q Matrix
                    value = qMatrix.get(pos).value; //and gets its value
                    if(best<value) { //if it is the best option, update
                        best = value;
                        bestPos = i;
                    }
                }
                else { //if this config is not in Q Matrix, its value is zero
                    //lets try it if there isnt a better option
                    if(best<0)
                        value=0;
                    best = value;
                    bestPos = i;
                }
            }

        }
        return bestPos;
    }
}
