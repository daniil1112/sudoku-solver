package game;

public class Pair {
    private int first;
    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int val) {
        second = val;
    }

    public void setFirst(int val) {
        first = val;
    }

    @Override
    public String toString() {
        return "game.Pair{" + "first=" + first + ", second=" + second + '}';
    }
}
