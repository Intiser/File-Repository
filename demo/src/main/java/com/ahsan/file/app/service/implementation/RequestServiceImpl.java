package com.ahsan.file.app.service.implementation;

import com.ahsan.file.app.data.entity.FileCheckSum;
import com.ahsan.file.app.data.entity.FileEntity;
import com.ahsan.file.app.data.entity.RequestEntity;
import com.ahsan.file.app.data.model.FileDTO;
import com.ahsan.file.app.data.model.RequestDTO;
import com.ahsan.file.app.repository.FileCheckSumRepository;
import com.ahsan.file.app.repository.FileRepository;
import com.ahsan.file.app.repository.RequestRepository;
import com.ahsan.file.app.repository.file.FileInfoDB;
import com.ahsan.file.app.service.RequestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    FileCheckSumRepository fileCheckSumRepository;

    @Autowired
    FileRepository fileRepository;

    @Override
    public List<RequestDTO> getAllRequests() {
        List<RequestEntity> list = requestRepository.findByIsResolvedIsFalse();
        List<RequestDTO> returnList = new ArrayList<>();

        for(RequestEntity entity: list){
            RequestDTO requestDTO = new RequestDTO();
            BeanUtils.copyProperties(entity, requestDTO);
            if(entity.getFileEntity() != null){
                FileDTO fileDTO = new FileDTO();
                BeanUtils.copyProperties(entity.getFileEntity(), fileDTO);
                requestDTO.setFile(fileDTO);
            }

            returnList.add(requestDTO);
            //System.out.println(entity.toString());
        }
        return returnList;
    }

    @Override
    public void resolveRequest() {

    }

    @Override
    public RequestDTO createRequest(RequestDTO requestDTO) {
        //System.out.println(requestDTO);
        RequestEntity request = new RequestEntity();
        BeanUtils.copyProperties(requestDTO, request);
        request.setIsResolved(false);

        FileEntity fileEntity = new FileEntity();
        BeanUtils.copyProperties(requestDTO.getFile(), fileEntity);
        request.setFileEntity(fileEntity);

        RequestEntity saved = requestRepository.save(request);
        RequestDTO ret = new RequestDTO();
        BeanUtils.copyProperties(saved, ret);
        return ret;
    }

    @Override
    public RequestDTO updateValueResolved(RequestDTO requestDTO) {
        RequestEntity request = new RequestEntity();
        BeanUtils.copyProperties(requestDTO, request);
        request.setIsResolved(true);
        RequestEntity saved = requestRepository.save(request);
        RequestDTO ret = new RequestDTO();
        BeanUtils.copyProperties(saved, ret);
        return ret;
    }

    @Override
    public RequestDTO blockFile(RequestDTO requestDTO) {
        RequestEntity request = new RequestEntity();
        BeanUtils.copyProperties(requestDTO, request);

        request.setIsResolved(true);

        FileEntity fileEntity =  fileRepository.findById(requestDTO.getFile().getId());
        fileEntity.setBlocked(true);
        fileRepository.save(fileEntity);

        FileCheckSum fileCheckSum = fileEntity.getFileCheckSum();
        fileCheckSum.setBlocked(true);
        fileCheckSumRepository.save(fileCheckSum);

        request.setFileEntity(fileEntity);
        RequestEntity saved = requestRepository.save(request);
        RequestDTO ret = new RequestDTO();
        BeanUtils.copyProperties(saved, ret);
        return ret;
    }

    @Override
    public RequestDTO unblockFile(RequestDTO requestDTO) {
        RequestEntity request = new RequestEntity();
        BeanUtils.copyProperties(requestDTO, request);
        request.setIsResolved(true);

        FileEntity fileEntity =  fileRepository.findById(requestDTO.getFile().getId());
        fileEntity.setBlocked(false);
        fileRepository.save(fileEntity);

        FileCheckSum fileCheckSum = fileEntity.getFileCheckSum();
        fileCheckSum.setBlocked(false);
        fileCheckSumRepository.save(fileCheckSum);

        request.setFileEntity(fileEntity);
        RequestEntity saved = requestRepository.save(request);
        RequestDTO ret = new RequestDTO();
        BeanUtils.copyProperties(saved, ret);
        return ret;
    }
}
