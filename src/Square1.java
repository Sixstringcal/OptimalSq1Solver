import java.util.LinkedList;


public class Square1 {
    private int[] topColor = new int[]{0, 0, 0};
    private int[] bottomColor = new int[]{255, 255, 255};
    private int[] backColor = new int[]{255, 165, 0};
    private int[] frontColor = new int[]{255, 0, 0};
    private int[] rightColor = new int[]{0, 255, 0};
    private int[] leftColor = new int[]{0, 0, 255};

    private int[] state;

    public Square1() {
        state = new int[]{
                1, 1, 2, 3, 3, 4, 5, 5, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 13, 13, 14, 15, 15, 16
        };
    }

    public void doMoves(String moves) throws Exception {
        if (moves.equals("")) {
            return;
        }
        boolean lastIsSlash = false;
        if (moves.charAt(moves.length() - 1) == '/') {
            lastIsSlash = true;
        }
        String[] udTogether = moves.split("/");
        for (int i = 0; i < udTogether.length; i++) {
            if (!udTogether.equals("")) {
                String[] tempArray = udTogether[i].split(",");
                int[] move = new int[2];
                move[0] = Integer.parseInt(tempArray[0]);
                move[1] = Integer.parseInt(tempArray[1]);
                ud(move);
            }
            if (i != udTogether.length - 1 || (lastIsSlash)) {
                if(state[4] == state[5] || state[10] == state[11] || state[17] == state[18] || state[23] == state[12]){
                    throw new Exception();
                }
                doSlash();
            }

        }

    }

    public void printState() {
        for (int i = 0; i < state.length; i++) {
            System.out.print(state[i] + " ");
        }
        System.out.println();
    }

    public void doSlash() {
        int temp;
        for (int i = 5; i < 11; i++) {
            temp = state[i];
            state[i] = state[i + 13];
            state[i + 13] = temp;
        }
    }

    public int[] getState() {
        return state;
    }

    public int[][] getOBLState() {
        LinkedList<Integer> lasts = new LinkedList<>();
        int[][] obl = new int[18][3];
        int todo = 0;
        for (int i = 0; i < 24; i++) {
            if (!lasts.contains(state[i])) {
                if (state[i] < 9) {
                    obl[todo] = topColor;
                } else {
                    obl[todo] = bottomColor;
                }
                todo++;
            }
            lasts.add(state[i]);
        }
        if (state[11] % 2 == 0) {
            obl[16] = new int[]{0, 0, 0};
        } else if (state[1] == 0) {
            obl[16] = new int[]{1, 1, 1};
        } else {
            obl[16] = new int[]{-1, -1, -1};
        }

        if (state[23] % 2 == 0) {
            obl[17] = new int[]{0, 0, 0};
        } else if (state[13] % 2 == 0) {
            obl[17] = new int[]{1, 1, 1};
        } else {
            obl[17] = new int[]{-1, -1, -1};
        }
        return obl;
    }

    public void ud(int[] move) {
        if (move[0] != 0) { //Do U move.
            if (move[0] < 0) { //Converts negative moves into positive moves.
                move[0] += 12;
            }
            for (int i = 0; i < move[0]; i++) {
                int temp = state[11];
                state[11] = state[0];
                state[0] = state[1];
                state[1] = state[2];
                state[2] = state[3];
                state[3] = state[4];
                state[4] = state[5];
                state[5] = state[6];
                state[6] = state[7];
                state[7] = state[8];
                state[8] = state[9];
                state[9] = state[10];
                state[10] = temp;

            }
        }
        if (move[1] != 0) { //Do D move.
            if (move[1] < 0) { //Converts negative moves into positive moves.
                move[1] += 12;
            }
            for (int i = 0; i < move[1]; i++) {
                int temp = state[23];
                state[23] = state[12];
                state[12] = state[13];
                state[13] = state[14];
                state[14] = state[15];
                state[15] = state[16];
                state[16] = state[17];
                state[17] = state[18];
                state[18] = state[19];
                state[19] = state[20];
                state[20] = state[21];
                state[21] = state[22];
                state[22] = temp;
            }

        }
    }

    public String reverseScramble(String scramble) {
        if (scramble.startsWith("/")) {
            scramble = "0,0" + scramble;
        }
        if (scramble.endsWith("/")) {
            scramble += "0,0";
        }
        String reversedScramble = "";
        boolean endsWithSlash = false;
        if (scramble.charAt(0) == '/') {
            endsWithSlash = true;
        }
        if (scramble.charAt(scramble.length() - 1) == '/') {
            reversedScramble = "/";
        }

        String[] splitScramble = scramble.split("/");

        for (int i = splitScramble.length - 1; i > -1; i--) {

            int x = -1 * Integer.parseInt(splitScramble[i].split(",")[0]);
            int y = -1 * Integer.parseInt(splitScramble[i].split(",")[1]);
            reversedScramble += x + "," + y;
            if (i > 0 || endsWithSlash) {
                reversedScramble += "/";
            }
        }

        return reversedScramble;
    }

    /**
     * Returns the relevant information to the non U/D pieces (also ignores the slice orientation)
     *
     * @return
     */
    public int[][] getPBLState() {
        int[][] returnState = new int[24][3];
        for (int i = 0; i < 24; i++) {
            switch (state[i]) {
                case 1:
                    returnState[i] = backColor;
                    i++;
                    returnState[i] = leftColor;
                    break;
                case 2:
                case 10:
                    returnState[i] = leftColor;
                    break;
                case 3:
                    returnState[i] = leftColor;
                    i++;
                    returnState[i] = frontColor;
                    break;
                case 4:
                case 16:
                    returnState[i] = frontColor;
                    break;
                case 5:
                    returnState[i] = frontColor;
                    i++;
                    returnState[i] = rightColor;
                    break;
                case 6:
                case 14:
                    returnState[i] = rightColor;
                    break;
                case 7:
                    returnState[i] = rightColor;
                    i++;
                    returnState[i] = backColor;
                    break;
                case 8:
                case 12:
                    returnState[i] = backColor;
                    break;
                case 9:
                    returnState[i] = frontColor;
                    i++;
                    returnState[i] = leftColor;
                    break;
                case 11:
                    returnState[i] = leftColor;
                    i++;
                    returnState[i] = backColor;
                    break;
                case 13:
                    returnState[i] = backColor;
                    i++;
                    returnState[i] = rightColor;
                    break;
                case 15:
                    returnState[i] = rightColor;
                    i++;
                    returnState[i] = frontColor;
                    break;
            }
        }
        return returnState;
    }

}
