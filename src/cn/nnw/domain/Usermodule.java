package cn.nnw.domain;

import java.io.Serializable;

public class Usermodule implements Serializable {

	private String useremail;
    private String moduleid;
    private String modulecolor;
    private String moduletitle;
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	public String getModulecolor() {
		return modulecolor;
	}
	public void setModulecolor(String modulecolor) {
		this.modulecolor = modulecolor;
	}
	public String getModuletitle() {
		return moduletitle;
	}
	public void setModuletitle(String moduletitle) {
		this.moduletitle = moduletitle;
	}

}
