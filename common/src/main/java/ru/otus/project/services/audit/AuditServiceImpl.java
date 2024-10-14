package ru.otus.project.services.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.annotations.ProjectLog;
import ru.otus.project.models.AuditRecord;
import ru.otus.project.models.dto.AuditRecordDto;
import ru.otus.project.repositories.AuditRepository;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    @ProjectLog
    @Override
    public AuditRecordDto add(AuditRecordDto auditRecord) {
        AuditRecord savedRecord = auditRepository.save(auditRecord.toDomainObject());
        return AuditRecordDto.fromDomainObject(savedRecord);
    }

    @ProjectLog
    @Override
    public void delete(AuditRecordDto auditRecord) {
        auditRepository.delete(auditRecord.toDomainObject());
    }

    @ProjectLog
    @Override
    public List<AuditRecordDto> findAll() {
        return auditRepository.findAll().stream()
                .map(AuditRecordDto::fromDomainObject).toList();
    }

    @ProjectLog
    @Override
    public List<AuditRecordDto> findAllByType(String type) {
        return auditRepository.findAllByType(type).stream()
                .map(AuditRecordDto::fromDomainObject).toList();
    }

    @ProjectLog
    @Override
    public List<AuditRecordDto> findAllByScope(String scope) {
        return auditRepository.findAllByScope(scope).stream()
                .map(AuditRecordDto::fromDomainObject).toList();
    }

    @ProjectLog
    @Override
    public List<AuditRecordDto> findAllByTypeAndScope(String type, String scope) {
        return auditRepository.findAllByTypeAndScope(type, scope).stream()
                .map(AuditRecordDto::fromDomainObject).toList();
    }

    @ProjectLog
    @Override
    public List<AuditRecordDto> findAllByDate(Date date) {
        return auditRepository.findAllByDate(date).stream()
                .map(AuditRecordDto::fromDomainObject).toList();
    }

    @ProjectLog
    @Override
    public List<AuditRecordDto> findAllByDateBetween(Date dateStart, Date dateEnd) {
        return auditRepository.findAllByDateBetween(dateStart, dateEnd).stream()
                .map(AuditRecordDto::fromDomainObject).toList();
    }

    @ProjectLog
    @Override
    public List<AuditRecordDto> findAllWithDateAfter(Date dateAfter) {
        return auditRepository.findAllWithDateAfter(dateAfter).stream()
                .map(AuditRecordDto::fromDomainObject).toList();
    }
}
