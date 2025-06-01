package hse.kpo.observers;

import hse.kpo.builders.ReportBuilder;
import hse.kpo.domains.DurationReportElement;
import hse.kpo.factories.DurationReportExporterFactory;
import hse.kpo.factories.DurationReportFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class MeasureDurationAspect {
    @Autowired
    ReportBuilder<DurationReportElement> reportBuilder;
    @Autowired
    DurationReportFactory durationReportFactory;

    @Around("@annotation(measureDuration)")
    public Object measureDuration(final ProceedingJoinPoint pjp, final MeasureDuration measureDuration) throws Throwable {
        final long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            final long duration = System.currentTimeMillis() - start;
            reportBuilder.addReportElement(durationReportFactory.create(duration, pjp));
        }
    }
}