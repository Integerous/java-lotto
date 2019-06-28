package step4.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoSeller {
    static final int PRICE_OF_A_LOTTO_TICKET = 1000;
    static final String ALERT_MISSING_MONEY = "돈을 넣어주세요. 로또복권은 한 장당 1000원 입니다.";

    public static int countAutoTickets(int inputMoney, int numberOfManualTickets) {
        validationInputMoney(inputMoney);
        return (inputMoney / PRICE_OF_A_LOTTO_TICKET) - numberOfManualTickets;
    }

    public static LottoTickets issueLottoTicket(int numberOfTicketsToBuy) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < numberOfTicketsToBuy; i++) {
            lottoTickets.add(LottoTicketGenerator.issue());
        }
        return LottoTickets.from(lottoTickets);
    }

    public static LuckyTicket getLuckyNumber(List<Integer> inputLuckyNumber, int bonusNumber) {
        List<LottoNumber> luckyTicket = inputLuckyNumber.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
        return LuckyTicket.of(luckyTicket, LottoNumber.from(bonusNumber));
    }

    private static void validationInputMoney(int inputMoney) {
        if (inputMoney < PRICE_OF_A_LOTTO_TICKET) {
            throw new IllegalArgumentException(ALERT_MISSING_MONEY);
        }
    }
}
