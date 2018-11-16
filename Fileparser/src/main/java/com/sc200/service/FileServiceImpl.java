package com.sc200.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class FileServiceImpl implements FileService {

    private int i;

    public String parseFile(com.sc200.domain.File file) throws IOException {
        int lastIndex = file.getPath().lastIndexOf('/');
        String directory = file.getPath().substring(0,lastIndex);

        if(createDirectories(directory) && createFile(file)) {
            return "Successfully Created";
        }
        else {
            return "Directory already exists";
        }


    }

    public boolean createDirectories(String path) {

        //create layers of directories

        File files = new File(path);
        if(!files.exists()) {
            if(files.mkdirs()) {
                return true;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean createFile(com.sc200.domain.File file) throws IOException {

        //Create the file
        File newFile = new File(file.getPath());
        if (newFile.createNewFile())
        {
            FileWriter writer = new FileWriter(newFile);
            writer.write(file.getInput());
            writer.close();
            return true;
        } else {
            return false;
        }

    }

    public String createTree() {
        String[] strings = {"a/b/d/c/HelloWorld.java" , "c/HelloWorld.java" , "a/d/e/HelloWorld.java" };

        Directory directory = new Directory( "root" , new ArrayList<>());

        Directory parent = directory;
        Directory oldParent = directory;
        ArrayList<Directory> allCurrent = parent.value;
        //        Directory current = allCurrent.get(0);
        Directory toBeAdded;
        String parentString = new String();
        String directoryString = new String();
        String[] subStrings1 = strings[0].split("/");
        toBeAdded = PathToTree(subStrings1);
        directory.value.add(toBeAdded);
//        System.out.println(directory.toString());
        for(int j = 1; j<strings.length;j++)
        {

            parent = directory;
            String[] subStrings = strings[j].split("/");
//            System.out.println(strings[j]);
            toBeAdded = PathToTree(subStrings);
            boolean a = true;
            while(a){
//                System.out.println("11");
                for(int k=0;k<allCurrent.size();k++)
                {
//                    System.out.println(k);
//                    System.out.println("first"+allCurrent.get(k).key+"in end");
//                    System.out.println("second"+toBeAdded.key+"in end");
                    if(allCurrent.get(k).key.equals(toBeAdded.key))
                    {
//                        System.out.println("hello");
                        oldParent = parent;
                        parent = allCurrent.get(k);
                        allCurrent = parent.value;
//                        System.out.println(parent.key);
//                        System.out.println(toBeAdded.toString() + "ssjn");
                        toBeAdded = toBeAdded.value.get(0);
//                        System.out.println(toBeAdded.toString());
                        //   directory.value.add(parent);
                        break;
                    }
                    else
                    {

//                        System.out.println("third:"+toBeAdded.toString() + "in else");
                        parent.value.add(toBeAdded);
//                        System.out.println("Directory inside loop : " + directory.value.get(1));
//                        System.out.println("Parent inside loop : " + parent.value.get(1));
                        a=false;
//                        System.out.println(directory.toString() + "1111");

//                        System.out.println(parent.toString() );

                        break;

                    }

                }
//                System.out.println("loop ended");

//                System.out.println(directory.toString() + "3");
//                if(parent == directory)
//                {
//                    parent.value.add(toBeAdded);
//                }
            }
            parentString = parent.toString();
            directoryString = directory.toString();
//            System.out.println("Parent : " + parent.toString());
//            System.out.println("Directory : " + directory.toString());
//            directory.value.add(parent);
//            System.out.println("");
            directoryString.replace(oldParent.toString() , parentString);

        }


//        Gson gson = new Gson();
//        String json = gson.toJson(directory);
//        System.out.println("ended");
        //


//        System.out.println(directoryString );
//        Gson gson = new Gson();
//        String json = gson.toJson(directory);
//        System.out.println(json);

        directoryString = "{'" + directoryString;
        directoryString = directoryString.replaceAll("\\[", "':{'");
        directoryString = directoryString.replaceAll("]" , "'}'");
        directoryString = directoryString.replaceAll(" :" , "' : '");
        directoryString = directoryString.replaceAll("''" , "");
        directoryString = directoryString.replaceAll(", " , ", '");
        directoryString = directoryString.replaceAll("}}'" , "}}");
        directoryString = directoryString.replaceAll("' null'" , "null");
        directoryString = directoryString.substring(0,directoryString.length()-2);

        int leftbraces = 0;
        int rightbraces = 0;

        for(int l=0;l<directoryString.length();l++)
        {
            if(directoryString.charAt(l)=='{')
            {
                leftbraces++;
            }
            else if(directoryString.charAt(l)=='}')
            {
                rightbraces++;
            }
        }

        for(int l=0;l<leftbraces-rightbraces;l++){
            directoryString = directoryString + "}";
        }

        System.out.println(directoryString);



        return directoryString;
    }



    public Directory PathToTree(String[] subString) {


        this.i = subString.length;
        Directory prevNode = new Directory(subString[subString.length-1] , null);

        for (int i = subString.length - 2; i >= 0; i--)
        {
//            System.out.println(subString[i]);
            Directory node = new Directory(subString[i], new ArrayList<>());
            node.value.add(prevNode);
            prevNode = node;
        }

        return prevNode;
    }



    public static class Directory {

        String key;
        ArrayList<Directory> value;

        public Directory(String key , ArrayList<Directory> value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            if(this.value==null)
            {
                return this.key + " : "  + this.value;
            }
            String a = "";
            a = a + this.key + this.value.toString() ;
            return a;
        }

    }

    public  String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }

}

