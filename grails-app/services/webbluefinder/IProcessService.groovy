package webbluefinder

interface IProcessService {
	
	public void execute()
	public void stop()
	public boolean isComputing()
	public boolean isFinalized()
	public String getErrors()
	public String getResults()
	
}
