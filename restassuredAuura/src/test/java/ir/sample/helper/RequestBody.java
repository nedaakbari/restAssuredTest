package ir.sample.helper;

import helper.requestModel.Address;
import helper.requestModel.Service;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestBody {
    private String requestId;
    private String name;
    private String family;
    private String fatherName;
    private String certificateNo;
    private String birthDate;
    private String mobile;
    private String email;
    private int gender;
    private String identificationNo;
    private int identificationType;
    private String resellerCode;
    private int iranian;
    private int person;
    private Address address;
    private Service service;
}