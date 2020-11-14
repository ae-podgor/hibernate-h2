package com.tsconsulting.training.alina.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "library_section")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section", cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<>();

}
