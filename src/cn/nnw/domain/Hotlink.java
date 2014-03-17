package cn.nnw.domain;

public class Hotlink {

	private String hotlink;
	private int accessamount;
	private String location;
	private int cachetime;
	private String title;
	public String getHotlink() {
		return hotlink;
	}
	public void setHotlink(String hotlink) {
		this.hotlink = hotlink;
	}
	public int getAccessamount() {
		return accessamount;
	}
	public void setAccessamount(int accessamount) {
		this.accessamount = accessamount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCachetime() {
		return cachetime;
	}
	public void setCachetime(int cachetime) {
		this.cachetime = cachetime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
