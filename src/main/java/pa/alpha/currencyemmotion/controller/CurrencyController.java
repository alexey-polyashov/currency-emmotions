package pa.alpha.currencyemmotion.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pa.alpha.currencyemmotion.service.CurrencyService;
import pa.alpha.currencyemmotion.service.EmotionService;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;
    private final EmotionService emotionService;

    @GetMapping("/emotion/{currencyCode}")
    public ModelAndView getEmotion(@PathVariable("currencyCode") String currencyCode){

        CurrencyService.CourseDirection direction = currencyService.getCourseDirection(currencyCode);
        String targetUrl = emotionService.getEmotionUrl(direction);

        return new ModelAndView("redirect:" + targetUrl);

    }

    @GetMapping("/direction/{currencyCode}")
    public CurrencyService.CourseDirection getDirection(@PathVariable("currencyCode") String currencyCode){

        CurrencyService.CourseDirection direction = currencyService.getCourseDirection(currencyCode);

        return direction;

    }

}
