package lottogame.domain;

public class WinningLottos {

    private final Lottos lottos;
    private final LottoNumber bonusNumber;

    public WinningLottos(Numbers numbers, int bonusNumber) {
        this.lottos = LottoFactory.createLotto(numbers);
        this.bonusNumber =  new LottoNumber(Number.from(bonusNumber));
    }

    public Rank checkRank(Lottos lottos) {
        return Rank.find(match(lottos), matchBonusNumber(lottos));
    }

    public Number calculatePrize(Lottos lottos) {
        return Number.from(Rank.findPrize(match(lottos), matchBonusNumber(lottos)));
    }

    private boolean matchBonusNumber(Lottos lottos) {
        return lottos.match(bonusNumber);
    }

    public int size() {
        return lottos.size();
    }

    private int match(Lottos lottos) {
        return this.lottos.match(lottos);
    }
}
