package com.sc200.controller;


import com.sc200.domain.Directory;
import com.sc200.domain.Files;
import com.sc200.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/create")
    public String createDirectoryLayer(@RequestBody @Valid Files file) throws IOException {

            try {
                System.out.println(file.toString());
                String a = fileService.parseFile(file);
                return a;
            }
            catch(Exception e)
            {
                return e.getMessage();
            }

    }
    @PostMapping()
    public ResponseEntity<?> getDirectoryStructure(@RequestBody @Valid String folderName) throws  IOException{

        ResponseEntity responseEntity;

        try{

            fileService.setPathsAndContent(new File(folderName));
            Directory directory = new Directory(fileService.getPaths(),fileService.getContents());

            ArrayList<String> temp = directory.getContents();
            for(int i=0;i<temp.size();i++){
                if(temp.get(i)==null){
                    temp.remove(i);
                }
            }

            ArrayList<String> temp1 = directory.getPaths();
            for(int i=0;i<temp1.size();i++){
                if(temp1.get(i)==null){
                    temp1.remove(i);
                }
            }
           Directory directory1 = new Directory(temp1,temp);
            System.out.println(directory1.toString());


            responseEntity = new ResponseEntity<Directory>(directory1 , HttpStatus.OK);
        }
        catch (Exception e){

            fileService.setPathsAndContent(new File(folderName));
            Directory directory = new Directory(fileService.getPaths(),fileService.getContents());
            responseEntity = new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }

        return  responseEntity;
    }



<<<<<<< HEAD
=======
    @PostMapping()
    public String getTemplateFromBackend(@RequestBody @Valid String path) throws IOException {

        try {
            System.out.println("hello");
            String[] a = fileService.getTemplate(path);
            return a.toString();
        }
        catch(Exception e)
        {
            return e.getMessage();
        }

    }

  
>>>>>>> b735ce70bf6a299feb46fcedea123055527c44c8
}
