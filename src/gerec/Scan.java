package gerec;

import java.util.Timer;
import java.util.TimerTask;

public class Scan {
	
	/******************************************************
	 ******************** PUBLIC **************************
	 ******************************************************/
	
	public Scan() {
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, 1 * 1000);
	}
	
	class RemindTask extends TimerTask {
		
		public synchronized void run() {
			for (int i = 0; i < JframeWindow.scanner.size(); i++) {
				JframeWindow.scannedNode.addAll(JframeWindow.scanner.get(i).getScannedNode());
			}
		}
	}
	
	/******************************************************
	 ******************** PRIVATE *************************
	 ******************************************************/
	
	private Timer timer;

}
