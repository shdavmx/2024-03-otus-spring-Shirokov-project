package ru.otus.project.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "storage", name = "data")
public class ProjectData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "formats")
    private String formats;

    @Column(name = "is_active")
    private boolean is_active;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private DataType dataType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_location_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private StorageLocation location;
}
