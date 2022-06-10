package pa.alpha.currencyemmotion;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pa.alpha.currencyemmotion.controller.GiphyFeignClient;
import pa.alpha.currencyemmotion.dto.GiphyDataDto;
import pa.alpha.currencyemmotion.service.CurrencyService;
import pa.alpha.currencyemmotion.service.EmotionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
class EmotionServiseTest {

    @MockBean
    public GiphyFeignClient giphyFeignClient;

    @Autowired
    public CurrencyOptions currencyOptions;
    @Autowired
    public EmotionService emotionService;

    @Test
    void getEmotionUrlTest(){

        GiphyDataDto brokeEmotion = new GiphyDataDto();
        brokeEmotion.setData(brokeEmotion.new DataSection("broke"));

        GiphyDataDto richEmotion = new GiphyDataDto();
        richEmotion.setData(richEmotion.new DataSection("rich"));

        Mockito.when(giphyFeignClient.getRisingGif(
                currencyOptions.getGiphyApiKey()
        )).thenReturn(richEmotion);

        Mockito.when(giphyFeignClient.getFallingGif(
                currencyOptions.getGiphyApiKey()
        )).thenReturn(brokeEmotion);

        assertEquals("rich", emotionService.getEmotionUrl(CurrencyService.CourseDirection.RISE));
        assertEquals("broke", emotionService.getEmotionUrl(CurrencyService.CourseDirection.FALL));
        assertNotEquals("rich", emotionService.getEmotionUrl(CurrencyService.CourseDirection.FALL));
        assertNotEquals("broke", emotionService.getEmotionUrl(CurrencyService.CourseDirection.RISE));

    }

}
