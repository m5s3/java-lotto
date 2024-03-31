package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    @Test
    void 구입금액에_따른_로또갯수를_생성() {
        Money money = Money.from(10_000);
        Money price = Money.from(1_000);
        int expected = 10;

        assertThat(LottoFactory.createMultipleLottos(Number.from(money.divide(price).convertToInt())).size()).isEqualTo(expected);
    }

    @Test
    void 구입금액에_따른_로또갯수를_계산하기() {
        Money money = Money.from(10_000);
        Money price = Money.from(1_000);
        Number expected = Number.from(10);
        Number countOfLottos = LottoFactory.calculateCountOfLottos(money, price);

        assertThat(countOfLottos).isEqualTo(expected);
    }

    @Test
    void 수동입력_로또_생성() {
        List<List<Integer>> numbers = List.of(
                List.of(1,2,3,4,5,6),
                List.of(1,2,3,4,5,7),
                List.of(1,2,3,4,5,9)
        );
        int expected = 3;

        assertThat(LottoFactory.createMultipleLottos(numbers).size()).isEqualTo(expected);
    }
}