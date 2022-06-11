package game;

public class Square {
    private int[][] fields = new int[3][3];

    public void setCell(int x, int y, int val) {
        fields[x][y] = val;
    }

    public int getCell(int x, int y) {
        return fields[x][y];
    }

    public boolean checkCan(int val) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (val == fields[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkValid() {
        boolean[] used = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fields[i][j] > 9 || fields[i][j] < 0) return false;
                if (fields[i][j] > 0) {
                    if (used[fields[i][j]]) {
                        return false;
                    }
                    used[fields[i][j]] = true;
                }
            }
        }
        return true;
    }
}
