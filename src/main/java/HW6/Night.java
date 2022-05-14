package HW6;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Night {

    @JsonProperty(value = "Icon")
    private Integer icon;
    @JsonProperty(value = "IconPhrase")
    private String iconPhrase;
    @JsonProperty(value = "HasPrecipitation")
    private boolean hasPrecipitation;
    @JsonProperty(value = "PrecipitationType")
    private String precipitationType;
    @JsonProperty(value = "PrecipitationIntensity")
    private String precipitationIntensity;

}
