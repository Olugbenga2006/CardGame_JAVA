import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

class CardTest {

    public static void main(String[] args) {
        // create some cards for testing
        Card card1 = new Card(1, "Card A", Rank.COMMON);
        Card card2 = new Card(2, "Card B", Rank.UNCOMMON);
        Card card3 = new Card(3, "Card C", Rank.RARE);
        Card card4 = new Card(4, "Card A", Rank.COMMON);
        Card card5 = new Card(5, "Card B", Rank.UNCOMMON);

        // test HashSet for duplicate cards with different prices
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(card1);
        cardSet.add(card2);
        cardSet.add(card3);
        cardSet.add(card4);
        cardSet.add(card5);
        System.out.println("Card set size: " + cardSet.size()); // should be 3

        // test TreeSet for natural ordering by rank and name
        TreeSet<Card> cardTreeSet = new TreeSet<>();
        cardTreeSet.add(card1);
        cardTreeSet.add(card2);
        cardTreeSet.add(card3);
        cardTreeSet.add(card4);
        cardTreeSet.add(card5);
        for (Card card : cardTreeSet) {
            System.out.println(card.toString());
        }
    }
}

