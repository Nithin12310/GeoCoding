package com.example.FindingNearestStore.model;

import com.example.FindingNearestStore.dto.Address;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Store extends Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String storeId;
    private String companyId;
    private String storeName;
    private String contactNumber;
    private BigDecimal  latitude;
    private BigDecimal longitude;

}
