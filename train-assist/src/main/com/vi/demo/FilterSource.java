package com.vi.demo;

import org.junit.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FilterSource
 */
public class FilterSource {

    @Test
    public void findOutAllJar() {

        String repoPath = "G:\\maven\\zzkk\\vds_sources_folder";
        File file1 = new File(repoPath);
        if (file1.isDirectory()) {
            String[] fileNames = file1.list();
            for (String fileName : fileNames) {
                handlerJarName(fileName);
            }
        }
    }

    private void handlerJarName(String jarName) {

        String reg = "(.*)(-){1}(\\d.*)(-)(.*)";
        Pattern pattern = Pattern.compile(reg);
        Matcher ma = pattern.matcher(jarName);
        if (ma.find()) {
            //System.out.println(ma.group(1));
            //System.out.println(ma.group(3));
        } else {
            handlerJarName2(jarName);
        }
    }

    private void handlerJarName2(String jarName) {
        String reg = "(.*)(\\.){1}(source)(_)(\\d.*)";
        Pattern pattern = Pattern.compile(reg);
        Matcher ma = pattern.matcher(jarName);
        if (ma.find()) {
            //System.out.println(ma.group(1));
            //System.out.println(ma.group(5));
        } else {
            System.out.println(jarName);
        }
    }

}
