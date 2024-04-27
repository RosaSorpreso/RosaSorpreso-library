package library;

public class Catalogue {

    private static int nextISBN = 1;
    private Integer ISBN;
    private String title;
    private Integer publicationYear;
    private int pages;

    @Override
    public String toString() {
        return "Catalogo{" +
                "ISBN=" + ISBN +
                ", titolo ='" + title + '\'' +
                ", anno di pubblicazione =" + publicationYear +
                ", numero di pagine =" + pages +
                '}';
    }

    public Catalogue(String title, int publicationYear, int pages) {
        this.ISBN = nextISBN;
        nextISBN ++;
        this.title = title;
        this.publicationYear = publicationYear;
        this.pages = pages;
    }

    public static int getNextISBN() {
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

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
