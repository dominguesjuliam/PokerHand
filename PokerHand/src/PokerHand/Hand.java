package PokerHand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Hand implements Comparable<Hand> {
    private final String[] cards;

    public Hand(String[] cards) {
        this.cards = cards;
        Arrays.sort(this.cards);
    }

    @Override
    public int compareTo(Hand other) {
        HandRank rank1 = determineRank();
        HandRank rank2 = other.determineRank();

        if (rank1.getValue() != rank2.getValue()) {
            return Integer.compare(rank1.getValue(), rank2.getValue());
        }

        for (int i = 4; i >= 0; i--) {
            if (getCardValue(cards[i]) != getCardValue(other.cards[i])) {
                return Integer.compare(getCardValue(cards[i]), getCardValue(other.cards[i]));
            }
        }

        return 0; 
    }

    private int getCardValue(String card) {
        char value = card.charAt(0);
        if (Character.isDigit(value)) {
            return value - '0';
        } else {
            switch (value) {
                case 'T':
                    return 10;
                case 'J':
                    return 11;
                case 'Q':
                    return 12;
                case 'K':
                    return 13;
                case 'A':
                    return 14;
                default:
                    return 0; 
            }
        }
    }

    private HandRank determineRank() {
        Map<Character, Integer> valueCounts = new HashMap<>();
        Map<Character, Integer> suitCounts = new HashMap<>();

        for (String card : cards) {
            char value = card.charAt(0);
            char suit = card.charAt(1);
            valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
            suitCounts.put(suit, suitCounts.getOrDefault(suit, 0) + 1);
        }

        if (isRoyalFlush(suitCounts) && isStraight(valueCounts)) {
            return HandRank.ROYAL_FLUSH;
        }
        if (isStraightFlush(valueCounts, suitCounts)) {
            return HandRank.STRAIGHT_FLUSH;
        }
        if (isFourOfAKind(valueCounts)) {
            return HandRank.FOUR_OF_A_KIND;
        }
        if (isFullHouse(valueCounts)) {
            return HandRank.FULL_HOUSE;
        }
        if (isFlush(suitCounts)) {
            return HandRank.FLUSH;
        }
        if (isStraight(valueCounts)) {
            return HandRank.STRAIGHT;
        }
        if (isThreeOfAKind(valueCounts)) {
            return HandRank.THREE_OF_A_KIND;
        }
        if (isTwoPairs(valueCounts)) {
            return HandRank.TWO_PAIRS;
        }
        if (isOnePair(valueCounts)) {
            return HandRank.ONE_PAIR;
        }

        return HandRank.HIGH_CARD;
    }

    private boolean isRoyalFlush(Map<Character, Integer> suitCounts) {
        return suitCounts.containsValue(5);
    }

    private boolean isStraightFlush(Map<Character, Integer> valueCounts, Map<Character, Integer> suitCounts) {
        return suitCounts.containsValue(5) && isStraight(valueCounts);
    }

    private boolean isFourOfAKind(Map<Character, Integer> valueCounts) {
        return valueCounts.containsValue(4);
    }

    private boolean isFullHouse(Map<Character, Integer> valueCounts) {
        return valueCounts.containsValue(3) && valueCounts.containsValue(2);
    }

    private boolean isFlush(Map<Character, Integer> suitCounts) {
        return suitCounts.containsValue(5);
    }

    private boolean isStraight(Map<Character, Integer> valueCounts) {
        int min = 14; 
        int max = 2;  

        for (int i = 2; i <= 14; i++) {
            if (valueCounts.containsKey((char) ('0' + i))) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
        }

        return max - min == 4;
    }

    private boolean isThreeOfAKind(Map<Character, Integer> valueCounts) {
        return valueCounts.containsValue(3);
    }

    private boolean isTwoPairs(Map<Character, Integer> valueCounts) {
        int count = 0;
        for (int value : valueCounts.values()) {
            if (value == 2) {
                count++;
            }
        }
        return count == 2;
    }

    private boolean isOnePair(Map<Character, Integer> valueCounts) {
        return valueCounts.containsValue(2);
    }
}
