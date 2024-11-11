import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        AFD af = new AFD();
        af.loadFromCsv("C:\\_dev\\test.csv");

        String sentence = "abbba";

        boolean status = af.validator(sentence);
        System.out.println("O automato " + (status ? "aceita" : "rejeita") + " a sentença '" + sentence + "'.");
    }
}