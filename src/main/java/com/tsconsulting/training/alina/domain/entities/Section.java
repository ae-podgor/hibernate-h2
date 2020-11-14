package com.tsconsulting.training.alina.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "library_section")
public class Section {
    public static final String SEQ_BOOK_GENERATOR = "seq_book_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_BOOK_GENERATOR)
    @SequenceGenerator(name = SEQ_BOOK_GENERATOR, sequenceName = SEQ_BOOK_GENERATOR, allocationSize = 5)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

}
