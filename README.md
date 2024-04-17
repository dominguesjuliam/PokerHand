# PokerHand
Solution for Project Euler - #54 Poker Hands (https://projecteuler.net/problem=54)

The solution I developed for the poker game problem works as follows:
  - Reading the poker.txt file: The program reads each line of the file, which contains the hands of two players.
  - Determining each player's hands: Each line of the file is split into two hands, one for each player.
  - Hand classification: For each hand, poker hand ranking rules are applied to determine the hand type (e.g., pair, flush, straight) and compare the players' hands.
  - Hand comparison and win count: The hands are compared according to poker rules, and the win count for Player 1 is updated based on the result of each comparison.

What I like about my solution:
  - The use of Streams and Lambda Expressions makes the code more concise and readable.
  - Separating the hand comparison logic into a separate class makes the code more modular and easier to maintain.
  - The approach of unit testing ensures the robustness of the hand comparison logic.
What I don't like about my solution:
  - There was still an issue with the hand comparison logic, resulting in an incorrect outcome.
  - The complexity of the hand comparison logic could be reduced and simplified.
New technologies or approaches used:
  - Using Streams and Lambda Expressions for collection manipulation was a new and interesting approach for me.
