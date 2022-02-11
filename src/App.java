import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class App {
    private static String _dirPath = "";
    private static Integer _fileCount = 0;

    public static void main(String[] args) throws Exception {

        System.out.print("Enter directory path: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        _dirPath = reader.readLine();

        System.out.println("Directory was set to: " + _dirPath);

        File[] files = new File(_dirPath).listFiles();

        System.out.println("Found " + files.length + " files in the directory.");
        System.out.println("Press any key to start");
        reader.read();

        Pattern nativeIphonePattern = Pattern.compile("IMG_[0-9]{4}(_[0-9]{3,4})?\\.");

        File externalDir = new File(_dirPath + "\\External");
        if (externalDir.exists()) {
            System.out.println("Found an External directory");
        } else {
            externalDir.mkdir();
            System.out.println("No External directory found, created one");
        }

        for (File file : files) {
            if (file.isFile()) {
                _fileCount++;
                if (nativeIphonePattern.matcher(file.getName()).find()) {
                    System.out.println("Found native iPhone image: " + file.getName() + " - Will not be moved");
                } else {
                    File newFile = new File(externalDir.getAbsolutePath() + "\\" + file.getName());
                    file.renameTo(newFile);
                    System.out.println("Moved file: " + file.getName() + " to " + newFile.getName());
                }
            }
        }

        System.out.println("Finished moving " + _fileCount + " files");
        System.out.println("Thank you for using this program");
        System.out.print("iMediaSorter by Myuuiii");
    }
}
