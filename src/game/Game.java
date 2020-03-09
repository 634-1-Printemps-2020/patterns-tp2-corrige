package game;

import materials.Coin;
import materials.CoinState;
import player.Player;
import utils.Statistics;

import java.util.*;

public class Game {

  private Rules rules;
  private Coin coin;
  private Map<Player, List<CoinState>> history;

  public Game() {
    rules = Rules.getInstance();
    coin = Coin.getInstance();
    history = new HashMap<>();
  }

  /**
   * Ajouter un nouveau joueur au jeu
   * @param player le nouveau joueur
   */
  public void addPlayer(Player player) {
    history.put(player, null);
  }

  /**
   * Faire joueur tous les joueurs et stocker chaque partie dans history
   */
  public void play() {
    for (Player player : history.keySet()) {

      List<CoinState> states = new LinkedList<>();

      while (!rules.checkWin(states)) {
        player.play(coin);
        states.add(coin.getState());
      }
      history.put(player, states);
    }
  }

  /**
   * Calculer des statistiques de la partie précédente
   * @return Statistics
   */
  public Statistics getStatistics() {

    int fewerMovesToWin = Integer.MAX_VALUE;
    int mostMovesToWin = Integer.MIN_VALUE;
    int totalMovesToWin = 0;

    for (Player player : history.keySet()) {
      List<CoinState> states = history.get(player);

      if (states != null) {

        if (states.size() < fewerMovesToWin) {
          fewerMovesToWin = states.size();
        }

        if (states.size() > mostMovesToWin) {
          mostMovesToWin = states.size();
        }

        totalMovesToWin += states.size();

      }
    }

    Statistics statistics;

    if (history.size() == 0) {
      statistics = new Statistics(0, 0, 0, 0);
    } else {
      statistics = new Statistics(totalMovesToWin / history.size(), fewerMovesToWin, mostMovesToWin, totalMovesToWin);
    }

    return statistics;
  }

  /**
   * Obtenir l'historique de tous les joueurs de la partie précédente
   * @return Map contenant chaque joueur et la liste des ses lancers
   */
  public Map<Player, List<CoinState>> getHistory() {
    return history;
  }

  /**
   * Obtenir l'historique d'un joueur spécifique
   * @param player instance du joueur pour lequel on veut avoir l'historique
   * @return la liste des lancers d'un joueur
   */
  public List<CoinState> getSpecificHistory(Player player) {
    return history.get(player);
  }

}
