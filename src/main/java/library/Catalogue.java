package library;

import java.time.LocalDate;

/* creo una classe astratta Catalogue (genitore) che avrà in comune le proprietà
    e i metodi con le classi figlie (Books e Magazines) */

public class Catalogue {

    private static Integer nextISBN = 1;
    private Integer ISBN;
    private String title;
    private LocalDate publicationYear;
    private int pages;

    @Override
    public String toString() {
        return "Catalogue{" +
                "ISBN=" + ISBN +
                ", title ='" + title + '\'' +
                ", publication year =" + publicationYear +
                ", pages =" + pages +
                '}';
    }

    public Catalogue(String title, LocalDate publicationYear, int pages) {
        this.ISBN = nextISBN;
        nextISBN ++;
        this.title = title;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }

    public static Integer getNextISBN() {
        return nextISBN;
    }

    public static void setNextISBN(int nextISBN) {
        Catalogue.nextISBN = nextISBN;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
