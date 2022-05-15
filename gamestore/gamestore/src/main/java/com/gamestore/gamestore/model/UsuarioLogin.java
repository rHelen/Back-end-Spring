package com.gamestore.gamestore.model;

public class UsuarioLogin {
	
	private Long id;
	
	private String name;
	
	private String user;
	
	private String password;
	
	private String photo;
	
	private String type;
	
	private String token;
	

	public UsuarioLogin(Long id, String name, String user, String password, String photo, String type, String token) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.password = password;
		this.photo = photo;
		this.type = type;
		this.token = token;
	}
	
	public UsuarioLogin() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
