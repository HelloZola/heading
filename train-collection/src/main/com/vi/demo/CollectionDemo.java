package com.vi.demo;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CollectionDemo {


    @Test
    public void test1() {

        HashMap map = new HashMap();
        Hashtable table = new Hashtable();

        //map.put(null, null);
        //table.put(null, null);
    }


    @Test
    public void printMvnCmd() {

        String fileName = "";
        String groupId = "";
        String artifactId = "";
        String version = "";
        String packaging = "";
        String repositoryId = "";
        String url = "";
        if (false) {
            fileName = "javafx-18-ea+2.pom";
            groupId = "org.openjfx";
            artifactId = "javafx";
            version = "18-ea+2";
            packaging = "pom";
            repositoryId = "maven-releases";
            url = "http://127.0.0.1:8082/repository/maven-releases/";
        }
        if (false) {
            fileName = "javafx-18-ea+2.pom";
            groupId = "org.openjfx";
            artifactId = "javafx";
            version = "18-ea+2";
            packaging = "pom";
            repositoryId = "maven-snapshots";
            url = "http://127.0.0.1:8082/repository/maven-snapshots/";
        }

        if (true) {
            fileName = "org.eclipse.e4.core.services-2.0.100.jar";
            groupId = "org.eclipse.platform";
            artifactId = "org.eclipse.e4.core.services";
            version = "2.0.100";
            packaging = "jar";
            repositoryId = "maven-releases";
            url = "http://127.0.0.1:8082/repository/maven-releases/";
        }


        String cmd = "mvn deploy:deploy-file -Dfile=" + fileName + " -DgroupId=" + groupId + " -DartifactId=" + artifactId + " -Dversion=" + version + " -Dpackaging=" + packaging + " -DrepositoryId=" + repositoryId + " -Durl=" + url + "";
        System.out.println(cmd);

    }

    @Test
    public void findOutAllJar() {

        String repoPath = "G:\\maven\\repository_test";
        File file1 = new File(repoPath);
        //判断是否有目录
        if (file1.isDirectory()) {
            //获取目录中的所有文件名称
            String[] fileNames = file1.list();
            for (String filename : fileNames) {
                cycleDir(repoPath + "\\" + filename);
            }
        }
        buildFile();
    }

    private static void cycleDir(String Path) {

        File file1 = new File(Path);
        if (file1.isDirectory()) {
            String[] fileNames = file1.list();
            for (String filename : fileNames) {
                String childPath = Path + "\\" + filename;
                File childPathFile = new File(childPath);
                if (childPathFile.isFile()) {
                    fileHandle(childPathFile);
                } else {
                    cycleDir(childPathFile.getPath());
                }
            }
        } else if (file1.isFile()) {
            fileHandle(file1);
        }
    }

    private static void fileHandle(File path) {
        if (path.getName().endsWith(".jar")) {
            String allpath = path.getPath();
            String[] allpaths = allpath.split("\\\\");
            String version = allpaths[allpaths.length - 1 - 1];
            String artifactId = allpaths[allpaths.length - 1 - 2];
            String groupId = countGroupId(allpath);
            String jarFile = allpaths[allpaths.length - 1];
            System.out.println(allpath);
            System.out.println("jarFile:" + jarFile + "  " + "groupId:" + groupId + "  " + "artifactId:" + artifactId + "  " + "jarFile:" + jarFile + "  " + "version:" + version);
            privateMvnCmd(jarFile, groupId, artifactId, version);
            copyFile(path, "E:\\eclipse-projects\\datastudio300\\DataStudio\\dest-jar\\" + jarFile);
        }
    }

    private static String countGroupId(String allPath) {

        String[] paths = allPath.split("\\\\");
        StringBuilder sb = new StringBuilder();

        int i = 3;
        while (true) {
            if (i >= paths.length - 3) {
                break;
            }
            String ele = paths[i];
            if (StringUtils.isNotBlank(sb.toString())) {
                sb.append(".").append(ele);
            } else {
                sb.append(ele);
            }
            i++;
        }
        return sb.toString();
    }

    static List<String> cmdList = new ArrayList<String>();

    private static void privateMvnCmd(String fileName, String groupId, String artifactId, String version) {

        String packaging = "jar";
        String repositoryId = "maven-releases";
        String url = "http://127.0.0.1:8082/repository/maven-releases/";

        String cmd = "mvn deploy:deploy-file -Dfile=" + fileName + " -DgroupId=" + groupId + " -DartifactId=" + artifactId + " -Dversion=" + version + " -Dpackaging=" + packaging + " -DrepositoryId=" + repositoryId + " -Durl=" + url + ";";
        cmdList.add(cmd);
    }

    private static void buildFile() {

        String cmdFile = "E:\\eclipse-projects\\datastudio300\\DataStudio\\dest-jar\\" + System.currentTimeMillis() + "-cmd.sh";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(cmdFile));
            for (String cmdText : cmdList) {
                out.append(cmdText + "\n");
            }
            out.flush();
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
        }
    }

    private static void copyFile(File src, String dest) {
        try {
            FileUtils.copyFile(src, new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testMain() {
        try {
            FileUtils.copyFile(new File("E:\\eclipse-projects\\datastudio300\\DataStudio\\dest-jar\\11.txt"), new File("E:\\eclipse-projects\\datastudio300\\DataStudio\\dest-jar\\11.txt2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}


