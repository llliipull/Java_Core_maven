package HW6;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class DailyForecasts {

        private String date;
        private int epochDate;
        private Temperature temperature;
        private Day day;
        private Night night;
        private Sources sources;
        private String mobileLink;
        private String link;



}
