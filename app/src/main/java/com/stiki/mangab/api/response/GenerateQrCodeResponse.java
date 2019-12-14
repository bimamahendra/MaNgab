package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenerateQrCodeResponse extends BaseResponse {
    @SerializedName("id_absen")
    public String idAbsen;

    @SerializedName("qr_code")
    public String qrCode;

    @SerializedName("data_mhs")
    public List<MhsData> dataMhs;

    static class MhsData {
        @SerializedName("nama")
        public String nama;

        @SerializedName("nrp")
        public String nrp;

        @SerializedName("email")
        public String email;

        @SerializedName("status_absen")
        public String statusAbsen;
    }
}
