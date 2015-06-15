package game.tic.tac.toe.tictactoe;

import game.tic.tac.toe.ai.GameIntelligenceAgent;
import game.tic.tac.toe.ai.MinimaxAgent;
import game.tic.tac.toe.ai.heuristic.tictactoe.TicTacToeEvaluator;

import java.util.Scanner;

public class TicTacToeMain {

    public static void main(String[] args) {
        TicTacToeEvaluator evaluator = new TicTacToeEvaluator(TicTacToeGameState.Player.O);
        GameIntelligenceAgent<TicTacToeGameState> agent =
                new MinimaxAgent<TicTacToeGameState>(evaluator);
        Scanner scanner = new Scanner(System.in);
        TicTacToeGameRunner game = new TicTacToeGameRunner(agent, scanner, System.out);
        game.run();
    }

}
