package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = (SchoolBook) book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int number = 0;
        SchoolBook[] tmp = new SchoolBook[count()];
        for (int i = 0; i < count(); i++) {
            if (name.equals(schoolBooks[i].getName())) {
                tmp[number++] = schoolBooks[i];
            }
        }
        SchoolBook[] findedBooks = new SchoolBook[number];
        for (int i = 0; i < number; i++) {
            findedBooks[i] = tmp[i];
        }
        return findedBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int numberOfBooks = findByName(name).length;
        if (numberOfBooks == 0) {
            return false;
        }
        SchoolBook[] removed = new SchoolBook[count() - numberOfBooks];
        numberOfBooks = 0;
        for (SchoolBook book : schoolBooks) {
            if (book.getName() != name) {
                numberOfBooks++;
                removed[numberOfBooks] = book;

            }
        }
        schoolBooks = removed;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
