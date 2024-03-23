package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import lottogame.domain.Lotto;
import lottogame.domain.LottoFactory;
import lottogame.domain.LottoGame;
import lottogame.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.numbergenerator.RandomNumberGenerator;

class LottoGameTest {
    LottoGame lottoGame;

    @BeforeEach
    void setUp() {
        lottoGame = new LottoGame(new LottoFactory(new RandomNumberGenerator()));
    }

    @Test
    void 수익률() {
        Lotto winnerLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Lotto> lottos = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto(List.of(1, 3, 5, 14, 22, 45)),
                new Lotto(List.of(2, 8, 9, 18, 19, 21)),
                new Lotto(List.of(23, 25, 33, 36, 39, 41)),
                new Lotto(List.of(5, 9, 38, 41, 43, 44)),
                new Lotto(List.of(13, 14, 18, 21, 23, 35)),
                new Lotto(List.of(17, 21, 29, 37, 42, 45)),
                new Lotto(List.of(3, 8, 27, 30, 35, 44))
        );
        double expected = 0.35;

        assertThat(lottoGame.calculateReturnOfRate(winnerLotto, lottos)).isEqualTo(expected);
    }

    @Test
    void 총_당첨_결과_확인() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 7, 8)),
                new Lotto(List.of(1, 2, 4, 7, 8, 9)),
                new Lotto(List.of(1, 2, 7, 8, 9, 10)));
        Lotto winnerLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Rank> ranks = lottoGame.checkRanks(winnerLotto, lottos);
        assertThat(ranks).contains(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.EMPTY);
    }
}