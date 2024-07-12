package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "group_account")
public class GroupAccount {
    @EmbeddedId
    private PrimaryKey pk;

    @Column(name = "joined_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate joinedDate;

    @Getter
    @Setter
    @Embeddable
    public static class PrimaryKey implements Serializable {
        @Column(name = "group_id", nullable = false)
        private int groupId;

        @Column(name = "account_id", nullable = false)
        private int accountId;
    }
}
