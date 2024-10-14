package ru.otus.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.project.models.AuditRecord;

import java.util.Date;
import java.util.List;

public interface AuditRepository extends JpaRepository<AuditRecord, Long> {
    List<AuditRecord> findAllByType(String type);

    List<AuditRecord> findAllByScope(String scope);

    List<AuditRecord> findAllByTypeAndScope(String type, String scope);

    List<AuditRecord> findAllByDate(Date date);

    List<AuditRecord> findAllByDateBetween(Date dateStart, Date dateEnd);

    @Query("select a from AuditRecord a where a.date >= :dateAfter")
    List<AuditRecord> findAllWithDateAfter(@Param("dateAfter") Date dateAfter);
}
