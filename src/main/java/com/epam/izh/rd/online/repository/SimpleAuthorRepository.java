package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = {};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[this.count() - 1] = author;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName() == name && authors[i].getLastName() == lastname) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        Author[] temp;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {
            temp = new Author[count() - 1];
            int i = 0;
            for (Author findedAuthor : authors) {
                if (findedAuthor != findByFullName(author.getName(), author.getLastName())) {
                    temp[i] = findedAuthor;
                    i++;
                }
            }
        }
        authors = temp;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
