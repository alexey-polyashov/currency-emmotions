package pa.alpha.currencyemmotion.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pa.alpha.currencyemmotion.dto.CourseInfoDto;
import pa.alpha.currencyemmotion.dto.GiphyDataDto;

@FeignClient(name="giphy-service", url="https://api.giphy.com/v1/gifs/")
public interface GiphyFeignClient {

    @GetMapping("/random?api_key={apiKey}&tag==broke&rating=pg-13")
    public GiphyDataDto getFallingGif(@PathVariable("apiKey") String apiKey);

    @GetMapping("/random?api_key={apiKey}&tag==rich&rating=pg-13")
    public GiphyDataDto getRisingGif(@PathVariable("apiKey") String apiKey);

}
