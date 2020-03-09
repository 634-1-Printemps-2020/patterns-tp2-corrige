package game;

import materials.CoinState;

import java.util.List;

public class Rules {

  private static Rules instance;

  private Rules() {
  }

  public static Rules getInstance() {
    if (instance == null)
      instance = new Rules();
    return instance;
  }

  /**
   * Cette méthode permet de déterminer si une suite d'états de pièce permet de gagner à une partie
   * @param states liste d'états pour un joueur
   * @return true si un joueur a gagné, false sinon
   */
  public boolean checkWin(List<CoinState> states) {

    int numberTails = 0;

    for (CoinState state : states) {
      if (state == CoinState.TAILS) {
        numberTails++;
        if (numberTails == 3) {
          return true;
        }
      } else {
        numberTails = 0;
      }
    }

    return false;
  }
}
