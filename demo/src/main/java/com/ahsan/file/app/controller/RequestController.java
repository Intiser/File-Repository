package com.ahsan.file.app.controller;

import com.ahsan.file.app.data.model.FileDTO;
import com.ahsan.file.app.data.model.RequestDTO;
import com.ahsan.file.app.model.response.FileInfo;
import com.ahsan.file.app.model.response.RequestInfo;
import com.ahsan.file.app.model.resquest.FileRelatedRequest;
import com.ahsan.file.app.service.RequestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("requests")
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping
    List<RequestInfo> getAllRequests(){
        List<RequestDTO> list   = requestService.getAllRequests();
        List<RequestInfo> returnList = new ArrayList<>();
        for(RequestDTO requestDTO: list){
            RequestInfo requestInfo = new RequestInfo();
            BeanUtils.copyProperties(requestDTO, requestInfo);

            if(requestDTO.getFile() != null){
                FileInfo fileInfo = new FileInfo();
                BeanUtils.copyProperties(requestDTO.getFile(), fileInfo);
                requestInfo.setFile(fileInfo);
            }

            returnList.add(requestInfo);
            System.out.println(requestDTO.toString());
        }
        return returnList;
    }

    @PutMapping("/block")
    RequestInfo blockRequest(@RequestBody FileRelatedRequest request){
        RequestInfo requestInfo = new RequestInfo();
        RequestDTO requestDTO = new RequestDTO();

        BeanUtils.copyProperties(request, requestDTO);
        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(request.getFile(), fileDTO);
        requestDTO.setFile(fileDTO);

        RequestDTO response = requestService.blockFile(requestDTO);

        BeanUtils.copyProperties(response, requestInfo);
        return requestInfo;
    }

    @PutMapping("/unblock")
    RequestInfo unblockRequest(@RequestBody FileRelatedRequest request){
        RequestInfo requestInfo = new RequestInfo();
        RequestDTO requestDTO = new RequestDTO();

        BeanUtils.copyProperties(request, requestDTO);
        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(request.getFile(), fileDTO);
        requestDTO.setFile(fileDTO);

        RequestDTO response = requestService.unblockFile(requestDTO);

        BeanUtils.copyProperties(response, requestInfo);
        return requestInfo;
    }

    @PutMapping("/decline")
    RequestInfo declineRequest(@RequestBody FileRelatedRequest request){
        RequestInfo requestInfo = new RequestInfo();
        RequestDTO requestDTO = new RequestDTO();
        BeanUtils.copyProperties(request, requestDTO);

        RequestDTO response = requestService.updateValueResolved(requestDTO);

        BeanUtils.copyProperties(response, requestInfo);

        return requestInfo;
    }


    @PostMapping
    RequestInfo createRequest(@RequestBody FileRelatedRequest request){
        System.out.println(request.toString());
        RequestInfo requestInfo = new RequestInfo();

        RequestDTO requestDTO = new RequestDTO();
        BeanUtils.copyProperties(request, requestDTO);

        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(request.getFile(), fileDTO);
        requestDTO.setFile(fileDTO);


        RequestDTO created = requestService.createRequest(requestDTO);

        BeanUtils.copyProperties(created, requestInfo);
        return requestInfo;
    }

}
