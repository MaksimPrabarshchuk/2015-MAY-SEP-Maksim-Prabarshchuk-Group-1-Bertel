package game.tic.tac.toe.ai.heuristic.tictactoe;

import game.tic.tac.toe.DiscreteGameState;
import game.tic.tac.toe.tictactoe.TicTacToeGameState;
import game.tic.tac.toe.tictactoe.TicTacToeGameState.Player;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeEvaluatorTest {

  private TicTacToeEvaluator evaluator;
  @Mock
  private TicTacToeGameState game;
  @Mock
  private List<DiscreteGameState> availableStates;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setup() {
    evaluator = new TicTacToeEvaluator(Player.X);
  }

  @Test
  public void constructorNullPlayer() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("player cannot be null");
    new TicTacToeEvaluator(null);
  }

  @Test
  public void evaluateWin() {
    when(game.hasWin(Player.X)).thenReturn(true);
    assertThat(evaluator.evaluate(game)).isEqualTo(1);
  }

  @Test
  public void evaluateWinConsidersAvailableMoves() {
    when(game.hasWin(Player.X)).thenReturn(true);
    when(game.availableStates()).thenReturn(availableStates);
    when(availableStates.size()).thenReturn(5);
    assertThat(evaluator.evaluate(game)).isEqualTo(6);
  }

  @Test
  public void evaluateLoss() {
    when(game.hasWin(Player.O)).thenReturn(true);
    assertThat(evaluator.evaluate(game)).isEqualTo(-1);
  }

  @Test
  public void evaluateDraw() {
    assertThat(evaluator.evaluate(game)).isEqualTo(0);
  }

  @Test
  public void evaluateNull() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("cannot evaluate null game");
    evaluator.evaluate(null);
  }
}
