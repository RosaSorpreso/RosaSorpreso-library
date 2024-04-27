package library;

public class Magazines extends Catalogue{
    private Periodicity periodicity;

    public Periodicity getPeriodicity() {return periodicity;}
    public void setPeriodicity(Periodicity periodicity) {this.periodicity = periodicity;}

    public Magazines(String title, int publicationYear, int pages, Periodicity periodicity) {
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
