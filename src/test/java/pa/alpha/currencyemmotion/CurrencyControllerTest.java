package pa.alpha.currencyemmotion;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pa.alpha.currencyemmotion.controller.CurrencyController;
import pa.alpha.currencyemmotion.controller.CurrencyFeignClient;
import pa.alpha.currencyemmotion.dto.CourseInfoDto;
import pa.alpha.currencyemmotion.service.CurrencyService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurrencyControllerTest {

    @MockBean
    public CurrencyFeignClient currencyFeignClient;

    @Autowired
    public CurrencyOptions currencyOptions;
    @Autowired
    public CurrencyController currencyController;

    @Test
    public void getDirectionTest(){
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
        ModelAndView img1 = currencyController.getEmotion("RUB");

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
        ModelAndView img2 = currencyController.getEmotion("RUB");

        assertNotEquals(img1.getViewName(), img2.getViewName());
        assertTrue(!img1.getViewName().isEmpty());
        assertTrue(!img2.getViewName().isEmpty());

    }

}

