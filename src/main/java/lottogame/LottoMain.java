package lottogame;

import java.util.List;
import lottogame.domain.Lottos;
import lottogame.domain.LottoGame;
import lottogame.domain.Money;
import lottogame.domain.Number;
import lottogame.domain.WinningLottos;
import lottogame.view.InputView;
import lottogame.view.ResultView;

public class LottoMain {

    public static void main(String[] args) {
        Money money = InputView.requestMoney();
        LottoGame lottoGame = new LottoGame();
        List<Lottos> lottos = lottoGame.createLottos(money);
        ResultView.printCountOfPurchaseLottos(lottos);
        ResultView.printLottos(lottos);

        List<Integer> winningLottosNumbers = InputView.requestWinningLotto();
        Number bonusNumber = InputView.requestBonusNumber();
        WinningLottos winningLotto = lottoGame.createWinningLotto(winningLottosNumbers, bonusNumber);

        ResultView.printWinningResult(lottoGame.checkRanks(winningLotto, lottos));
        ResultView.printReturnOfRate(lottoGame.calculateReturnOfRate(winningLotto, lottos));
    }
}
