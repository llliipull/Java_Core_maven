package HW6;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Temperature {

    private Minimum minimum;
    private Maximum maximum;
}
