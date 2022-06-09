package pa.alpha.currencyemmotion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class CourseInfoDto {

    private Long timestamp;
    private String base;
    Map<String, String> rates;

}
