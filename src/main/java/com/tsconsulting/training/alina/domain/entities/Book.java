package com.tsconsulting.training.alina.domain.entities;

import com.tsconsulting.training.alina.constant.Genre;
import com.tsconsulting.training.alina.converter.YearAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.Year;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    public static final String SEQ_BOOK_GENERATOR = "seq_book_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_BOOK_GENERATOR)
    @SequenceGenerator(name = SEQ_BOOK_GENERATOR, sequenceName = SEQ_BOOK_GENERATOR, allocationSize = 5)
    private Long id;

    @Column(name = "book_name", length = 1000, nullable = false)
    private String bookName;

    @Column(name = "publication_year")
    @Convert(converter = YearAttributeConverter.class)
    private Year publicationYear;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String language;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "author_book",
            joinColumns = {@JoinColumn(name = "fk_book", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_author", referencedColumnName = "id")})
    private Set<Author> authors = Collections.emptySet();
}
