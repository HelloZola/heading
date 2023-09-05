package com.vi.demo;

import org.junit.Test;

import java.io.File;

public class ChangeFileName {

    @Test
    public void findOutAllJar() {

        String repoPath = "F:\\IDM下载\\plugins";
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
                    continue;
                } else {
                    changename(file);
                }
            }
        }
    }

    private void changename(File file) {
        String newFilePath = file.getPath().replace("_tools_orbit_downloads_drops_R20221123021534_repository_plugins_", "");
        file.renameTo(new File(newFilePath));
    }

    @Test
    public void renameTest() {

        String path = "F:\\IDM下载\\plugins.html";
        File file = new File(path);
        String newFilePath = file.getPath().replace("plugins", "");
        System.out.println(file.getPath());
        System.out.println(newFilePath);
    }
}
