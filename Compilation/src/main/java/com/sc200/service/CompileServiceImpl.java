package com.sc200.service;

import com.sc200.domain.File;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Service
public class CompileServiceImpl implements CompileService {


    FileParserService fileParserService;

    public CompileServiceImpl(FileParserService fileParserService){this.fileParserService = fileParserService;}


    @Override
    public ArrayList<String> runFile(File file) throws IOException {

        ArrayList<String> lines = new ArrayList<String>();
        int i=0;

        try{
            System.out.println("start excution......");
            String relativePath =  this.fileParserService.findRelativePath(file);
            String fileName =  this.fileParserService.findFileName(file);
            System.out.println(relativePath);
            System.out.println(fileName);
            String[] command = {"/bin/bash", "/data-docker/sc200/Compilation/src/main/resources/shellScript.sh", relativePath , fileName};
            ProcessBuilder p = new ProcessBuilder(command);
            Process p2 = p.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {

                System.out.println(line);
                lines.add(line);
                i++;
                
            }
            System.out.println("ending excution......");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return lines;
    }
    @Override
    public void clone(String url) throws IOException{
        try{
            System.out.println("started execution");
            System.out.println("github url: "+url);
            String[] command = {"/bin/bash/","/home/cgi/sc200/Compilation/src/main/resources/clonescript.sh",url};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            System.out.println("cloned the repository");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
