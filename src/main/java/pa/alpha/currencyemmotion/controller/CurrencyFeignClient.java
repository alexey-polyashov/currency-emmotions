package pa.alpha.currencyemmotion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pa.alpha.currencyemmotion.CurrencyOptions;
import pa.alpha.currencyemmotion.dto.CourseInfoDto;

@FeignClient(name="forex-service", url="https://openexchangerates.org/api/")
public interface  CurrencyFeignClient {

    @GetMapping("/latest.json?app_id={apiKey}&base={baseCurrency}")
    public CourseInfoDto getLastCourse(@PathVariable("apiKey") String apiKey, @PathVariable("baseCurrency") String baseCurrency);

    @GetMapping("/historical/{date}.json/?app_id={apiKey}&base={baseCurrency}")
    public CourseInfoDto getHistoryCourse(@PathVariable("apiKey") String apiKey, @PathVariable("date") String date, @PathVariable("baseCurrency") String baseCurrency);

}
