package ru.otus.project.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.otus.project.models.AuditRecord;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class AuditRecordDto extends BaseProjectDto {
    private Long id;

    private Date date;

    private String type;

    private String scope;

    private String service;

    private String method;

    private String message;

    public static AuditRecordDto fromDomainObject(AuditRecord auditRecord) {
        return new AuditRecordDto(auditRecord.getId(), auditRecord.getDate(), auditRecord.getType(),
                auditRecord.getScope(), auditRecord.getService(), auditRecord.getMethod(), auditRecord.getMessage());
    }

    public AuditRecord toDomainObject() {
        return new AuditRecord(this.id, this.date, this.type, this.scope, this.service, this.method, this.message);
    }
}
