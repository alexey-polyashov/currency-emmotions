package pa.alpha.currencyemmotion;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CurrencyOptions {
    @Value("${forex.api-key}")
    private String currencyApiKey;
    @Value("${options.base-currency}")
    private String baseCurrency;
    @Value("${giphy.api-key}")
    private String giphyApiKey;
}
