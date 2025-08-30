package ir.sample.helper.requestModel;

import lombok.Data;

@Data
public class ShahkarResponseBody {
    private String result;
    private String requestId;
    private Integer response;
    private String comment;
    private String id;
}
