package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GenerateQrCodeResponse extends BaseResponse implements Serializable {
    @SerializedName("id_absen")
    public String idAbsen;

    @SerializedName("qr_code")
    public String qrCode;

    @SerializedName("data_mhs")
    public List<DetailAbsenResponse.MhsData> dataMhs;
}
