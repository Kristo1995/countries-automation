package util;

import lombok.Data;

@Data
public class ExpectedResponse {

    private int statusCode;
    private Object body;
}
