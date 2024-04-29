package library;

import java.time.LocalDate;

public class Magazines extends Catalogue{
    private Periodicity periodicity;

    public Periodicity getPeriodicity() {return periodicity;}
    public void setPeriodicity(Periodicity periodicity) {this.periodicity = periodicity;}

    public Magazines(Integer isbn, String title, LocalDate publicationYear, int pages, Periodicity periodicity) {
        super(title, publicationYear, pages);
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return super.toString() + "Magazines{" +
                "periodicity =" + periodicity +
                '}';
    }
}
