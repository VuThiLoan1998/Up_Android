package hieu.com.testsqlite;

import java.util.Objects;

public class LopHoc {
    private String malop;
    private String tenlop;
    private String siso;

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public String getSiso() {
        return siso;
    }

    public void setSiso(String siso) {
        this.siso = siso;
    }

    public LopHoc() {
    }

    public LopHoc(String malop, String tenlop, String siso) {
        this.malop = malop;
        this.tenlop = tenlop;
        this.siso = siso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LopHoc lopHoc = (LopHoc) o;
        return Objects.equals(malop, lopHoc.malop);
    }

    @Override
    public int hashCode() {

        return malop.hashCode();
    }

    @Override
    public String toString() {
        return tenlop + "_"+ siso;
    }
}
