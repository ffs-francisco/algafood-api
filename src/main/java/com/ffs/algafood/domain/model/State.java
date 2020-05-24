package com.ffs.algafood.domain.model;

import com.ffs.algafood.core.validation.Groups;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author francisco
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class State implements Serializable {

    @NotNull(groups = Groups.StateId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;
}
