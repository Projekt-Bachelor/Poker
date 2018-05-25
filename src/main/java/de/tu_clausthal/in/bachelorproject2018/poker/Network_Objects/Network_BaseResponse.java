package de.tu_clausthal.in.bachelorproject2018.poker.Network_Objects;

public class Network_BaseResponse {

    private String status;
    private int code;

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
