import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME

        String[] myMovies = new String[10]; // Array to store movie attributes read from the CSV
        Movies movie;
        String title;
        Integer year = 0;
        String distributor;
        Long budget = 0L;
        Long domOpen = 0L;
        Long domSales = 0L;
        Long intSales = 0L;
        Long wwSales = 0L;
        String runtime;
        String license;

        ArrayList<Movies> data = new ArrayList<>();
        for (int i = 0; i < numLines; i++) {
            String line = inputFileNameScanner.nextLine();
            if (line == null || line.isEmpty()) {
                continue; // Skip empty lines
            } else {
                line = line.trim();
            }
            myMovies = line.split(",");
            if (myMovies.length != 10) {
                continue; // Skip lines that don't have all 10 attributes
            }
            try {
                // Set the movie attributes
                title = myMovies[0] == null ? "N/A" : myMovies[0];
                if (isNumeric(myMovies[1])) {year = Integer.parseInt(myMovies[1] == null ? "0" : myMovies[1]);}
                distributor = myMovies[2] == null ? "N/A" : myMovies[2];
                if (isNumeric(myMovies[3])) {budget = Long.parseLong(myMovies[3] == null ? "0" : myMovies[3]);}
                if (isNumeric(myMovies[4])) {domOpen = Long.parseLong(myMovies[4] == null ? "0" : myMovies[4]);}
                if (isNumeric(myMovies[5])) {domSales = Long.parseLong(myMovies[5] == null ? "0" : myMovies[5]);}
                if (isNumeric(myMovies[6])) {intSales = Long.parseLong(myMovies[6] == null ? "0" : myMovies[6]);}
                if (isNumeric(myMovies[7])) {wwSales = Long.parseLong(myMovies[7] == null ? "0" : myMovies[7]);}
                runtime = myMovies[8] == null ? "0" : myMovies[8];
                license = myMovies[9] == null ? "0" : myMovies[9];

                // Create and set values for the movie object
                movie = new Movies();
                movie.setTitle(title);
                movie.setYear(year);
                movie.setDistributor(distributor);
                movie.setBudget(budget);
                movie.setDomOpen(domOpen);
                movie.setDomSales(domSales);
                movie.setIntSales(intSales);
                movie.setWwSales(wwSales);
                movie.setRuntime(runtime);
                movie.setLicense(license);

                data.add(movie);
            } catch (NumberFormatException e) {
                break;
            }
        }
        inputFileNameScanner.close();

        double runTime;
        long start, end;
        String content;
        SeparateChainingHashTable<Movies> hashTable = new SeparateChainingHashTable<>();

        //clearFile();

        // Sorted
        content = "\nRuntimes for " + numLines + " sorted movies:";
        System.out.println(content);
        writeToFile(content);
        Collections.sort(data);

        // Insert
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.insert(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Insert runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);

        // Search
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.contains(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Search runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);

        // Delete
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.remove(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Remove runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);



        // Shuffled
        content = "\nRuntimes for " + numLines + " shuffled movies:";
        System.out.println(content);
        writeToFile(content);
        Collections.shuffle(data);

        // Insert
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.insert(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Insert runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);

        // Search
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.contains(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Search runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);

        // Delete
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.remove(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Remove runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);



        // Reversed
        content = "\nRuntimes for " + numLines + " reversed movies:";
        System.out.println(content);
        writeToFile(content);
        Collections.sort(data, Collections.reverseOrder());

        // Insert
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.insert(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Insert runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);

        // Search
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.contains(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Search runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);

        // Delete
        start = System.nanoTime();
        for (Movies m : data) {
            hashTable.remove(m);
        }
        end = System.nanoTime();
        runTime = end - start;
        runTime = runTime / 1_000_000_000.0;

        content = "Remove runtime:\t" + runTime + " seconds";
        writeToFile(content);
        System.out.println(content);
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void writeToFile(String content) {
        try (FileWriter fw = new FileWriter("./analysis.txt", true)) {
            fw.write(content + System.lineSeparator());
        } catch (IOException e) {
            System.exit(1);
        }
    }

    private static void clearFile() {
        try (FileWriter fw = new FileWriter("./analysis.txt", false)) {
            fw.write("");
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
