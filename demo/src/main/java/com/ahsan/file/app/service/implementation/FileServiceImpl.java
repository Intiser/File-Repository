package com.ahsan.file.app.service.implementation;

import com.ahsan.file.app.data.entity.FileCheckSum;
import com.ahsan.file.app.data.entity.FileEntity;
import com.ahsan.file.app.data.model.DeleteFileDTO;
import com.ahsan.file.app.data.model.FileDTO;
import com.ahsan.file.app.repository.FileCheckSumRepository;
import com.ahsan.file.app.repository.FileRepository;
import com.ahsan.file.app.repository.file.FileInfoDB;
import com.ahsan.file.app.service.FileService;
import com.ahsan.file.app.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FileCheckSumRepository fileCheckSumRepository;

    @Autowired
    Utils utils;

    @Override
    public FileDTO storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("Sorry! Filename contains invalid path sequence " + fileName)) {
                throw new RuntimeException();
            }


            FileEntity dbFile = new FileEntity();
            dbFile.setName(fileName);
            dbFile.setData(file.getBytes());
            dbFile.setSize(file.getSize());
            dbFile.setFileType(file.getContentType());
            dbFile.setDateTime(Utils.getCurrentDat());
            dbFile.setOld(new byte[1]);

            FileCheckSum fileCheckSum =  new FileCheckSum();
            fileCheckSum.setCheckksum(Utils.checksum(file.getBytes()));
            fileCheckSum.setDownloaduuid(Utils.getUniquesId());
            fileCheckSum.setDownloadlink(Utils.getDownnloadLink(fileCheckSum.getDownloaduuid()));


            fileCheckSum = fileCheckSumRepository.save(fileCheckSum);
            dbFile.setFileCheckSum(fileCheckSum);

            FileEntity fileEntity =  fileRepository.save(dbFile);
            FileDTO returnFile = new FileDTO();
            BeanUtils.copyProperties(fileEntity, returnFile);

            return returnFile;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FileDTO> getFileList() {
        List<FileDTO> returnValue = new ArrayList<>();
        List<FileInfoDB> list = fileRepository.findAllBy();
        for(FileInfoDB fid: list){
            FileDTO fd = new FileDTO();
            BeanUtils.copyProperties(fid, fd);
            returnValue.add(fd);
            System.out.println(fid.getName());
            System.out.println(fd);
        }
        return returnValue;
    }

    @Override
    public FileDTO getFile(String link) {
        FileCheckSum fileCheckSum =  fileCheckSumRepository.findByDownloadlink(Utils.getDownLink(link));
        FileEntity fileEntity = fileRepository.findByFileCheckSum(fileCheckSum);

        System.out.println(fileCheckSum.toString());
        System.out.println(fileEntity.toString());

        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(fileEntity, fileDTO);

        return fileDTO;
    }

    @Override
    public FileDTO getFile(FileDTO fileDTO) {
        FileEntity fileEntity = fileRepository.findById(fileDTO.getId());
        FileDTO returnData = new FileDTO();
        BeanUtils.copyProperties(fileEntity, returnData);
        return returnData;
    }

    @Override
    public DeleteFileDTO deleteFile(long id) {
        fileRepository.deleteById(id);
        DeleteFileDTO dto = new DeleteFileDTO();
        dto.setId(id);
        return dto;
    }
}
