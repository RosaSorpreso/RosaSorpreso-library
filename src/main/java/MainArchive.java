import library.Books;
import library.Catalogue;
import library.Magazines;
import library.Periodicity;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainArchive {

    private static Integer ISBN = 0;

    public static File archiveFile = new File("archive.txt");

    public static Scanner scanner = new Scanner(System.in);

    public static Logger logger = LoggerFactory.getLogger(MainArchive.class);

    public static List<Catalogue> archive = new ArrayList<Catalogue>();

    static List<Books> booksList = new ArrayList<Books>();
    static List<Magazines> magazinesList = new ArrayList<Magazines>();

    public static void main(String[] args) {

        try {
            archiveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Books book0 = new Books(0, "prova libro 0", LocalDate.parse("1999-03-02"), 100, "Mario Rossi", "giallo");
        Books book1 = new Books(1, "prova libro 1", LocalDate.parse("1998-04-03"), 150, "Mario Rossi", "narrativa");
        Books book2 = new Books(2, "prova libro 2", LocalDate.parse("1997-05-04"), 180, "Mario Rossi", "romantico");

        Magazines magazine0 = new Magazines(3, "prova rivista 0", LocalDate.parse("1999-03-02"), 100, Periodicity.SEMIANNUAL);
        Magazines magazine1 = new Magazines(4, "prova rivista 1", LocalDate.parse("1999-05-07"), 108, Periodicity.WEEKLY);
        Magazines magazine2 = new Magazines(5, "prova rivista 2", LocalDate.parse("1999-06-03"), 150, Periodicity.MONTHLY);

        booksList.add(book0);
        booksList.add(book1);
        booksList.add(book2);

        magazinesList.add(magazine0);
        magazinesList.add(magazine1);
        magazinesList.add(magazine2);

        archive = Stream.concat(booksList.stream(), magazinesList.stream()).collect(Collectors.toList());
        System.out.println(archive);

        int start = 1;

        do {
            System.out.printf("Digit a number : %n"
                    + "1 - add article to catalogue %n"
                    + "2 - remove article by ISBN %n"
                    + "3 - search article by ISBN %n"
                    + "4 - search article by year %n"
                    + "5 - search book by author %n"
                    + "6 - write file on disc %n"
                    + "7 - read file on disc %n"
                    + "0 - end program %n");
            try {
                start = Integer.parseInt(scanner.nextLine());

                switch (start) {
                    case 1:
                        boolean choose = true;
                        while(choose) {
                            System.out.printf("Choose between 1-Magazine,  2-Book: " );
                            int res1 = Integer.parseInt(scanner.nextLine());

                            if(res1 == 1) {
                                addArticle(magazinesList);
                                choose = false;
                            }else if(res1 == 2) {
                                addArticle(booksList);
                                choose = false;
                            }else {
                                System.out.println("wrong number");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("ISBN: " );
                        String res2 = scanner.nextLine();
                        removeByISBN(Integer.valueOf(res2));
                        break;
                    case 3:
                        System.out.println("ISBN: " );
                        String res3 = scanner.nextLine();
                        searchByISBN(Integer.valueOf(res3));
                        break;
                    case 4:
                        System.out.println("Year (Y-M-D): " );
                        String res4 = scanner.nextLine();
                        searchByYear(LocalDate.parse(res4));
                        break;
                    case 5:
                        System.out.println("Author: " );
                        String res5 = scanner.nextLine();
                        searchByAuthor(res5);
                        break;
                    case 6:
                        try {
                            writeFile();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        try {
                            readFile();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                }

            }catch(NumberFormatException e) {
                logger.info("you can write only numbers");
            }
        } while (start != 0);

        logger.info("you ended the program");
    }

    //creo i metodi:

    //metodo per rimuovere un articolo dal catalogo tramite codice isbn
    public static void removeByISBN(Integer isbn) {
        archive.removeIf(el -> el.getISBN().equals(isbn));
        logger.info("article removed by ISBN: " + isbn);
    }

    //metodo per cercare un articolo dal catalogo tramite ISBN
    public static void searchByISBN(Integer isbn){
        Stream<Catalogue> article = archive.stream().filter(el -> el.getISBN().equals(isbn));
        article.forEach(el -> logger.info("article with ISBN: " + isbn + ": " + el.toString()));
    }

    //metodo per cercare un articolo dal catalogo tramite anno di pubblicazione
    public static void searchByYear(LocalDate year){
        Stream<Catalogue> article = archive.stream().filter(el -> el.getPublicationYear().equals(year));
        article.forEach(el -> logger.info("article published in " + year + ": " + el.toString()));
    }

    //metodo per cercare un libro dal catalogo tramite l'autore
    public static void searchByAuthor(String author){
        Stream<Books> book =
                archive
                        .stream()
                        .filter(el -> el instanceof Books).map(e -> (Books) e)
                        .filter(el -> el.getAuthor().equals(author));
        book.forEach(el -> logger.info("Books written by " + author + ": " + el.toString()));
    }

    //metodo per aggiungere un articolo al catalogo
    public static void addArticle(List<?> unknown){
        System.out.println("add the following infos: ");

        if(unknown.get(0).getClass() == Books.class){
            ISBN++;
            Integer isbn = ISBN++;

            System.out.println("Title:");
            String title = scanner.nextLine();

            System.out.println("Year: ");
            LocalDate year = (LocalDate.ofYearDay(Integer.parseInt(scanner.nextLine()), 1));

            System.out.println("Pages: ");
            int pages = Integer.parseInt(scanner.nextLine());

            System.out.println("Author: ");
            String author = scanner.nextLine();

            System.out.println("Genre: ");
            String genre = scanner.nextLine();

            archive.add(new Books(isbn, title, year, pages, author, genre));
            logger.info("Book added with success");

        } else if(unknown.get(0).getClass() == Magazines.class) {
            ISBN++;
            Integer isbn = ISBN++;

            System.out.println("Title:");
            String title = scanner.nextLine();

            System.out.println("Year: ");
            LocalDate year = (LocalDate.ofYearDay(Integer.parseInt(scanner.nextLine()), 1));

            System.out.println("Pages: ");
            int pages = Integer.parseInt(scanner.nextLine());

            System.out.println("Magazine periodicity: 1 Weekly, 2 Monthly, 3 Semiannual");
            int res = Integer.parseInt(scanner.nextLine());
            Periodicity periodicity = null;

            switch (res){
                case 1:
                    periodicity = Periodicity.WEEKLY;
                    break;
                case 2:
                    periodicity = Periodicity.MONTHLY;
                    break;
                case 3:
                    periodicity = Periodicity.SEMIANNUAL;
                    break;
            }

            archive.add(new Magazines(isbn, title, year, pages, periodicity));
            logger.info("Magazine added with success");

        }else {
            logger.info("Error");
        }
    }

    //metodo per salvare un articolo nel disco
    public static void writeFile() throws IOException {
        archive.forEach(el -> {
            try {
                FileUtils.writeStringToFile(archiveFile, el.toString(), "UTF-8", true);
                logger.info("Article saved with success");
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    //metodo per caricare un elemento dal disco
    public static void readFile() throws IOException {
        String txtFile = FileUtils.readFileToString(archiveFile, "UTF-8");
        String[] obj = txtFile.split("#");
        for (int i = 0; i < obj.length; i++) {
            logger.info(obj[i]);
        }
    }
}
