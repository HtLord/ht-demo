package ht.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Bpi {

    @Id
    private String code;
    private String symbol;
    private Float rate;
    private String description;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private Coin coin;
}
