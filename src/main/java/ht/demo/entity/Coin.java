package ht.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coin {

    @Id
    @Column
    private String charName;
    @Column
    private String disclaimer;

    @OneToMany(
        fetch = FetchType.LAZY,
        orphanRemoval = true,
        mappedBy = "coin"
    )
    private List<Bpi> bpis;
}
