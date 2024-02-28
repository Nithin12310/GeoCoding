package com.example.FindingNearestStore.service;

import com.example.FindingNearestStore.dto.FindStoreRequestDTO;
import com.example.FindingNearestStore.dto.StoreDTO;
import com.example.FindingNearestStore.model.CompanyStoreViews;
import com.example.FindingNearestStore.model.Store;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public interface StoreService {

    public Store addStoreDetails(StoreDTO storeDTO) throws BadRequestException;

    public List<CompanyStoreViews> findStore(FindStoreRequestDTO findStoreRequestDTO);

    public byte[] getApiKey(HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException;
}
