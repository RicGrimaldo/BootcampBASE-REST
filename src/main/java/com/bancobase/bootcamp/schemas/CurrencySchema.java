package com.bancobase.bootcamp.schemas;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "currencies")
@Data
@NoArgsConstructor
public class CurrencySchema {
    @Id
    @Column(name = "currency_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long currencyId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="symbol", nullable = false, unique = true)
    private String symbol;

    @Column(name="value", nullable = false)
    private Double value;
}
