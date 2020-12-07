package com.tsconsulting.training.alina;

import com.tsconsulting.training.alina.domain.entities.Author;
import com.tsconsulting.training.alina.domain.entities.Book;
import com.tsconsulting.training.alina.domain.entities.Section;
import com.tsconsulting.training.alina.util.HibernateUtil;
import com.tsconsulting.training.alina.util.LiquibaseInit;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.Year;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Программа создает базу
 */
public class LibraryApp {
    public static void main(String[] args) {
        // Инициализируем Liquibase
        LiquibaseInit.init();

        // Добавляем секцию
        Section section = generateNewSection("AB78");

        // Добавляем авторов
        Author author1 = generateNewAuthor("Harry", "Harrison");
        Author author2 = generateNewAuthor("David", "Bischoff");
        author1.setSection(section);
        author2.setSection(section);

        // Добавляем книгу
        Book book1 = generateNewBook("Bill, the Galactic Hero on the Planet of Ten Thousand Bars");
        book1.setAuthors(Set.of(author1, author2));
        book1.setPublicationYear(Year.of(2006));

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Стартуем транзакцию
            transaction = session.beginTransaction();
            // Сохраняем новые объекты
            session.save(section);
            session.save(author1);
            session.save(author2);
            session.save(book1);

            // Коммитим транзакцию
            transaction.commit();

            Author authorOne = session.find(Author.class, 100L);
            System.out.println(authorOne);
            Author authorTwo = session.find(Author.class, 101L);
            System.out.println(authorTwo);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private static Section generateNewSection(String name) {
        Section section = new Section();
        section.setName(name);
        return section;
    }

    private static Author generateNewAuthor(String fName, String lName) {
        Author author = new Author();
        author.setFirstName(fName);
        author.setLastName(lName);
//        author.setSection(section);
        return author;
    }

    private static Book generateNewBook(String name) {
        Book book = new Book();
        book.setBookName(name);
        return book;
    }


}

