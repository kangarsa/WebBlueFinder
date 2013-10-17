package webbluefinder;

import java.util.ArrayList;

public abstract class AbstractObservableProcess {
	private ArrayList<IProcessObserver> observers;


	public AbstractObservableProcess() {
		observers = new ArrayList<IProcessObserver>();
	}
 
	public void addObserver(IProcessObserver o) {
		observers.add(o);
	}
 
	public void removeObserver(IProcessObserver o) {
		observers.remove(o);
	}
	 
	public void notifyErrorsToObservers() {
		for (IProcessObserver o:observers) {
			o.updateErrors();
		}
	}
 
	public void notifyFinalizedToObservers() {
		for (IProcessObserver o:observers) {
			o.updateProcess();
		}
	}
}
