package me.shinsunyoung.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/file1")
    public String newFile() {
        return "upload_form";
    }
    @PostMapping("/file1")
//    DispatcherServlet에서 MultipartResolver를 실행 하여 request를 아래와 같이 받게 해줌
    public String saveFileV1(@RequestParam String itemName,
                             @RequestParam MultipartFile file,
                             //MultipartHttpServletRequest mRequest,
                             HttpServletRequest mRequest) throws IOException{
        log.info("1. itemName = {}", itemName);
        log.info("2. mFile = {}", file);
        String filename = file.getOriginalFilename();
        log.info(filename.equals("")? "3. 첨부안함" : "3. 첨부파일:" + filename);
        if(!file.isEmpty()){
            String fullPath = fileDir + filename;
            if(new File(fullPath).exists()){
                fullPath = fileDir + UUID.randomUUID().toString() + filename;
            }
            file.transferTo(new File(fullPath));
            log.info("5. 파일 저장 fullPath = {}",fullPath);
        }
        return "upload_form";
    }
    @GetMapping("/file2")
    public String newFile2(){
        return "upload_form2";
    }
    public String saveFile(@RequestParam String itemName,
                           MultipartRequest mRequest) throws IOException{
        Iterator<String> params = mRequest.getFileNames();
        int i = 1;
        while(params.hasNext()) {
            String param = params.next();
            log.info("= = = {}번째 파라미터 이름 : {} = = =", i , param);
            MultipartFile mFile = mRequest.getFile(param);
            String filename = mFile.getOriginalFilename();
            log.info(filename.equals("")? "첨부안해서 빈 스트링" : "첨부한 파일 이름은"+filename);

            String fullPath = fileDir + filename;
            if(filename != null && !filename.equals("")){
                if(new File(fullPath).exists()){
                    fullPath = fileDir + System.currentTimeMillis() + filename;
                }
                mFile.transferTo(new File(fullPath));
            }
            i++;
        }
        return  "upload_form2";
    }
    public String saveFileV1(MultipartHttpServletRequest mRequest,
                             HttpServletRequest request)throws IOException {
        log.info("1. request = {}", request);
        log.info("2. mrequest = {}", mRequest);
        log.info("3. itemName = {}", mRequest.getParameter("itemName"));
        Iterator<String> params = mRequest.getFileNames();
        String param = params.next();
        log.info("4.파라미더 이름 = {}", param);
        MultipartFile mFile = mRequest.getFile(param);
        String filename = mFile.getOriginalFilename();
        log.info(filename.equals("") ? "5. 첨부안함" : "5. 첨부파일" + filename);

        if (!filename.equals("")) {
            String fullPath = fileDir + filename;
            if (new File(fullPath).exists()) {
                fullPath = fileDir + System.currentTimeMillis() + filename;
            }
            mFile.transferTo(new File(fullPath));
            log.info("6. 파일 저장 fullPath={}", fullPath);
        }
            return "upload_form";
    }
    @PostMapping("/file2")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file1,
                           @RequestParam MultipartFile file2) throws IOException{
        if(!file1.isEmpty()) {
            String fullPath1 = fileDir + file1.getOriginalFilename();
            log.info("1. 첨부한 파일에 따라 저장할 예정 = {}",fullPath1);
            if(new File(fullPath1).exists()){
                fullPath1 = fileDir + UUID.randomUUID().toString() + file1.getOriginalFilename();
            }
            file1.transferTo(new File(fullPath1));
            log.info("1. 실제 저장한 파일 fullPath={}",fullPath1);
        }else{
            log.info("1. 파일 첨부 안함");
        }
        if(!file2.isEmpty()){
            String fullPath2 = fileDir + file2.getOriginalFilename();
            log.info("2. 첨부한 파일에 따라 저장할 예정 = {}", fullPath2);
            if(new File(fullPath2).exists()){
                fullPath2 = fileDir + UUID.randomUUID().toString() + file2.getOriginalFilename();
            }
            file2.transferTo(new File(fullPath2));
            log.info("2.실제 저장한 파일 fullPath = {}",fullPath2);
        }else{log.info("2. 파일 첨부 안 함");
        }
        return "upload_form2";
    }
}
