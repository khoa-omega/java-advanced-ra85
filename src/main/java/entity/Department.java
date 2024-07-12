package entity;

import converter.DepartmentTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "department_id_generator",
            sequenceName = "department_id_sequence",
            initialValue = 5,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_id_generator"
    )
    private int id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Convert(converter = DepartmentTypeConverter.class)
    private Type type;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        System.out.println("Trước khi thêm vào database");
    }

    @PostPersist
    public void postPersist() {
        System.out.println("Sau khi thêm vào database");
    }

    public enum Type {
        DEVELOPER, TESTER, SCRUM_MASTER, PROJECT_MANAGER
    }
}
