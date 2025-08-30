package ir.sample.helper.requestModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    private String name;
    private String family;
    private String fatherName;
    private String identificationNo;
    private int identificationType;
    private String birthDate;
    private String birthCertificateNo;
    private String mobile;
}
