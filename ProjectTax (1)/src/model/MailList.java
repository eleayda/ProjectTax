package model;

import java.util.ArrayList;
import java.util.List;

public class MailList  {
List <String>mailList=new ArrayList<>();
	
	public List<String> getMailList() {
	return mailList;
}

@Override
	public String toString() {
		return "MailList [mailList=" + mailList + "]";
	}

public void setMailList(List<String> mailList) {
	this.mailList = mailList;
}

	public MailList() {
		
	}

	

	public void uppdateMailList(String mail) {
		
		if(!mailList.contains(mail)){
			mailList.add(mail);
			
				
		}
			
		
	}

}
