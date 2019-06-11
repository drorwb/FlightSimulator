package test;

public class SleepCommand implements Command{

	@Override
	public double doCommand(String[] parameters, int commandIndex) {
		try {
			Thread.sleep(20);
		} catch (NumberFormatException | InterruptedException e) { e.printStackTrace(); }
		return parameters.length;
	}
}
