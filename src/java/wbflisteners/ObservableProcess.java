package wbflisteners;

import java.util.ArrayList;

public class ObservableProcess {
	 
	private ArrayList<ProcessesListener> observers;
	  
	  public ObservableProcess() {
		  observers = new ArrayList<ProcessesListener>();
	  }
	 
	  public void addObserver(ProcessesListener o) {
		  observers.add(o);
	  }
	 
	  public void removeObserver(ProcessesListener o) {
		  observers.remove(o);
	  }
	 
	  public void notifyFinished() {
	      for (ProcessesListener o:observers) {
	    	  o.finalized(this);
	      }
	      System.out.println("notifico");
	  }
}
