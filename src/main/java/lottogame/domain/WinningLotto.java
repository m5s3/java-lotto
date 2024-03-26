package lottogame.domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, LottoNumber bonusNumber) {
        super(numbers);
        this.bonusNumber = bonusNumber;
    }

    public int getPrize(Lotto lotto) {
        return getRank(lotto).getPrize();
    }

    public Rank getRank(Lotto lotto) {
        return Rank.findRank(match(lotto), isBonusMatch(lotto));
    }

    private boolean isBonusMatch(Lotto lotto) {
        return lotto.match(bonusNumber);
    }
}
