package ht.demo.dto.coin.desk;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoinDeskDto {

    private CoinDeskTimeDto time;
    private String disclaimer;
    private String charName;
    private List<BpiDto> bpi;
    /**
     * {
     *         "time": {
     *             "updated": "Nov 1, 2021 11:47:56 UTC",
     *             "updatedISO": "2021-11-01T11:47:56+00:00",
     *             "updateduk": "Nov 1, 2021 at 11:47 BST"
     *         },
     *         "disclaimer": "Crypto prices are volatile, trade carefully.",
     *         "chartName": "Bitcoin",
     *         "bpi": {
     *             "USD": {
     *                 "code": "USD",
     *                 "symbol": "$",
     *                 "rate": "41,652.1492",
     *                 "description": "US Dollar",
     *                 "rate_float": 41652.1492
     *             },
     *             "GBP": {
     *                 "code": "GBP",
     *                 "symbol": "£",
     *                 "rate": "37,942.4688",
     *                 "description": "British Pound Sterling",
     *                 "rate_float": 37942.4688
     *             },
     *             "EUR": {
     *                 "code": "EUR",
     *                 "symbol": "€",
     *                 "rate": "39,817.1257",
     *                 "description": "Euro",
     *                 "rate_float": 39817.1257
     *             }
     *         }
     *     },
     */
}
