package com.ahsan.file.app.controller;

import com.ahsan.file.app.data.model.DeleteFileDTO;
import com.ahsan.file.app.data.model.FileDTO;
import com.ahsan.file.app.model.response.*;
import com.ahsan.file.app.model.resquest.FileDownloadRequest;
import com.ahsan.file.app.repository.FileRepository;
import com.ahsan.file.app.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/{link}")
    public FileInfo getFile(@PathVariable String link){
        FileDTO fileDTO = fileService.getFile(link);
        FileInfo response = new FileInfo();

        BeanUtils.copyProperties(fileDTO, response);
        String fileDownloadUri = fileDTO.getFileCheckSum().getDownloadlink();
        response.setDownloadLink(fileDownloadUri);
        return response;
    }

    @PostMapping("/download")
    public FileInfoData dowloadFile(@RequestBody FileDownloadRequest fileDownloadRequest){
        FileInfoData fileInfoData = new FileInfoData();

        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(fileDownloadRequest, fileDTO);

        FileDTO file = fileService.getFile(fileDTO);

        BeanUtils.copyProperties(file, fileInfoData);


        return fileInfoData;
    }

     @GetMapping("/download/{link}")
     public ResponseEntity<Resource> downloadFile(@PathVariable String link){
          System.out.println(link);
          FileDTO fileDTO = fileService.getFile(link);
//          Sample sample = new Sample();
//          sample.setString(link);
         return ResponseEntity.ok()
                 .contentType(MediaType.parseMediaType(fileDTO.getFileType()))
                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getName() + "\"")
                 .body(new ByteArrayResource(fileDTO.getData()));
     }

     @PostMapping("/upload")
     public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file){

          FileDTO fileDTO = fileService.storeFile(file);
          FileUploadResponse response = new FileUploadResponse();

          BeanUtils.copyProperties(fileDTO, response);
          String fileDownloadUri = fileDTO.getFileCheckSum().getDownloadlink();
          response.setDownloadLink(fileDownloadUri);

          return ResponseEntity.ok(response);
     }



     @GetMapping
     public List<FileInfo> getList(){
         List<FileDTO> list = fileService.getFileList();
         List<FileInfo> returnList = new ArrayList<>();

         for(FileDTO fd: list) {
             FileInfo fileInfo = new FileInfo();
             BeanUtils.copyProperties(fd, fileInfo);
             returnList.add(fileInfo);
         }
         return returnList;
     }



     @PostMapping("/block/{id}")
     public void blockFile(long id){

     }

     @DeleteMapping("/delete/{id}")
     public DeleteFileResponse deleteFile(@PathVariable Long id){
         System.out.println(id);
         DeleteFileResponse deleteFileResponse = new DeleteFileResponse();
         DeleteFileDTO deleteDTO = fileService.deleteFile(id);
         BeanUtils.copyProperties(deleteDTO, deleteFileResponse);
         return deleteFileResponse;
     }

}

