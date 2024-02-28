package com.example.FindingNearestStore.service.implementation;

import com.example.FindingNearestStore.dto.FindStoreRequestDTO;
import com.example.FindingNearestStore.dto.StoreDTO;
import com.example.FindingNearestStore.model.CompanyStoreViews;
import com.example.FindingNearestStore.model.Store;
import com.example.FindingNearestStore.repository.CompanyRepository;
import com.example.FindingNearestStore.repository.CompanyStoreViewRepository;
import com.example.FindingNearestStore.repository.StoreRepository;
import com.example.FindingNearestStore.service.JWTService;
import com.example.FindingNearestStore.service.StoreService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class StoreServiceImplementation implements StoreService {

    Log LOG = LogFactory.getLog(StoreService.class);
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JWTService jwtService;

    @Autowired
    CompanyStoreViewRepository companyStoreViewRepository;


    @Override
    public Store addStoreDetails(StoreDTO storeDTO) throws BadRequestException {


        System.out.println(storeDTO);
        Store savedStoreDetails = null;


        Store store = modelMapper.map(storeDTO, Store.class);
        savedStoreDetails = storeRepository.save(store);
        return savedStoreDetails;

    }

    @Override
    public List<CompanyStoreViews> findStore(FindStoreRequestDTO findStoreRequestDTO) {
        System.out.println("hi findStore");
        List<CompanyStoreViews> stores = companyStoreViewRepository.findStore(findStoreRequestDTO.getLatitude(), findStoreRequestDTO.getLongitude(), findStoreRequestDTO.getDistance(), findStoreRequestDTO.getCompanyId());
        LOG.info("storelist:" + stores);
        return stores;
    }


    public byte[] getApiKey(HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {

        String jwtToken = httpServletRequest.getHeader("Authorization");
        jwtToken = jwtToken.substring(7);
        Claims claims = jwtService.extractAllClaims(jwtToken);
        String subscriptionId = claims.get("subscriptionId").toString();
        System.out.println(subscriptionId);
        Date Expiry = claims.getExpiration();
        System.out.println(Expiry);
        byte[] message = ("" + subscriptionId + "_" + Expiry).getBytes();
        System.out.println("message: " + message);


        String privateKey = "MIICWwIBAAKBgQCXCItnBNE12NnZ8tlYtVxDohFbDjClUYwypX4HL+upBoii72ugK7bRZOlpWZjeB7lcGKAhXDunrzlLMwLikRgOblJsF5lkDFg54cgC4VxOcmczF/we47tELZoGlks8vNgfQoDX8E9rNfeS0Hp2Ll9sD6hHNtegZwEQlijZsCAIyQIDAQABAoGAatWadjbMHkDUaP0FjWBMcXL0v+obsvUqLLEvorndAuUiRc8B3HzPhvLQwLqmDXh9P44Tx0VM1WJ3LqBYzBdCYeV6styPFGq10kWYcEoKJVFtYaDEZYBu7AqTv81x4g53/sJ1xWhuKIGM/PNk/eavBCZXjNJU9a0rBvLFPlm9wWECQQD2u8Kv98FwRXhZ3/6alQXDrfCMO38QuSDo4QNtQa7EQ4krd7+kfk458yJm7cfmHob9zLsa6GeedbqeIsxeYq+tAkEAnLSq7tw+LB0y2bhLTQb4TZsO+nkkmK4b1plvH+IYbDRtH505ZTXjkNgxMQvZz3Gj+iWScDGqVLpc0UoikxExDQJAdccjJESsPh+rV5upObkxSztYvFgaS61TnFbs/9Z+yolCjVFYVflE9jyti1yuM5cW8UgteKa/79BbCOmrjNKm8QJAXxRvskYHoxSESf28EpxsBApU6Dygq9LYKTknZ5zzXJPisg/fRz2POc8y6aNbILa+24P4egJoasg4kPP+TmVb8QJAPBNIDbIzdkfWgR5APwmMYW7CNOB5HmARTf1ixmgxM1tuptWGIktum+/CQLZ8EP6mUEVNjt5Wd7/AbH3m/+pfMA==";
        String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXCItnBNE12NnZ8tlYtVxDohFbDjClUYwypX4HL+upBoii72ugK7bRZOlpWZjeB7lcGKAhXDunrzlLMwLikRgOblJsF5lkDFg54cgC4VxOcmczF/we47tELZoGlks8vNgfQoDX8E9rNfeS0Hp2Ll9sD6hHNtegZwEQlijZsCAIyQIDAQAB";


        byte[] publicByte = Base64.getDecoder().decode(publickey.getBytes());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicByte);
        PublicKey publicKey = null;
        KeyFactory keyFactory = null;

        keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(spec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(cipher.ENCRYPT_MODE, publicKey);
        cipher.update(message);
        byte[] cipherText = cipher.doFinal();
//        log.info(cipherText.toString());
        System.out.println("cipher text " + cipherText);
        return cipherText;
    }
}