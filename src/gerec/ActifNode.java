package gerec;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ActifNode extends Node {

	/******************************************************
	 ******************** PUBLIC **************************
	 ******************************************************/

	public int getBleWindowSize() {
		return bleWindowSize;
	}

	public void setBleWindowSize(int bleWindowSize) {
		this.bleWindowSize = bleWindowSize;
	}

	public int getWifiWindowSize() {
		return wifiWindowSize;
	}

	public void setWifiWindowSize(int wifiWindowSize) {
		this.wifiWindowSize = wifiWindowSize;
	}

	
	public Set<Integer> getDirectWifiConnectedNode() {
		return directWifiConnectedNode;
	}

	public void setDirectWifiConnectedNode(Set<Integer> directWifiConnectedNode) {
		this.directWifiConnectedNode = directWifiConnectedNode;
	}

	public Set<Integer> getIndirectWifiConnectedNode() {
		return indirectWifiConnectedNode;
	}

	public void setIndirectWifiConnectedNode(
			Set<Integer> indirectWifiConnectedNode) {
		this.indirectWifiConnectedNode = indirectWifiConnectedNode;
	}

	public ActifNode(int x, int y, int speedX, int speedY, String role, int id) {
		super(x, y, speedX, speedY, role, id);
		if (role == JframeWindow.SCANNER) {
			this.bleWindowSize = generateWindowSize();
		}
		if (role == JframeWindow.SAMPLER) {
			this.wifiWindowSize = generateWindowSize();
		}
		directBleConnectedNode = new HashSet<Integer>();
		directWifiConnectedNode = new HashSet<Integer>();
		indirectBleConnectedNode = new HashSet<Integer>();
		indirectWifiConnectedNode = new HashSet<Integer>();
		ScannedNode = new HashSet<Integer>();
	}

	public Set<Integer> getScannedNode() {
		return ScannedNode;
	}

	public void setScannedNode(Set<Integer> scannedNode) {
		ScannedNode = scannedNode;
	}

	public Set<Integer> getDirectBleConnectedNode() {
		return directBleConnectedNode;
	}

	public void setDirectBleConnectedNode(Set<Integer> directBleConnectedNode) {
		this.directBleConnectedNode = directBleConnectedNode;
	}

	public Set<Integer> getIndirectBleConnectedNode() {
		return indirectBleConnectedNode;
	}

	public void setIndirectBleConnectedNode(
			Set<Integer> indirectBleConnectedNode) {
		this.indirectBleConnectedNode = indirectBleConnectedNode;
	}

	private int generateWindowSize() {
		Random random = new Random();
		return random.nextInt(JframeWindow.MAXWINDOWSIZE + 1);
	}


	/******************************************************
	 ******************** PRIVATE *************************
	 ******************************************************/
	private int bleWindowSize;
	private int wifiWindowSize;
	private Set<Integer> ScannedNode;
	private Set<Integer> directBleConnectedNode;
	private Set<Integer> indirectBleConnectedNode;
	private Set<Integer> directWifiConnectedNode;
	private Set<Integer> indirectWifiConnectedNode;


}
