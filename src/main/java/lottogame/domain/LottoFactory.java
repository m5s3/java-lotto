package lottogame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    private static final List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());

    public static List<Lottos> createLottoses(Money purchaseAmount, Money price) {
        Number count = Number.from(purchaseAmount.divide(price).convertToInt());
        List<Lottos> lottoses = new ArrayList<>();
        while (count.isPositive()) {
            lottoses.add(createRandomLotto());
            count = count.decrease();
        }
        return lottoses;
    }

    public static Lottos createLotto(List<Integer> numbers) {
        return new Lottos(numbers);
    }

    private static Lottos createRandomLotto() {
        Collections.shuffle(numbers);
        return createLotto(numbers
                .subList(0, 6)
                .stream()
                .collect(Collectors.toUnmodifiableList()));
    }
}
