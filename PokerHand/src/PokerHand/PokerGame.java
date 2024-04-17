package PokerHand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class PokerGame {

	public static void main(String[] args) {
		String fileName = "poker.txt";
        int player1Wins = 0;

        try {
            player1Wins = (int) Files.lines(Paths.get(fileName))
                    .map(line -> line.split(" "))
                    .filter(hands -> HandComparator.player1Wins(Arrays.copyOfRange(hands, 0, 5), Arrays.copyOfRange(hands, 5, 10)))
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Número de vitórias do Jogador 1: " + player1Wins);
	}

}
