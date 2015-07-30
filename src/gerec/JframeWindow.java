package gerec;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Timer;

class JframeWindow extends JPanel implements ActionListener {

	/******************************************************
	 ******************** PUBLIC **************************
	 ******************************************************/

	public static final String SCANNER = "Scanner";
	public static final String SAMPLER = "Sampler";
	public static final String WATCHER = "Watcher";
	public static final int MAXWINDOWSIZE = 50;
	public static Set<Integer> scannedNode = new HashSet<Integer>();
	public static List<Node> scanned = new ArrayList<Node>();
	public static List<ActifNode> scanner = new ArrayList<ActifNode>();

	/**
	 * Initialize the timer of the frame.
	 */
	public JframeWindow() {
		initTimer();
	}

	/**
	 * Get the timer.
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * Draw Scanned Nodes.
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawingScanned(g);
		drawingScanner(g);
		drawingSampler(g);
		drawingWatcher(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();

	}

	/******************************************************
	 ******************** PRIVATE *************************
	 ******************************************************/

	private static final long serialVersionUID = 1L;
	private final int SCANNERNB = 7;
	private final int SCANNEDNB = 100;
	private final String SCANNED = "Scanned";
	private final int DELAY = 100;
	private Timer timer;
	private List<Integer> visitedBle = new ArrayList<Integer>();
	private List<Integer> visitedWifi = new ArrayList<Integer>();
	private int i;
	private int j;
	private int width;
	private int height;

	/**
	 * Initialize the timer.
	 */
	private void initTimer() {
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void drawingScanned(Graphics g) {
		width = getWidth();
		height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.blue);
		g2d.setStroke(new BasicStroke(5));

		if (i == 0) {
			for (int j = 0; j < SCANNEDNB; j++) {
				Random r = new Random();
				int x = 100 + r.nextInt(width - 200);
				int y = 100 + r.nextInt(height - 200);
				int speedX = -5 + (int) (Math.random() * (11));
				int speedY = -5 + (int) (Math.random() * (11));
				scanned.add(new Node(x, y, speedX, speedY, SCANNED, j));
			}
			i++;
		} else {
			for (int i = 0; i < scanned.size(); i++) {
				scanned.get(i).setX(
						scanned.get(i).getX() + scanned.get(i).getSpeedX());
				scanned.get(i).setY(
						scanned.get(i).getY() + scanned.get(i).getSpeedY());

				if (scanned.get(i).getX() >= width - 100)
					scanned.get(i).setSpeedX(-scanned.get(i).getSpeedX());
				if (scanned.get(i).getY() >= height - 100)
					scanned.get(i).setSpeedY(-scanned.get(i).getSpeedY());
				if (scanned.get(i).getX() <= 100)
					scanned.get(i).setSpeedX(-scanned.get(i).getSpeedX());
				if (scanned.get(i).getY() <= 100)
					scanned.get(i).setSpeedY(-scanned.get(i).getSpeedY());

				g2d.drawLine(scanned.get(i).getX(), scanned.get(i).getY(),
						scanned.get(i).getX(), scanned.get(i).getY());
			}
		}
	}

