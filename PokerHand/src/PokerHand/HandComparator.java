package PokerHand;

public class HandComparator {
	public static boolean player1Wins(String[] player1Hand, String[] player2Hand) {
        Hand hand1 = new Hand(player1Hand);
        Hand hand2 = new Hand(player2Hand);

        return hand1.compareTo(hand2) > 0;
    }
}
