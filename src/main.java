import java.util.HashMap;
import java.util.HashSet;

public class main {
    static Square1 adjadj = new Square1();
    static int[] solvedState;
    static String[] moves = new String[121];


    public static void main(String[] args) {
        int moveIndex = 0;
        for (int i = -5; i < 6; i++) {
            for (int j = -5; j < 6; j++) {
                moves[moveIndex] = i + "," + j + "/";
                moveIndex++;
            }
        }
        solvedState = adjadj.getState();
        try {
            adjadj.doMoves("1,0/2,-1/1,1/-3,0/-1,0");
        } catch (Exception e) {

        }
        first(adjadj, 4, "");
        System.out.println("no");
    }

    public static void print(String thing){
        System.out.println();
    }


    public static void first(Square1 sq1, int slicesRemain, String solution) {
        if (slicesRemain == 0) {
            Square1 abfs = new Square1();
            try {
                abfs.doMoves(solution);
                checkABFs(abfs, 0, 0, solution);
            } catch (Exception e) {
            }
            return;
        }
        for (int i = 0; i < moves.length; i++) {
            Square1 temp = new Square1();
            String tempSol = solution + moves[i];
            try {
                temp.doMoves(tempSol);
                r(temp, slicesRemain - 1, tempSol);
                System.out.println(i + "/" + 120);
            } catch (Exception e) {
                System.out.println(solution);

            }
        }
    }

    public static void r(Square1 sq1, int slicesRemain, String solution) {
        if (slicesRemain == 0) {
            Square1 abfs = new Square1();
            try {
                abfs.doMoves(solution);
                checkABFs(abfs, 0, 0, solution);
            } catch (Exception e) {
            }
            return;
        }
        for (int i = 0; i < moves.length; i++) {
            Square1 temp = new Square1();
            String tempSol = solution + moves[i];
            try {
                temp.doMoves(tempSol);
                r(temp, slicesRemain - 1, tempSol);
            } catch (Exception e) {

            }
        }
    }

    public static boolean equate(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void checkABFs(Square1 sq1, int top, int bottom, String solution) {
        Square1 move = new Square1();
        try {
            move.doMoves(solution + top + "," + bottom);
        } catch (Exception e) {
        }

        if (equate(solvedState, move.getState())) {
            //solution += top + "," + bottom;
            System.out.println(solution);
            return;
        }
        if (top == 13) {
            return;
        }
        while (bottom < 13) {
            bottom++;
            checkABFs(sq1, top, bottom, solution);
            return;
        }
        checkABFs(sq1, top + 1, 0, solution);

    }
}
