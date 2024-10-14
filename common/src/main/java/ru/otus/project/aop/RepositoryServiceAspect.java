package ru.otus.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import ru.otus.project.models.dto.BaseProjectDto;
import ru.otus.project.services.audit.AuditService;
import ru.otus.project.services.base.BaseService;

@Aspect
@Component
public class RepositoryServiceAspect extends BaseService {
    private final Logger logger = LoggerFactory.getLogger(RepositoryServiceAspect.class);

    public RepositoryServiceAspect(AuditService auditService) {
        super(auditService);
    }

    @Pointcut("@annotation(ProjectLog)")
    public void projectPointCut() {
    }

    @Around("projectPointCut()")
    public Object aroundProjectRepositoryAdvice(ProceedingJoinPoint jp) throws Throwable {
        BaseProjectDto baseObject = new BaseProjectDto();
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            return jp.proceed();
        } catch (RuntimeException e) {
            baseObject.setCode(500);
            baseObject.setFailed(true);
            baseObject.setErrorText(e.getMessage());
            serviceLogging(logger, 500, baseObject.getErrorText());
        }
        finally {
            stopWatch.stop();
            serviceLogging(logger, 100, "Elapsed time of method '%s' ".formatted(jp.toShortString() +
                    "= '%s'".formatted(stopWatch.prettyPrint())));
        }

        return baseObject;
    }
}
