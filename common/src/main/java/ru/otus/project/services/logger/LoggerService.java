package ru.otus.project.services.logger;

import org.slf4j.Logger;
import ru.otus.project.models.dto.BaseProjectDto;

public interface LoggerService {
    void serviceLogging(Logger logger, int code, String message);

    void serviceLogging(Logger logger, BaseProjectDto baseDto);

    void addAuditLog(String type, String scope, String service, String method, String message);
}
