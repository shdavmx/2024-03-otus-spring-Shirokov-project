package ru.otus.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(schema = "common", name = "audit_records")
public class AuditRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date")
    private Date date;

    @Column(name = "type")
    private String type;

    @Column(name = "scope")
    private String scope;

    @Column(name = "service")
    private String service;

    @Column(name = "method")
    private String method;

    @Column(name = "message")
    private String message;
}
