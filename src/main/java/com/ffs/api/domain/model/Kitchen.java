package com.ffs.api.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author francisco
 */
@JsonRootName("kitchen")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Kitchen implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;
}
