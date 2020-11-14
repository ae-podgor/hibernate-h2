package com.tsconsulting.training.alina.generator;

import com.tsconsulting.training.alina.domain.entities.Author;

public class AuthorGenerator {

    private static Author generateNewAuthor(String fName, String lName) {
        Author author = new Author();
        author.setFirstName("Harry");
        author.setLastName("Harrison");
        return author;
    }
}
