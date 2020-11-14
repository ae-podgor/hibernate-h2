package com.tsconsulting.training.alina;

import com.tsconsulting.training.alina.constant.Genre;
import com.tsconsulting.training.alina.domain.entities.Author;
import com.tsconsulting.training.alina.domain.entities.Book;
import com.tsconsulting.training.alina.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 */
public class LibraryApp {



    public static void main(String[] args) {
        Author author1 = generateNewAuthor("Harry", "H");
        Author author2 = generateNewAuthor("David", "B");

        Book book1 = generateNewBook("Bill, the Galactic Hero on the Planet of Ten Thousand Bars");
        book1.addAuthor(author1);
        book1.addAuthor(author2);


        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(author1);
            session.save(author2);
            session.save(book1);


            // commit transaction
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            List<AuthorEntity> authors = session.createQuery("from Author", AuthorEntity.class).list();
//            authors.forEach(a -> System.out.println(a.getFirstName()));
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
    }

    private static Author generateNewAuthor(String fName, String lName) {
        Author author = new Author();
        author.setFirstName(fName);
        author.setLastName(lName);
        return author;
    }

    private static Book generateNewBook(String name) {
        Book book = new Book();
        book.setBookName(name);
        return book;
    }


}

