package com.rockies.webservice.model;

public class UserWsDTO extends BaseModel{

	private long id;
	
	private String name;
	
	private int age;
	
	private double salary;

	public UserWsDTO(){
		id=0;
	}
	
	public UserWsDTO(long id, String name, int age, double salary){
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserWsDTO other = (UserWsDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserWsDTO [id=" + id + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + "]";
	}
}

