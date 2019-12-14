package com.stiki.mangab.api;

import com.stiki.mangab.api.response.BaseResponse;
import com.stiki.mangab.api.response.CheckStatusLoginResponse;
import com.stiki.mangab.api.response.DetailAbsenResponse;
import com.stiki.mangab.api.response.GenerateQrCodeResponse;
import com.stiki.mangab.api.response.HistoryAbsensiResponse;
import com.stiki.mangab.api.response.LoginResponse;
import com.stiki.mangab.api.response.MyClassResponse;
import com.stiki.mangab.api.response.MyLectureResponse;

import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Api {
    @POST("auth/checkStatusLogin")
    CheckStatusLoginResponse checkStatusLogin(@Field("id_device") String idDevice);

    @POST("auth/login")
    LoginResponse login(@Field("no_induk") String noInduk, @Field("password") String password);

    @POST("auth/logout")
    BaseResponse logout(@Field("no_induk") String noInduk);

    @POST("atuh/changePassword")
    BaseResponse changePassword(@Field("no_induk") String noInduk);

    @POST("matkul/myLecture")
    MyLectureResponse myLecture(@Field("nip") String nip);

    @POST("matkul/myClass")
    MyClassResponse myClass(@Field("kode_matkul") String kodeMatkul);

    @POST("matkul/generateQrCode")
    GenerateQrCodeResponse generateQrCode(@Field("id_matkul") String idMatkul,
                                          @Field("topik") String topik,
                                          @Field("ruangan") String ruangan);

    @POST("absen/absenMhs")
    BaseResponse absenMhs(@Field("qr_code") String qrCode,
                          @Field("nrp") String nrp,
                          @Field("status_absen") String statusAbsen);

    @POST("absen/detailAbsen")
    DetailAbsenResponse detailAbsen(@Field("id_absen") String idAbsen);

    @POST("absen/historyAbsensi")
    HistoryAbsensiResponse historyAbsensi(@Field("no_induk") String noInduk);

    @POST("absen/rekap")
    BaseResponse rekap(@Field("qr_code") String qrCode);

}
