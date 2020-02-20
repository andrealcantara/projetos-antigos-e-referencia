package br.com.devtree.error;

public class CustomErrorType {
    private String errorMsg;

    public CustomErrorType(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
