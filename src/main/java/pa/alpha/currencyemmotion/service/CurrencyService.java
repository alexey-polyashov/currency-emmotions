package pa.alpha.currencyemmotion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pa.alpha.currencyemmotion.CurrencyOptions;
import pa.alpha.currencyemmotion.controller.CurrencyFeignClient;
import pa.alpha.currencyemmotion.dto.CourseInfoDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    public enum CourseDirection{
        RISE, FALL;
    }

    private final CurrencyFeignClient currencyFeignClient;
    private final CurrencyOptions currencyOptions;

    public CourseDirection getCourseDirection(String currencyCode){

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String laterDatePresent = dateFormatter.format(LocalDateTime.now().minusDays(1));

        CourseInfoDto courseInfo = currencyFeignClient.getLastCourse(currencyOptions.getCurrencyApiKey(), currencyOptions.getBaseCurrency());
        CourseInfoDto laterCourseInfo = currencyFeignClient.getHistoryCourse(currencyOptions.getCurrencyApiKey(), laterDatePresent, currencyOptions.getBaseCurrency());

        String cRate = courseInfo.getRates().get(currencyCode);
        String lRate = laterCourseInfo.getRates().get(currencyCode);
        String targetUrl = "";
        CourseDirection courseDirection = CourseDirection.RISE;
        if(cRate!=null && lRate!=null) {
            Double currentRate = Double.valueOf(cRate);
            Double laterRate = Double.valueOf(lRate);
            if(currentRate>laterRate){
                courseDirection = CourseDirection.FALL;
            }

        }

        return courseDirection;
    }

}
