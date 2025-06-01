package hse.kpo.factories;

import hse.kpo.domains.DurationReportElement;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DurationReportFactory {
    public DurationReportElement create(float duration, ProceedingJoinPoint pjp) {
        return new DurationReportElement(duration, pjp, LocalDateTime.now());
    }
}
