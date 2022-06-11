import game.Board;
import game.Solver;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
test cases:

1)
0 0 0 0 0 0 3 4 8
1 7 0 0 8 0 0 0 0
6 3 0 0 4 0 0 0 0
0 0 0 5 0 0 2 6 0
0 2 0 0 0 1 0 0 3
0 0 0 0 7 4 0 0 0
0 0 0 0 0 6 0 0 4
7 0 0 0 0 0 0 5 0
0 0 9 0 0 0 6 8 0

2)
0 0 0 5 0 2 7 0 0
0 0 0 0 0 9 0 0 8
0 0 5 6 0 0 0 1 4
9 0 6 0 0 4 5 0 0
0 0 0 0 0 0 0 4 0
2 4 0 9 0 0 6 0 0
3 0 0 2 0 8 0 0 7
0 0 7 0 5 0 0 0 0
0 2 8 0 0 0 4 0 0


 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a field of size 9*9, 0 = blank cell. All input cells must have values from 0 to 9");
        int[][] cels = new int[9][9];
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    cels[i][j] = scanner.nextInt();
                }
            }
            Board game = new Board();
            game.initGame(cels);

            if (!game.checkBoard()) {
                System.err.println("Game has errors in field");
            } else {
                Solver solver = new Solver();
                Board res = solver.solve(game, game.countEmpty());
                if (res == null || res.countEmpty() > 0 || !res.checkBoard()) {
                    System.err.println("Hasn't solve");
                } else {
                    System.out.println("Board's solve:");
                    game.printBoard();
                }
            }
        } catch (InputMismatchException exception) {
            System.err.print("You have entered a character that cannot be processed by the game.");
        } catch (Exception exception){
            System.err.print("Something went wrong, when running program");
        }
    }
}
