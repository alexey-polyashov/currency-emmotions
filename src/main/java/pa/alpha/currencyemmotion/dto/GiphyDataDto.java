package pa.alpha.currencyemmotion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GiphyDataDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public class DataSection {
        private String embed_url;
    }

    DataSection data;

}
