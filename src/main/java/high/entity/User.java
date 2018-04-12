package high.entity;

import java.io.Serializable;

public class User implements Serializable{
	{
		System.out.println("普通代码块！");
	}
	static{
		System.out.println("静态代码块");
	}
	private static final long serialVersionUID = -3107149676470496908L;
	
	private Long id;
	private String firstName;
	private String userMail;
	private String lastName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public User(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		System.out.println("构造方法！");
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("构造方法！");
	}
	public User(Long id, String firstName, String userMail, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.userMail = userMail;
		this.lastName = lastName;
		System.out.println("构造方法！");
	}	
}
