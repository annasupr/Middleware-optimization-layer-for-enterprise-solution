package com.upk.diploma.catalogservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.bridge.builtin.BigDecimalBridge;
import org.hibernate.search.bridge.builtin.DoubleBridge;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "offer")
@Indexed(index = "idx_offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Field
    @Column(name = "title", length = 100)
    private String title;

    @Field
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

//    @Field
//    @FieldBridge(impl = BigDecimalBridge.class)
    @Column(name = "price", columnDefinition = "NUMERIC")
    private BigDecimal price;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "point_of_distribution_id")
    private PointOfDistribution pointOfDistribution;
}
