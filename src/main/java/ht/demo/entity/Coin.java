package ht.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Coin {

    @Id
    @Column
    private String charName;
    @Column
    private String disclaimer;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @OneToMany(
        cascade = { CascadeType.ALL },
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        mappedBy = "id.charName"
    )
    private List<Bpi> bpis;
}
