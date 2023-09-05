package com.vi.demo;

import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FilterSource
 */
public class copyJar {

    @Test
    public void findOutAllJar() {

        String repoPath = "G:\\maven\\repository_for_dbstudio";
        File file1 = new File(repoPath);
        if (file1.isDirectory()) {
            handlerJarName(file1);
        }
        System.out.println("done!");
    }

    private void handlerJarName(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    handlerJarName(file);
                } else {
                    copy(file);
                }
            }
        }
    }

    private void copy(File file) {

        String fileName = file.getName();
        if (fileName.endsWith("jar") && !fileName.contains("source")) {
            File goal = new File("G:\\maven\\zzkk\\jars\\" + file.getName());
            try {
                System.out.println(file.getPath());
                //copyFileUsingStream(file, goal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

}
