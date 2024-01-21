import java.util.Objects;
class Card implements Comparable<Card> {
    private long card_ID;
    private String card_name;
    private Rank card_rank;
    private long price;
    public Card(long card_ID, String card_name, Rank card_rank){
        this.card_ID=card_ID;
        this.card_name=card_name;
        this.card_rank=card_rank;
        price=0;
    }



    @Override
    public String toString() {
        return "Card{" +
                "id=" + card_ID +
                ", name='" + card_name + '\'' +
                ", rank=" + card_rank +
                ", price=" + price +
                '}';
    }
    public int hashCode() {
        return Objects.hash(card_ID, card_name, card_rank);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Card)) return false;
        Card card = (Card) obj;
        return card_ID == card.card_ID &&
                Objects.equals(card_name, card.card_name) &&
                card_rank == card.card_rank;
    }
    @Override
    public int compareTo(Card o) {
        if (this.card_rank != o.card_rank) {
            return this.card_rank.compareTo(o.card_rank);
        } else if (!this.card_name.equals(o.card_name)) {
            return this.card_name.compareTo(o.card_name);
        } else if (this.card_ID != o.card_ID) {
            return Long.compare(this.card_ID, o.card_ID);
        } else {
            return 0;
        }
    }

    public long getPrice() {
        return price;
    }

    public long getId() {
        return card_ID;
    }
}

