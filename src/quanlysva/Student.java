package quanlysva;

public class Student {
	private String masv, hoTen, lop;
	private float diem;
	public Student() { }
	public Student(String masv, String hoTen, String lop, float diem) {
		this.masv = masv;
		this.hoTen = hoTen;
		this.lop = lop;
		this.diem = diem;
	}
	public String getMasv() {
		return masv;
	}

	public void setMasv(String masv) {
		this.masv = masv;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}
	public float getDiem() {
		return diem;
	}
	public void setDiem(float diem) {
		this.diem = diem;
	}
	
}
