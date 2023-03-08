import java.util.Random;
import javax.swing.JLabel;

class TimerRunnable implements Runnable {
	private JLabel timer;

	public TimerRunnable(JLabel timer) {
		this.timer = timer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int n = 0;
		Random random = new Random();
		while (true) {
			//timer.setText(p);
			n++;
			if (n % 10 == 0)
				timer.setLocation(random.nextInt(180), random.nextInt(200));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}

	}
}
