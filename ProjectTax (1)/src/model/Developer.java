package model;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Developer implements HttpSessionBindingListener {
	private String name, mail, company, division;
	
	
	
	
	@Override
	public String toString() {
		return "©" + name+ ", "+ division+", "+company+ ", mail: " + mail ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Developer() {
		super();
	}

	public Developer(String name, String mail, String company, String division) {
		super();
		this.name = name;
		this.mail = mail;
		this.company = company;
		this.division = division;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("Class: Developer, massage: 'value bound',  session ID: "+event.getSession().getId());

	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

}
