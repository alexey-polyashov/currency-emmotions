package pa.alpha.currencyemmotion.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CourseInfoDto {

    private Long timestamp;
    private String base;
    Map<String, String> rates;

    public CourseInfoDto() {
        this.rates = new HashMap<>();
    }
}
