package ru.otus.project.services.base;

import org.slf4j.Logger;
import ru.otus.project.models.dto.AuditRecordDto;
import ru.otus.project.models.dto.BaseProjectDto;
import ru.otus.project.services.audit.AuditService;
import ru.otus.project.services.logger.LoggerService;


public class BaseService implements LoggerService {
    private final AuditService auditService;

    public BaseService(AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public void serviceLogging(Logger logger, int code, String message) {
        if(code == 0) {
            logger.trace(message);
        } else if (code > 0 && code < 200) {
            logger.debug(message);
        } else if (code > 200 && code < 400) {
            logger.info(message);
        } else if (code > 400) {
            logger.error(message);
        }
    }

    @Override
    public void serviceLogging(Logger logger, BaseProjectDto baseDto) {
        serviceLogging(logger, baseDto.getCode(), baseDto.getErrorText());
    }

    @Override
    public void addAuditLog(String type, String scope, String service, String method, String message) {
        AuditRecordDto record = new AuditRecordDto();
        record.setType(type);
        record.setScope(scope);
        record.setService(service);
        record.setMethod(method);
        record.setMessage(message);

        auditService.add(record);
    }
}
