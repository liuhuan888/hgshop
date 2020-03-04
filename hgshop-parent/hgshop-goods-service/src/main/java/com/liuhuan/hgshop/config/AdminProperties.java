package com.liuhuan.hgshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:hgshop-admin.properties")
public class AdminProperties {

	@Value("${admin.user}")
	private String adminUser;

	@Value("${admin.pwd}")
	private String adminPassword;

	public String getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "AdminProperties [adminUser=" + adminUser + ", adminPassword=" + adminPassword + "]";
	}

	public AdminProperties(String adminUser, String adminPassword) {
		super();
		this.adminUser = adminUser;
		this.adminPassword = adminPassword;
	}

	public AdminProperties() {
		super();
	}

}
