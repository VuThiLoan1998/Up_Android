package hieu.com.testsqlite;

import java.io.Serializable;
import java.util.Objects;

public class Sinhvien implements Serializable {
    private String masv;
    private String tensv;

    private String gioitinh;
    private String ngaysinh;
    private String malop;

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }



    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public Sinhvien() {
    }

    public Sinhvien(String masv, String tensv,  String gioitinh, String ngaysinh, String malop) {
        this.masv = masv;
        this.tensv = tensv;

        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.malop = malop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sinhvien sinhvien = (Sinhvien) o;
        return masv.equals(sinhvien.masv);
    }

    @Override
    public int hashCode() {

        return masv.hashCode();
    }

    @Override
    public String toString() {
        return masv + " - " + tensv + " - " +gioitinh + " - " +ngaysinh + " - " +malop;
    }
}
