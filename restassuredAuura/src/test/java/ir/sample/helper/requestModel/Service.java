package ir.sample.helper.requestModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
    private int type;
    private String mobileNumber;
    private String serial;
    private String mobileType;
    private int sms;
    private int gprs;
    private int mms;
    private int wap;
    private int threeG;
    private int fourG;
    private int videoCall;
    private String province;
    private int ipStatic;
    private String ip;
    private String subnet;
    private String resellerCode;
    private int apn;
    private int dataSim;
}
