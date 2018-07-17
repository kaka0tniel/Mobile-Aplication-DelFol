package com.otniel.delfol.delfol.Model;

import java.util.List;

/**
 * Created by Otniel on 5/22/2018.
 */

public class ResponsBerita {
    String  kode, pesan;
    List<modelBerita> result;

    public List<modelBerita> getResult() {
        return result;
    }

    public void setResult(List<modelBerita> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
