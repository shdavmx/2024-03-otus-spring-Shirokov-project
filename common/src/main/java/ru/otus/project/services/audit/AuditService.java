package ru.otus.project.services.audit;

import org.springframework.data.repository.query.Param;
import ru.otus.project.models.dto.AuditRecordDto;

import java.util.Date;
import java.util.List;

public interface AuditService {
    AuditRecordDto add(AuditRecordDto auditRecord);

    void delete(AuditRecordDto auditRecord);

    List<AuditRecordDto> findAll();

    List<AuditRecordDto> findAllByType(String type);

    List<AuditRecordDto> findAllByScope(String scope);

    List<AuditRecordDto> findAllByTypeAndScope(String type, String scope);

    List<AuditRecordDto> findAllByDate(Date date);

    List<AuditRecordDto> findAllByDateBetween(Date dateStart, Date dateEnd);

    List<AuditRecordDto> findAllWithDateAfter(@Param("dateAfter") Date dateAfter);
}
