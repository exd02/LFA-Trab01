import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@AllArgsConstructor
public class AutomatoFinito {
    private Set<String> endPoints;
    private List<Transition> transitionList;
    private String curState;

    public AutomatoFinito() {
        this.endPoints = new HashSet<>();
        this.transitionList = new ArrayList<>();
        curState = "";
    }

    public void loadFromCsv(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            curState = line;

            String[] cuts = line.split(";");
            if (cuts[0] != null && !cuts[0].equals(""))
                curState = cuts[0];
        }

        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cuts = line.split(";");
            for (String cut : cuts) {
                if (cut != null && !cut.equals(""))
                    endPoints.add(cut);
            }
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cuts = line.split(";");

            if (cuts.length == 3) {
                String initialState = cuts[0];
                char symbol = cuts[1].charAt(0);
                String finalState = cuts[2];

                transitionList.add(new Transition(initialState, symbol, finalState));
            }
        }

        scanner.close();
    }

    public boolean validator(String sentence) {
        boolean found;
        for (int i = 0; i < sentence.length(); i++) {
            found = false;
            for (Transition t : transitionList) {
                if (t.getSrc().equals(curState) && t.getLetter() == sentence.charAt(i)) {
                    System.out.println("d(" + t.getSrc() + "; " + t.getLetter() + ") = " + t.getDes());
                    curState = t.getDes();
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        return endPoints.contains(curState);
    }
}
