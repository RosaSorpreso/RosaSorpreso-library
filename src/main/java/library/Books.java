package library;

import java.time.LocalDate;

public class Books extends Catalogue{
    private String author;
    private String genre;

    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}

    public Books(Integer isbn, String title, LocalDate publicationYear, int pages, String author, String genre) {
        super(title, publicationYear, pages);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return super.toString() + "Books{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
