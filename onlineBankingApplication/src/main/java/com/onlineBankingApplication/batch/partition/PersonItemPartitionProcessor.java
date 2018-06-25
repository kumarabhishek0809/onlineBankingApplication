package com.onlineBankingApplication.batch.partition;

import com.onlineBankingApplication.batch.entity.User;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemPartitionProcessor implements ItemProcessor<User,User>{
	private String threadName;
	 
	  public String getThreadName() {
	    return threadName;
	  }
	 
	  public void setThreadName(String threadName) {
	    this.threadName = threadName;
	  }
	 
	  @Override
	  public User process(User person) throws Exception {
	    System.out.println(threadName + " processing : "+person);
	    return person;
	  }
}
