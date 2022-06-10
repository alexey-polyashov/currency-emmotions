package pa.alpha.currencyemmotion;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pa.alpha.currencyemmotion.controller.CurrencyFeignClient;
import pa.alpha.currencyemmotion.dto.CourseInfoDto;
import pa.alpha.currencyemmotion.service.CurrencyService;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CurrencyServiseTest {

    @MockBean
    public CurrencyFeignClient currencyFeignClient;

    @Autowired
    public CurrencyOptions currencyOptions;
    @Autowired
    public CurrencyService currencyService;


    @Test
    void getCourseDirectionTest(){

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

        assertEquals(CurrencyService.CourseDirection.RISE, currencyService.getCourseDirection("RUB"));

        Mockito.when(currencyFeignClient.getHistoryCourse(
                currencyOptions.getCurrencyApiKey(),
                laterDatePresent,
                currencyOptions.getBaseCurrency()
        )).thenReturn(lessCourse);

        Mockito.when(currencyFeignClient.getLastCourse(
                currencyOptions.getCurrencyApiKey(),
                currencyOptions.getBaseCurrency()
        )).thenReturn(graterCourse);

        assertEquals(CurrencyService.CourseDirection.FALL, currencyService.getCourseDirection("RUB"));


    }

}
