package com.tsconsulting.training.alina.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "section")
public class Section {
    public static final String SEQ_SECTION_GENERATOR = "seq_section_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_SECTION_GENERATOR)
    @SequenceGenerator(name = SEQ_SECTION_GENERATOR, sequenceName = SEQ_SECTION_GENERATOR, allocationSize = 5)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "section_name", updatable = false, nullable = false, unique = true, length = 4)
    private String name;

    @Column(name = "restricted")
    private boolean isRestricted;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "section")
    private Set<Author> authors = Collections.emptySet();
}
