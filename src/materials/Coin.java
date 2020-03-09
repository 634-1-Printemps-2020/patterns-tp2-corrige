package materials;

public class Coin {

  private CoinState coinState;

  private static Coin instance;

  private Coin() {
    throwCoin();
  }

  /**
   * Change l'état de la pièce.
   * 50% de probabilité d'obtenir HEADS, 50% de probabilité d'obtenir TAILS
   */
  public void throwCoin() {
    this.coinState = (Math.round(Math.random()) == 0) ? CoinState.HEADS : CoinState.TAILS;
  }

  public CoinState getState() {
    return coinState;
  }

  public static Coin getInstance() {
    if (instance == null)
      instance = new Coin();
    return instance;
  }

}
