package hse.kpo.observers;

import hse.kpo.builders.ReportBuilder;
import hse.kpo.domains.DurationReportElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
@RequiredArgsConstructor
@Component
@Aspect
public class MeasureDurAspect {
    @Autowired
    private final ReportBuilder<DurationReportElement> reportBuilder;

    @Around("@annotation(measureDuration)")
    public Object measureDuration(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object result = pjp.proceed();
        long finish = System.nanoTime();
        reportBuilder.addReportElement(new DurationReportElement(finish-start, pjp, LocalDateTime.now()));
        return result;
    }
}
*/