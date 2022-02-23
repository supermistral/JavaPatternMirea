package ru.mirea.task12;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Component
public class FileHandler {
    private String inputfileName;
    private String outputFileName;
    private String fileHash;

    public FileHandler(ApplicationArguments args) {
        List<String> commandArgs = args.getNonOptionArgs();
        if (commandArgs.size() > 1) {
            inputfileName = commandArgs.get(0);
            outputFileName = commandArgs.get(1);
        }
    }

    @PostConstruct
    public void readFile() {
        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
            return;
        }

        BufferedInputStream inputStream;

        try {
            inputStream = new BufferedInputStream(new FileInputStream(inputfileName));
            byte[] buffer = new byte[8192];
            int count;

            while ((count = inputStream.read(buffer)) > 0) {
                digest.update(buffer, 0, count);
            }

            inputStream.close();
        } catch (FileNotFoundException ex) {
            fileHash = "null";
            return;
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }

        fileHash = String.format("%064x", new BigInteger(1, digest.digest()));
    }

    @PreDestroy
    public void writeFile() {
        if (outputFileName.isEmpty()) {
            return;
        }

        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write(fileHash);

            writer.flush();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
