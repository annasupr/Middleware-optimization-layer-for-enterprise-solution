package com.upk.diploma.catalogservice.model.entity;

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
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_instance")
@Indexed(index = "idx_product_instance")
public class ProductInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "storehouse_id")
    private Storehouse storehouse;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "product_instance_status_id")
    private ProductInstanceStatus productInstanceStatus;
}
