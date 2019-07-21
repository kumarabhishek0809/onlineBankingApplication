package com.onlineBankingApplication.batch.partition;

import com.onlineBankingApplication.batch.entity.UserDetailsData;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemPartitionProcessor implements ItemProcessor<UserDetailsData, UserDetailsData>{
	private String threadName;
	 
	  public String getThreadName() {
	    return threadName;
	  }
	 
	  public void setThreadName(String threadName) {
	    this.threadName = threadName;
	  }
	 
	  @Override
	  public UserDetailsData process(UserDetailsData person) throws Exception {
	    System.out.println(threadName + " processing : "+person);
	    return person;
	  }
}
