package com.vi.demo;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo3 {

    static List<String> cmdList = new ArrayList<String>();

    @Test
    public void findOutAllJar() {

        try {
            String repoPath = "E:\\eclipse-projects\\datastudio300\\DataStudio\\3rd_src\\pom.xml";
            File file1 = new File(repoPath);

            InputStreamReader Reader = new InputStreamReader(new FileInputStream(file1), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(Reader);
            String lineTxt = null;
            String groupId = "";
            String artifactId = "";
            String version = "";
            while ((lineTxt = bufferedReader.readLine()) != null) {
                //buffereReader.readLine()按行读取写成字符串
                lineTxt = lineTxt.trim();
                if (lineTxt.trim().startsWith("<dependency>")) {
                    groupId = "";
                    artifactId = "";
                    version = "";
                }
                if (lineTxt.trim().startsWith("<groupId>")) {
                    lineTxt = lineTxt.replace("<groupId>", "");
                    lineTxt = lineTxt.replace("</groupId>", "");
                    groupId = lineTxt;
                }
                if (lineTxt.trim().startsWith("<artifactId>")) {
                    lineTxt = lineTxt.replace("<artifactId>", "");
                    lineTxt = lineTxt.replace("</artifactId>", "");
                    artifactId = lineTxt;
                }
                if (lineTxt.trim().startsWith("<version>")) {
                    lineTxt = lineTxt.replace("<version>", "");
                    lineTxt = lineTxt.replace("</version>", "");
                    version = lineTxt;
                }
                if (lineTxt.trim().startsWith("</dependency>")) {
                    System.out.println("groupId:" + groupId + " " + "artifactId:" + artifactId + " " + "version:" + version);
                    String repositoryId = "maven-releases";
                    String url = "http://127.0.0.1:8082/repository/maven-releases/";

                    String fileName = artifactId + "-" + version + ".jar";
                    String cmd = "mvn deploy:deploy-file -Dfile=" + fileName + " -DgroupId=" + groupId + " -DartifactId=" + artifactId + " -Dversion=" + version + " -Dpackaging=jar" + " -DrepositoryId=" + repositoryId + " -Durl=" + url + ";";
                    cmdList.add(cmd);
                }
            }
            Reader.close();
            buildFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void buildFile() {

        String cmdFile = "E:\\eclipse-projects\\datastudio300\\DataStudio\\target-jar\\" + System.currentTimeMillis() + "-cmd.sh";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(cmdFile));
            for (String cmdText : cmdList) {
                out.append(cmdText + "\n");
            }
            out.flush();
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
