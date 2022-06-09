package pa.alpha.currencyemmotion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pa.alpha.currencyemmotion.CurrencyOptions;
import pa.alpha.currencyemmotion.controller.GiphyFeignClient;
import pa.alpha.currencyemmotion.dto.GiphyDataDto;

@Service
@RequiredArgsConstructor
public class EmotionService {

    private final GiphyFeignClient giphyFeignClient;
    private final CurrencyOptions currencyOptions;

    public String getEmotionUrl(CurrencyService.CourseDirection direction){

        String targetUrl = "";
        GiphyDataDto targetData;
        if(direction==CurrencyService.CourseDirection.FALL){
            targetData = giphyFeignClient.getFallingGif(currencyOptions.getGiphyApiKey());
        }else{
            targetData = giphyFeignClient.getRisingGif(currencyOptions.getGiphyApiKey());
        }
        if(targetData!=null){
            targetUrl = targetData.getData().getEmbed_url();
        }
        return targetUrl;

    }

}
