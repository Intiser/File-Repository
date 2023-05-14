package com.ahsan.file.app.service;

import com.ahsan.file.app.data.model.RequestDTO;

import java.util.List;

public interface RequestService {
    List<RequestDTO> getAllRequests();
    void resolveRequest();
    RequestDTO createRequest(RequestDTO requestDTO);

    RequestDTO updateValueResolved(RequestDTO requestDTO);

    RequestDTO blockFile(RequestDTO requestDTO);
    RequestDTO unblockFile(RequestDTO requestDTO);
}
