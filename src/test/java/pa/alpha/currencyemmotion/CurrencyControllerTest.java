package pa.alpha.currencyemmotion;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;
import pa.alpha.currencyemmotion.controller.CurrencyController;
import pa.alpha.currencyemmotion.controller.CurrencyFeignClient;
import pa.alpha.currencyemmotion.controller.GiphyFeignClient;
import pa.alpha.currencyemmotion.dto.CourseInfoDto;
import pa.alpha.currencyemmotion.dto.GiphyDataDto;
import pa.alpha.currencyemmotion.service.CurrencyService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyControllerTest {

    @MockBean
    public CurrencyFeignClient currencyFeignClient;
    @MockBean
    public GiphyFeignClient giphyFeignClient;


    @Autowired
    public CurrencyOptions currencyOptions;
    @Autowired
    public CurrencyController currencyController;

    @Test
    void getDirectionTest(){

        /*  Prepare  @MockBean GiphyFeignClient  */
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

        /*  Prepare  @MockBean CurrencyFeignClient  */
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String laterDatePresent = dateFormatter.format(LocalDateTime.now().minusDays(1));

        CourseInfoDto lessCourse = new CourseInfoDto();
        lessCourse.getRates().put("UAN", "15.5");
        lessCourse.getRates().put("RUB", "10.5");
        lessCourse.getRates().put("GBP", "20.5");

        CourseInfoDto graterCourse = new CourseInfoDto();
        graterCourse.getRates().put("UAN", "1.5");
        graterCourse.getRates().put("RUB", "12.5");
        graterCourse.getRates().put("GBP", "1.5");

        Mockito.when(currencyFeignClient.getHistoryCourse(
                currencyOptions.getCurrencyApiKey(),
                laterDatePresent,
                currencyOptions.getBaseCurrency()
        )).thenReturn(graterCourse);

        Mockito.when(currencyFeignClient.getLastCourse(
                currencyOptions.getCurrencyApiKey(),
                currencyOptions.getBaseCurrency()
        )).thenReturn(lessCourse);

        assertEquals(CurrencyService.CourseDirection.RISE, currencyController.getDirection("RUB"));
        ModelAndView imgRich = currencyController.getEmotion("RUB");

        Mockito.when(currencyFeignClient.getHistoryCourse(
                currencyOptions.getCurrencyApiKey(),
                laterDatePresent,
                currencyOptions.getBaseCurrency()
        )).thenReturn(lessCourse);

        Mockito.when(currencyFeignClient.getLastCourse(
                currencyOptions.getCurrencyApiKey(),
                currencyOptions.getBaseCurrency()
        )).thenReturn(graterCourse);

        assertEquals(CurrencyService.CourseDirection.FALL, currencyController.getDirection("RUB"));
        ModelAndView imgBroke = currencyController.getEmotion("RUB");

        assertEquals("redirect:rich", imgRich.getViewName());
        assertEquals("redirect:broke", imgBroke.getViewName());

    }

}