	/**
	 * Draw Scanner Nodes.
	 */
	private void drawingScanner(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.red);
		g2d.setStroke(new BasicStroke(3));
		if (j == 0) {
			for (int i = 0; i < SCANNERNB; i++) {
				Random r = new Random();
				int x = 100 + r.nextInt((width - 100) - 100);
				int y = 100 + r.nextInt((height - 100) - 100);
				int speedX = -5 + (int) (Math.random() * ((5 - (-5)) + 1));
				int speedY = -5 + (int) (Math.random() * ((5 - (-5)) + 1));
				scanner.add(new ActifNode(x, y, speedX, speedY, SCANNER, i));
			}
			j++;
		} else {
			for (int i = 0; i < scanner.size(); i++) {
				scanner.get(i).setX(
						scanner.get(i).getX() + scanner.get(i).getSpeedX());
				scanner.get(i).setY(
						scanner.get(i).getY() + scanner.get(i).getSpeedY());

				if (scanner.get(i).getX() >= width - 100)
					scanner.get(i).setSpeedX(-scanner.get(i).getSpeedX());
				if (scanner.get(i).getY() >= height - 100)
					scanner.get(i).setSpeedY(-scanner.get(i).getSpeedY());
				if (scanner.get(i).getX() <= 100)
					scanner.get(i).setSpeedX(-scanner.get(i).getSpeedX());
				if (scanner.get(i).getY() <= 100)
					scanner.get(i).setSpeedY(-scanner.get(i).getSpeedY());

				g2d.drawOval(scanner.get(i).getX() - 50,
						scanner.get(i).getY() - 50, 100, 100);
				g2d.drawLine(scanner.get(i).getX(), scanner.get(i).getY(),
						scanner.get(i).getX(), scanner.get(i).getY());

			}
			for (int i = 0; i < scanner.size(); i++) {
				for (int j = 0; j < scanner.size(); j++) {
					if (i != j) {
						if (scanner.get(i).getRole() != WATCHER
								&& scanner.get(j).getRole() != WATCHER) {
							if (neighborBle(i, j)) {
								scanner.get(i).getDirectBleConnectedNode()
										.add(scanner.get(j).getId());
								scanner.get(i).getIndirectBleConnectedNode()
										.add(scanner.get(i).getId());
								g2d.drawLine(scanner.get(i).getX(), scanner
										.get(i).getY(), scanner.get(j).getX(),
										scanner.get(j).getY());

							} else {
								scanner.get(i)
										.getDirectBleConnectedNode()
										.remove((Integer) scanner.get(j)
												.getId());
								scanner.get(i)
										.getIndirectBleConnectedNode()
										.remove((Integer) scanner.get(j)
												.getId());
							}
						}
					}
				}

			}
			for (int j = 0; j < scanner.size(); j++) {
				// Recursive
				connectedBleNode(j);
				visitedBle.clear();
				g2d.drawString(scanner.get(j).getBleWindowSize() + ":"
						+ scanner.get(j).getRole(), scanner.get(j).getX(),
						scanner.get(j).getY());

				g2d.drawString(scanner.get(j).getIndirectBleConnectedNode()
						.toString(), scanner.get(j).getX(), scanner.get(j)
						.getY() + 15);

			}
		}
	}

	/**
	 * Draw Sampler Nodes.
	 */
	private void drawingSampler(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.black);
		g2d.setStroke(new BasicStroke(3));
		for (int i = 0; i < scanner.size(); i++) {
			for (int j = 0; j < scanner.size(); j++) {
				if (i != j) {
					if (scanner.get(i).getRole() == SAMPLER
							&& scanner.get(j).getRole() == SAMPLER) {
						if (neighborWifi(i, j)) {
							scanner.get(i).getDirectWifiConnectedNode()
									.add(scanner.get(j).getId());
							scanner.get(i).getIndirectWifiConnectedNode()
									.add(scanner.get(j).getId());
							
							g2d.drawString(scanner.get(i).getBleWindowSize()
									+ ":" + scanner.get(i).getRole(), scanner
									.get(i).getX(), scanner.get(i).getY());
							g2d.drawOval(scanner.get(i).getX() - 250, scanner
									.get(i).getY() - 250, 500, 500);
							g2d.drawLine(scanner.get(j).getX(), scanner.get(j)
									.getY(), scanner.get(i).getX(), scanner
									.get(i).getY());

						} else {
							scanner.get(i).getDirectWifiConnectedNode()
									.remove((Integer) scanner.get(j).getId());
							scanner.get(i).getIndirectWifiConnectedNode()
									.remove((Integer) scanner.get(j).getId());
						}
					}

				}
			}
		}
		for (int j = 0; j < scanner.size(); j++) {

				connectedWifiNode(j);
				visitedWifi.clear();
				g2d.drawString(scanner.get(j).getIndirectWifiConnectedNode()
						+ ":" + scanner.get(j).getRole(),
						scanner.get(j).getX(), scanner.get(j).getY() + 30);
			
		}
		
	}

	/**
	 * Draw Watcher Nodes.
	 */
	private void drawingWatcher(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.GREEN);
		g2d.setStroke(new BasicStroke(8));
		for (int i = 0; i < scanner.size(); i++) {
			if (scanner.get(i).getRole() == WATCHER) {
				g2d.drawOval(scanner.get(i).getX() - 250,
						scanner.get(i).getY() - 250, 500, 500);
				g2d.drawLine(scanner.get(i).getX(), scanner.get(i).getY(),
						scanner.get(i).getX(), scanner.get(i).getY());
			}

		}
	}

	/**
	 * Check connected Bluetooth nodes.
	 */
	private void connectedBleNode(int index) {
		scanner.get(index).getIndirectBleConnectedNode()
				.addAll(scanner.get(index).getDirectBleConnectedNode());
		visitedBle.add(index);
		for (int i : scanner.get(index).getDirectBleConnectedNode()) {
			if (!visitedBle.contains(i)) {
				connectedBleNode(i);
			}
			scanner.get(index).getIndirectBleConnectedNode()
					.addAll(scanner.get(i).getIndirectBleConnectedNode());
		}
	}

	/**
	 * Check connected Wifi nodes.
	 */
	private void connectedWifiNode(int index) {
		scanner.get(index).getIndirectWifiConnectedNode()
				.addAll(scanner.get(index).getDirectWifiConnectedNode());
		visitedWifi.add(index);
		for (int i : scanner.get(index).getDirectWifiConnectedNode()) {
			if (!visitedWifi.contains(i)) {
				connectedWifiNode(i);
			}
			scanner.get(index).getIndirectWifiConnectedNode()
					.addAll(scanner.get(i).getIndirectWifiConnectedNode());
		}
	}

	/**
	 * Check Bluetooth neighbor.
	 */
	private boolean neighborBle(int i, int j) {
		if (Math.sqrt(Math
				.pow(scanner.get(j).getX() - scanner.get(i).getX(), 2)
				+ Math.pow(scanner.get(j).getY() - scanner.get(i).getY(), 2)) < 100)
			return true;
		else
			return false;
	}

	/**
	 * Check Wifi neighbor.
	 */
	private boolean neighborWifi(int i, int j) {
		if (Math.sqrt(Math
				.pow(scanner.get(j).getX() - scanner.get(i).getX(), 2)
				+ Math.pow(scanner.get(j).getY() - scanner.get(i).getY(), 2)) < 500)
			return true;
		else
			return false;
	}

}
