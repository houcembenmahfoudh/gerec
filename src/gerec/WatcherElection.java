package gerec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class WatcherElection {
	/******************************************************
	 ******************** PUBLIC **************************
	 ******************************************************/

	public WatcherElection() {
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, 1 * 5000);
	}

	class RemindTask extends TimerTask {
		public void run() {
			if (gerecSimulation.electionWatcher) {
				visitedSampler.clear();
				samplerGroup.clear();
				gerecSimulation.electionWatcher = false;
				for (int i = 0; i < JframeWindow.scanner.size(); i++) {
					if (JframeWindow.scanner.get(i)
							.getIndirectWifiConnectedNode().size() > 0
							&& !visitedSampler.contains(JframeWindow.scanner
									.get(i).getId())) {
						visitedSampler.addAll(JframeWindow.scanner.get(i)
								.getIndirectWifiConnectedNode());
						samplerGroup.add(JframeWindow.scanner.get(i)
								.getIndirectWifiConnectedNode());
					}
					if (JframeWindow.scanner.get(i)
							.getDirectWifiConnectedNode().size() < 1) {
						Set<Integer> self = new HashSet<Integer>();
						self.add(JframeWindow.scanner.get(i).getId());
						JframeWindow.scanner.get(i).setRole(
								JframeWindow.WATCHER);
						samplerGroup.add(self);
					}

				}
				for (int i = 0; i < samplerGroup.size(); i++) {
					int min = JframeWindow.MAXWINDOWSIZE;
					for (int j : samplerGroup.get(i)) {
						if (JframeWindow.scanner.get(j).getWifiWindowSize() <= min) {
							min = JframeWindow.scanner.get(j)
									.getWifiWindowSize();
						}
					}
					boolean watcherElected = false;
					for (int k : samplerGroup.get(i)) {
						if (JframeWindow.scanner.get(k).getWifiWindowSize() == min
								&& watcherElected == false
								&& JframeWindow.scanner.get(k).getRole() == JframeWindow.SAMPLER) {
							if (samplerGroup.get(i).size() > 1)
								JframeWindow.scanner.get(k).setWifiWindowSize(
										JframeWindow.MAXWINDOWSIZE);
							JframeWindow.scanner.get(k).setRole(
									JframeWindow.WATCHER);
							watcherElected = true;
						} else {
							if (JframeWindow.scanner.get(k).getRole() != JframeWindow.SAMPLER) {
								JframeWindow.scanner.get(k).setWifiWindowSize(
										JframeWindow.scanner.get(k)
												.getWifiWindowSize() / 2);
								JframeWindow.scanner.get(k).setRole(
										JframeWindow.SCANNER);
							}
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
	private List<Set<Integer>> samplerGroup = new ArrayList<Set<Integer>>();
	private Set<Integer> visitedSampler = new HashSet<Integer>();

}
