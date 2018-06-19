package de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects.BasicTestFiles;

import javax.annotation.Nonnull;

public class Network_BaseResponse {

    private String status;
    private int code;

    public Network_BaseResponse(@Nonnull final String status, final int code){
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
