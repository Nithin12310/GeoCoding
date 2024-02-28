package com.example.FindingNearestStore.model;

import com.example.FindingNearestStore.dto.Address;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Company extends Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String companyId;
    private String companyName;
    private String password;
    private String descryption;
    private String contactNumber;
    private String emailId;
    private String gstNumber;
    private String tenant;




}
