package cn.nnw.web.formbean;

public class RegisterForm {

	private String RegisterForm_email;
	private String RegisterForm_nickname;
	private String RegisterForm_password;
	private String RegisterForm_password_confirmation;
	
	

	public String getRegisterForm_email() {
		return RegisterForm_email;
	}



	public void setRegisterForm_email(String registerForm_email) {
		RegisterForm_email = registerForm_email;
	}



	public String getRegisterForm_nickname() {
		return RegisterForm_nickname;
	}



	public void setRegisterForm_nickname(String registerForm_nickname) {
		RegisterForm_nickname = registerForm_nickname;
	}



	public String getRegisterForm_password() {
		return RegisterForm_password;
	}



	public void setRegisterForm_password(String registerForm_password) {
		RegisterForm_password = registerForm_password;
	}



	public String getRegisterForm_password_confirmation() {
		return RegisterForm_password_confirmation;
	}



	public void setRegisterForm_password_confirmation(
			String registerForm_password_confirmation) {
		RegisterForm_password_confirmation = registerForm_password_confirmation;
	}



	public boolean validate(){
		boolean isok = true;
		
		
		
		return isok;
		
	}
	
	
	
}
