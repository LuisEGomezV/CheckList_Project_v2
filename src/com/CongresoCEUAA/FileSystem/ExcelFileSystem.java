package com.CongresoCEUAA.FileSystem;

import org.apache.commons.io.FilenameUtils;

public abstract class ExcelFileSystem
{
    static String VerifyPath(String fullPath)
    {
        String ext  = FilenameUtils.getExtension(fullPath);
        String name  = FilenameUtils.getBaseName(fullPath);
        String path  = FilenameUtils.getPath(fullPath);

        //System.out.println("Not verified path: " + fullPath);

        //System.out.println("Ext: " + ext);
        //System.out.println("name: " + name);
        //System.out.println("Path: " + path);
        String verifiedPath;
        if(ext != "xlsx")
            verifiedPath = fullPath.replace("." + ext, ".xlsx");
        else
            verifiedPath = fullPath;



        //System.out.println("VerifiedPath:      " + verifiedPath);


        return verifiedPath;
    }
}
