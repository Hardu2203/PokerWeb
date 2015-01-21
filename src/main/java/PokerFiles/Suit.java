package PokerFiles;

/**
 * Created by harduNel on 2015-01-12.
 */
public enum Suit {
    Spades("S"),
    Clubs ("C"),
    Diamonds ("D"),
    Hearts("H");

    private final String symbol;

    private Suit(String symbol) {
        this.symbol = symbol;
    }

    public static Suit fromSymbol(String sym)
    {
        for(Suit suit : values())
        {
            if(suit.symbol.equals(sym))
                return suit;
        }
        return null;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
