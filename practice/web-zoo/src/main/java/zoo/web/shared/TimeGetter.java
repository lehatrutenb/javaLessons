package zoo.web.shared;

import org.springframework.stereotype.Component;
import zoo.web.ishared.ItimeGetter;

import java.time.LocalDate;

@Component
public class TimeGetter implements ItimeGetter {
    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
