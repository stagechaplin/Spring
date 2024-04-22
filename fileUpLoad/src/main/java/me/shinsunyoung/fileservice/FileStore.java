package me.shinsunyoung.fileservice;

import me.shinsunyoung.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;
    public String getFullPath(String filename) {return fileDir + filename;}
    public UploadFile storeFile(MultipartFile mFile)throws IOException{
        if(mFile.isEmpty()){
            return null;
        }
        String originalFilename = mFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        mFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }
    public List<UploadFile> storeFiles(List<MultipartFile> mFiles) throws  IOException{
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile mFile : mFiles) {
            if(!mFile.isEmpty()){
                uploadFiles.add(storeFile(mFile));
            }
        }
        return uploadFiles;
    }
    private String createStoreFileName(String originalFilename) {
            String uuid= UUID.randomUUID().toString();
            return uuid + "." + extractExt(originalFilename, uuid);
    }
    private String extractExt(String originalFileName, String uuid){
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos+1);
    }
}
