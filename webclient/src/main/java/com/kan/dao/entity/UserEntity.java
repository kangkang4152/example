package com.kan.dao.entity;

public class UserEntity {

	private int id;
	private String name;
	private int age;
	private int sex;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return  "id = " + String.valueOf(this.id)
				+ "name =" + this.name 
				+ "sex =" + String.valueOf(this.sex)
				+ "age =" + String.valueOf(this.age);
	}
	
	
}
