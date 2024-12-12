
package Model;


public class ChiTietPhieuXuat {
    int MaChiTietPhieuXuat;
    int MaPhieuXuat;
    int SoLuong;
    Float DOnGia;

    public ChiTietPhieuXuat(int MaChiTietPhieuXuat, int MaPhieuXuat, int SoLuong, Float DOnGia) {
        this.MaChiTietPhieuXuat = MaChiTietPhieuXuat;
        this.MaPhieuXuat = MaPhieuXuat;
        this.SoLuong = SoLuong;
        this.DOnGia = DOnGia;
    }

    public int getMaChiTietPhieuXuat() {
        return MaChiTietPhieuXuat;
    }

    public void setMaChiTietPhieuXuat(int MaChiTietPhieuXuat) {
        this.MaChiTietPhieuXuat = MaChiTietPhieuXuat;
    }

    public int getMaPhieuXuat() {
        return MaPhieuXuat;
    }

    public void setMaPhieuXuat(int MaPhieuXuat) {
        this.MaPhieuXuat = MaPhieuXuat;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Float getDOnGia() {
        return DOnGia;
    }

    public void setDOnGia(Float DOnGia) {
        this.DOnGia = DOnGia;
    }
    
    
    
}
