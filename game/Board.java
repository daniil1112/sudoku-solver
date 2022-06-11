package game;

public class Board {
    private Square[][] squares;

    public Board() {
        squares = new Square[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j] = new Square();
            }
        }
    }

    public void initGame(int[][] cells) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                squares[i / 3][j / 3].setCell(i % 3, j % 3, cells[i][j]);
            }
        }
    }

    public Square getSquare(int x, int y) {
        return squares[x / 3][y / 3];
    }

    public void setCell(int x, int y, int val) {
        squares[x / 3][y / 3].setCell(x % 3, y % 3, val);
    }

    public void clearCell(int x, int y) {
        setCell(x, y, 0);
    }

    public int getCell(int x, int y) {
        Square sq = getSquare(x, y);
        x %= 3;
        y %= 3;
        return sq.getCell(x, y);
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(getCell(i, j) + " ");
            }
            System.out.println("");
        }
    }

    private boolean checkVertical(int cord, int val) {
        int lvl = cord / 3;
        int cord_check = cord % 3;
        for (int i = 0; i < 3; i++) {
            Square sq = squares[i][lvl];
            for (int j = 0; j < 3; j++) {
                if (sq.getCell(j, cord_check) == val) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkHorisontal(int cord, int val) {
        int lvl = cord / 3;
        int cord_check = cord % 3;
        for (int i = 0; i < 3; i++) {
            Square sq = squares[lvl][i];
            for (int j = 0; j < 3; j++) {
                if (sq.getCell(cord_check, j) == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkValidPut(int x, int y, int val) {
        Square sq = getSquare(x, y);
        return sq.checkCan(val) && checkVertical(y, val) && checkHorisontal(x, val);
    }

    private int countVariants(int x, int y) {
        if (getCell(x, y) != 0) {
            return 0;
        }
        int cnt = 0;
        for (int i = 1; i < 10; i++) {
            cnt += checkValidPut(x, y, i) ? 1 : 0;
        }
        return cnt;
    }

    public int countEmpty() {
        int cnt = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cnt += getCell(i, j) == 0 ? 1 : 0;
            }
        }
        return cnt;
    }
    public Pair getCellMinAlter() {
        int min = 10;
        Pair save = new Pair(-1, -1);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int t = countVariants(i, j);
                if (t == 1) return new Pair(i, j);
                if (t < min && t > 0) {
                    min = t;
                    save.setFirst(i);
                    save.setSecond(j);
                }
            }
        }
        return save;
    }

    private boolean checkValidHorisontal(int i) {
        boolean[] used = new boolean[10];
        for (int j = 0; j < 9; j++) {
            int val = getCell(i, j);
            if (val > 0 && used[val]) {
                return false;
            }
            used[val] = true;
        }
        return true;
    }

    private boolean checkValidVertical(int j) {
        boolean[] used = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int val = getCell(i, j);
            if (val > 0 && used[val]) {
                return false;
            }
            used[val] = true;
        }
        return true;
    }


    public boolean checkBoard() {
        //check squares
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!squares[i][j].checkValid()) return false;
            }
        }
        //check horizontal
        for (int i = 0; i < 9; i++) {
            if (!checkValidHorisontal(i)) return false;
        }
        // check vertical
        for (int j = 0; j < 9; j++) {
            if (!checkValidVertical(j)) return false;
        }
        return true;
    }
}
