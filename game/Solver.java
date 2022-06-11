package game;


public class Solver {
    public Board solve(Board t, int empty) {
        if (empty == 0) {
            return t;
        }
        Pair cellForeach = t.getCellMinAlter();
        int x = cellForeach.getFirst();
        int y = cellForeach.getSecond();
        if (x >= 0 && y >= 0) {
            for (int val = 1; val < 10; val++) {

                if (t.checkValidPut(x, y, val)) {
                    t.setCell(x, y, val);
                    Board res = solve(t, empty - 1);
                    if (res != null) {
                        return res;
                    }
                    t.clearCell(x, y);
                }
            }
        }

        return null;

    }
}
