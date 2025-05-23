package ParallelTextProcessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

/**
 * ===REQUIREMENTS===
 * 1. Periodically scan the ./src/main/resources directory and watches for new files
 * 2. For each file found in this directory new thread should take care of its processing.
 * 3. Processing = each line of the file will be hashed using a hashing algorithm
 * 4. The program will create new files, marked _output suffix, and they will placed into ./src/main/resources/output
 * 5. Throw an exception if line is empty
 */
public class Main {
public static void main(String[] args) {

    Thread watcher =  new Thread(new Watcher());
    watcher.start();
}
}

class Watcher implements Runnable {
    public void run() {
        File inputDirectory = new File("Project-ParallelTextFileProcessing/resources");
        while(true){
            if (inputDirectory.exists()) {
                 if(inputDirectory.listFiles().length != 0){
                    Arrays.stream(inputDirectory.listFiles()).forEach(
                        file -> {
                            Thread t = new Thread(new FileProcessor(file));
                            t.setUncaughtExceptionHandler(new ExceptionHandler());
                            t.start();

                    });
                }
                sleep();
            }
            else {
                System.out.println("Input directory does not exist");
            }
        }

    }

    private void sleep(){
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}

class FileProcessor implements Runnable {

    private final File file;

private final static  String OUTPUT_PATH ="Project-ParallelTextFileProcessing/output/";
    public FileProcessor(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH+file.getName()));
            Files.lines(Path.of(file.getCanonicalPath()))
                    .map(this::hash)
                        .map(line-> line+"\n")
                            .forEach(el ->{
                                try {
                                    writer.write(el);
                                } catch (IOException e) {
                                   e.printStackTrace();
                                }
                            });

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+ "processed file: "+file.getName());
        //file.delete(); // so files will not processed again

    }
    private String hash(String input) {
        try {
            if(Objects.equals(input, "")){
                throw  new RuntimeException("The line is empty , hashing cannot be done");
            }
            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashbytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Exception handled" + e.getMessage());
    }
}
