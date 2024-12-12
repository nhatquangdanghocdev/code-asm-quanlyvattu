package Model;

public class VatTu {

    String Mavattu;
    String TenVattu;
    String Donvitinh;
    double Dongia;

    public VatTu(String Mavattu, String TenVattu, String Donvitinh, double Dongia) {
        this.Mavattu = Mavattu;
        this.TenVattu = TenVattu;
        this.Donvitinh = Donvitinh;
        this.Dongia = Dongia;
    }

    

    public VatTu() {
    }

    public String getMavattu() {
        return Mavattu;
    }

    public void setMavattu(String Mavattu) {
        this.Mavattu = Mavattu;
    }

    public String getTenVattu() {
        return TenVattu;
    }

    public void setTenVattu(String TenVattu) {
        this.TenVattu = TenVattu;
    }

    public String getDonvitinh() {
        return Donvitinh;
    }

    public void setDonvitinh(String Donvitinh) {
        this.Donvitinh = Donvitinh;
    }

    public double getDongia() {
        return Dongia;
    }

    public void setDongia(double Dongia) {
        this.Dongia = Dongia;
    }

    

}
