package gerec;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class gerecSimulation extends JFrame {

	/******************************************************
	 ******************** PUBLIC **************************
	 ******************************************************/
	
	public static boolean electionWatcher = false;

	/**
	 * Launch the application.
	 * Launch Election
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gerecSimulation window = new gerecSimulation();
					window.frame.setVisible(true);
						new SamplerElection();
						new WatcherElection();
						new Scan();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gerecSimulation() {
		initialize();
	}

	/******************************************************
	 ******************** PUBLIC **************************
	 ******************************************************/
	private JFrame frame;
	private static final long serialVersionUID = 1L;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("GEREC");
		frame.add(new JframeWindow());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setSize(1000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JframeWindow surface = new JframeWindow();
		add(surface);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Timer timer = surface.getTimer();
				timer.stop();
			}
		});
	}
}
