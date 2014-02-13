package wbflisteners;

public interface ProcessesListener {
	
	public void computing(ObservableProcess p);
	public void stopped(ObservableProcess p);
	public void notStarted(ObservableProcess p);
	public void finalized(ObservableProcess p);
	
}
