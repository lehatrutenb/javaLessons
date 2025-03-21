package hse.kpo.domains;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.time.LocalDateTime;

public class DurationReportElement {
    @Getter
    private final LocalDateTime timestamp;
    @Getter
    private final float duration;
    @Getter
    private final String operation;

    public DurationReportElement(float duration, ProceedingJoinPoint pjp, LocalDateTime timestamp) {
        this.duration = duration;
        operation = pjp.toString();
        this.timestamp = timestamp;
    }

    public String toString() {
        return String.format("%s with duration: %f ms", operation, duration);
    }
}
