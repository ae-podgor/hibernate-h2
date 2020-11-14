package com.tsconsulting.training.alina.domain.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


// Были проблемы, если аннотация @Data и у Author, и у Book; ошибка "Method threw 'org.hibernate.HibernateException' exception. Cannot evaluate com.tsconsulting.training.alina.domain.entities.Author.toString()"
//попробовать на many-to-many @ToString.Exclude
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author_data")
public class Author {
    public static final String SEQ_AUTHOR_GENERATOR = "seq_author_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_AUTHOR_GENERATOR)
    @SequenceGenerator(name = SEQ_AUTHOR_GENERATOR, sequenceName = SEQ_AUTHOR_GENERATOR, initialValue = 100, allocationSize = 5)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = true)
    private LocalDateTime birthDate;

    @Column(name = "alive", nullable = true)
    private boolean isAlive;

    @ManyToOne()
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

}
