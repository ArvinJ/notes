package com.ahhf.ljxbw;

public class User implements Comparable<User> {

	
	private Integer age ;
	private Integer mark;
	private String userName;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public int compareTo(User o) {
		// Ĭ��Ϊ����  this.getMark().compareTo(o.getMark()); ��֮Ϊ����
		//return this.getMark().compareTo(o.getMark());
		// ��һ�ֶ� ��mark desc
		int  temp = o.getMark().compareTo(this.getMark());
		if(temp==0){
			// �ڶ��ֶ� ��age desc
			return this.getAge().compareTo(o.getAge());
		}
		return temp;
	}
	@Override
	public String toString() {
		return "User [age=" + age + ", mark=" + mark + ", userName=" + userName + "]";
	}
	
	
}
