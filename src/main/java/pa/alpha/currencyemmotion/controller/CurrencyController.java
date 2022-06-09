package pa.alpha.currencyemmotion.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pa.alpha.currencyemmotion.CurrencyOptions;
import pa.alpha.currencyemmotion.dto.CourseInfoDto;
import pa.alpha.currencyemmotion.dto.GiphyDataDto;
import pa.alpha.currencyemmotion.service.CurrencyService;
import pa.alpha.currencyemmotion.service.EmotionService;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;
    private final EmotionService emotionService;

    @GetMapping("/{currencyCode}")
    public ModelAndView courseRequest(@PathVariable("currencyCode") String currencyCode){

        CurrencyService.CourseDirection direction = currencyService.getCourseDirection(currencyCode);
        String targetUrl = emotionService.getEmotionUrl(direction);

        return new ModelAndView("redirect:" + targetUrl);

    }

}
