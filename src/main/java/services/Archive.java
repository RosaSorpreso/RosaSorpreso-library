package services;

import library.Catalogue;

import java.util.Optional;

public interface Archive {
    void add(Catalogue catalogue);
    void deleteISBN(Integer ISBN);
    Optional<Catalogue> getByISBN(Integer ISBN);
    Optional<Catalogue> getPublicationYear(Integer publicationYear);
    void getAuthor(String author);
}
