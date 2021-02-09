package com.ffs.algafood.domain.model.restaurant;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

/**
 *
 * @author francisco
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ProductPhoto implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "product_id")
    private Long id;

    private String fileName;
    private String description;
    private String contentType;
    private Long size;

    @MapsId
    @OneToOne(fetch = LAZY)
    private Product product;
}
