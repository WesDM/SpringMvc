package com.wesdm.springmvc.spittr;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class Spitter {
	
	private Long id;
	@NotNull
	@Size(min=5, max=16, message="{username.size}")
	private String username;
	@NotNull
	@Size(min=5, max=25, message="{password.size}")
	private String password;
	@NotNull
	@Size(min=2, max=30, message="{firstName.size}")
	private String firstName;
	@NotNull
	@Size(min=2, max=30, message="{lastName.size}")
	private String lastName;
	@NotNull
	@Email(message="{email.valid}")
	private String email;
	
	public Spitter(){
		
	}
	
	public Spitter(Long id, String username, String password, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spitter other = (Spitter) obj;
		return Objects.equals(this.id, other.id) &&
				Objects.equals(this.username, other.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.username);
	}
}
