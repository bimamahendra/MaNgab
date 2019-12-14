package com.stiki.mangab.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryAbsensiResponse extends BaseResponse {
    @SerializedName("data")
    public List<HistoryAbsensiData> data;

    static class HistoryAbsensiData {
        @SerializedName("kode_matkul")
        public String kodeMatkul;

        @SerializedName("nama_matkul")
        public String namaMatkul;

        @SerializedName("kelas_matkul")
        public String kelasMatkul;

        @SerializedName("topik_matkul")
        public String topikMatkul;

        @SerializedName("ruangan_matkul")
        public String ruanganMatkul;

        @SerializedName("jadwal_kelas")
        public String jadwalKelas;

        @SerializedName("status_absen")
        public String statusAbsen;

        @SerializedName("jadwal_absen")
        public String jadwalAbsen;
    }
}
