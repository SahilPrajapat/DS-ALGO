import java.util.*;

public class recursionBacktracking {
    public static Scanner scn = new Scanner(System.in);

    public static void floodfill(int[][]maze, int row, int col, String ans, boolean[][] visited){
        if(row < 0 || col < 0 || row == maze.length || col == maze[0].length || maze[row][col] == 1 || visited[row][col] == true ){
            return ;
        }
        if(row == maze.length - 1 && col == maze[0].length - 1){
            System.out.println(ans);
            return ;
        }

        visited[row][col] = true;
        floodfill(maze, row - 1, col, ans + "t", visited);
        floodfill(maze, row, col - 1, ans + "l", visited);
        floodfill(maze, row + 1, col, ans + "d", visited);
        floodfill(maze, row, col + 1, ans + "r", visited);
        visited[row][col] = false;
    }

    public static void printTargetSumSubset(int[] arr, int idx, String set, int sos, int tar){
        if(idx == arr.length){
            if(sos == tar){
                System.out.println(set + ".");
            }
            return ;
        }

        printTargetSumSubset(arr, idx + 1, set + arr[idx] + ", ", sos + arr[idx], tar);
        printTargetSumSubset(arr, idx + 1, set, sos, tar);

    }

    public static void printNQueen(int[][]chess, String qsf, int row){
        if(row == chess.length){
            System.out.println(qsf + ".");
            return ;
        }
        for(int col = 0 ; col < chess.length; col++){
            if(chess[row][col] == 0 && isQueenSafe(chess,row, col) == true){
            chess[row][col] = 1;
            printNQueen(chess, qsf + row + "-" + col + ", ", row + 1);
            chess[row][col] = 0;   
            }
        }

    }
    public static boolean isQueenSafe(int[][]chess, int row, int col){
        for(int i = row-1, j = col; i>=0; i--){
            if(chess[i][j] == 1){
                return false;
            }
        }

        for(int i = row -1, j = col-1; i>=0 && j >= 0; i--,j--){
            if(chess[i][j] == 1){
                return false;
            }
        }
        for(int i = row -1, j = col +1; i>=0 && j< chess.length; i--,j++){
            if(chess[i][j] == 1){
                return false;
            }
        }
        return true;
    }

    public static void printKnightTour(int[][] chess, int r, int c, int move){
        if(r<0 || c<0 || r>=chess.length || c>=chess.length || chess[r][c]>0){
            return;
        }else if(move == chess.length*chess.length){
            chess[r][c] = move;
            display(chess);
            chess[r][c] = 0;
            return;

        }

        chess[r][c] = move;
        printKnightTour(chess, r - 1, c + 2, move + 1);
        printKnightTour(chess, r - 2, c + 1, move + 1);
        printKnightTour(chess, r + 1, c + 2, move + 1);
        printKnightTour(chess, r + 2, c + 1, move + 1);
        printKnightTour(chess, r + 2, c - 1, move + 1);
        printKnightTour(chess, r + 1, c - 2, move + 1);
        printKnightTour(chess, r - 2, c - 1, move + 1);
        printKnightTour(chess, r - 1, c - 2, move + 1);
        chess[r][c] = 0;

    }

    public static void display(int[][] chess){
        // System.out.println("sahil");
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        // int n = scn.nextInt();
        // int m = scn.nextInt();
        // int[][]arr = new int[n][m];
        // for(int i = 0; i< arr.length; i++){
        //     for(int j = 0; j< arr[0].length; j++){
        //         arr[i][j] = scn.nextInt();
        //     }
        // }
        // boolean[][] visited = new boolean[n][m];

        // floodfill(arr, 0, 0, "", visited);

        // int n = scn.nextInt();
        // int[]arr = new int[n];
        // for(int i = 0; i < arr.length; i++){
        //     arr[i] = scn.nextInt();
        // }
        // int tar = scn.nextInt();
        // printTargetSumSubset(arr, 0, "", 0, tar);

        // int n = scn.nextInt();
        // int[][] chess = new int[n][n];
        // printNQueen(chess, "", 0);

        int n = scn.nextInt();
        int r = scn.nextInt();
        int c = scn.nextInt();
        int[][]chess = new int[n][n];
        printKnightTour(chess, r, c, 1);

    }
    
}
