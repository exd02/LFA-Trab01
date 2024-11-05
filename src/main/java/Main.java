import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        AutomatoFinito af = new AutomatoFinito();
        af.loadFromCsv("/home/exd/Documents/1.txt");

        String sentence = "abbba";

        boolean status = af.validator(sentence);
        System.out.println("O automato " + (status ? "aceita" : "rejeita") + " a sentença '" + sentence + "'.");
    }
}