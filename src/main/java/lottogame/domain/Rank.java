package lottogame.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Rank {

    EMPTY(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private final int numOfWinnings;
    private final int prize;

    Rank(int numOfWinnings, int prize) {
        this.numOfWinnings = numOfWinnings;
        this.prize = prize;
    }

    public static List<Rank> getRanks() {
        return Stream.of(values())
                .filter(rank -> rank != EMPTY)
                .collect(Collectors.toList());
    }

    public static Rank findRank(int numOfWinnings, boolean matchBonus) {
        if (numOfWinnings == Rank.SECOND.numOfWinnings && matchBonus) {
            return SECOND;
        }
        return findRank(numOfWinnings);
    }

    public static Rank findRank(int numOfWinnings) {
        return Stream.of(values())
                .filter(match(numOfWinnings))
                .filter(Rank::removeBonusRank)
                .findAny()
                .orElse(EMPTY);
    }

    public static int getPrize(int numOfWinnings, boolean matchBonus) {
        Rank rank = findRank(numOfWinnings, matchBonus);
        return rank.prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getNumOfWinnings() {
        return numOfWinnings;
    }

    private static Predicate<Rank> match(int numOfWinnings) {
        return rank -> rank.numOfWinnings == numOfWinnings;
    }

    private static boolean removeBonusRank(Rank rank) {
        List<Rank> bonusRanks = List.of(SECOND);
        return !bonusRanks.contains(rank);
    }
}
