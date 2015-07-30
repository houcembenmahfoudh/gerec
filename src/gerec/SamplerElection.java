package gerec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class SamplerElection {
	
	/******************************************************
	 ******************** PUBLIC **************************
	 ******************************************************/
	public SamplerElection() {
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, 1 * 2000);
	}

	class RemindTask extends TimerTask {
		public void run() {
			if (gerecSimulation.electionWatcher == false) {
				visitedScanner.clear();
				scannerGroup.clear();
				gerecSimulation.electionWatcher = true;
				for (int i = 0; i < JframeWindow.scanner.size(); i++) {
					if (JframeWindow.scanner.get(i).getIndirectBleConnectedNode()
							.size() > 0
							&& !visitedScanner.contains(JframeWindow.scanner.get(i).getId())) {
						visitedScanner.addAll(JframeWindow.scanner.get(i)
								.getIndirectBleConnectedNode());
						scannerGroup.add(JframeWindow.scanner.get(i)
								.getIndirectBleConnectedNode());
					}
					if (JframeWindow.scanner.get(i).getDirectBleConnectedNode().size() < 1  ) {
						Set<Integer> self = new HashSet<Integer>();
						self.add(JframeWindow.scanner.get(i).getId());
						JframeWindow.scanner.get(i).setRole(JframeWindow.SAMPLER);
						scannerGroup.add(self);
						
					}
				}
				for (int i = 0; i < scannerGroup.size(); i++) {
					int min = JframeWindow.MAXWINDOWSIZE;
					for (int j : scannerGroup.get(i)) {
						if (JframeWindow.scanner.get(j).getBleWindowSize() <= min) {
							min = JframeWindow.scanner.get(j).getBleWindowSize();
						}

					}
					boolean samplerElected = false;
					for (int k : scannerGroup.get(i)) {
						if (JframeWindow.scanner.get(k).getBleWindowSize() == min
								&& samplerElected == false ) {
							if (scannerGroup.get(i).size() > 0)
								{JframeWindow.scanner.get(k).setBleWindowSize(
										JframeWindow.MAXWINDOWSIZE);
							JframeWindow.scanner.get(k).setRole(JframeWindow.SAMPLER);
							samplerElected = true;}
						} else {
							JframeWindow.scanner.get(k)
									.setBleWindowSize(
											JframeWindow.scanner.get(k)
													.getBleWindowSize() / 2);
							JframeWindow.scanner.get(k).setRole(JframeWindow.SCANNER);
						}
					}
				}
			}
		}
	}
	/******************************************************
	 ******************** PRIVATE *************************
	 ******************************************************/

	private Timer timer;
	private List<Set<Integer>> scannerGroup = new ArrayList<Set<Integer>>();
	private Set<Integer> visitedScanner = new HashSet<Integer>();


}
