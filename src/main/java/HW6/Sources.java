package HW6;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Sources {

    @JsonProperty(value = "Source")
    private String source;
}
