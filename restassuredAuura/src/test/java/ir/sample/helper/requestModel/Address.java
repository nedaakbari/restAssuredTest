package ir.sample.helper.requestModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String address;
    private String postalCode;
    private String tel;
    private String provinceName;
    private String houseNumber;
    private String street2;
    private String townshipName;
}