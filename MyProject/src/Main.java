import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyMole extends JFrame implements ActionListener, Runnable {
	Container con_m = getContentPane();

	//
	private BorderLayout bl = new BorderLayout(10, 10);
	private JPanel stage_1_1 = new JPanel();
	private GridLayout gl1 = new GridLayout(3, 4);
	private JPanel stage_1_2 = new JPanel();
	private GridLayout gl2 = new GridLayout(1, 2);
	private FlowLayout fl21 = new FlowLayout(FlowLayout.RIGHT);
	// main page
	private JPanel MainText = new JPanel(); // ����ȭ�� ��ܺ�
	private JPanel name = new JPanel(); // ����ȭ�� �ϴܺ�
	private JPanel ButtonPanel = new JPanel();
	private JLabel JTF = new JLabel("�δ��� ��� ����");
	private JLabel name_label = new JLabel("User_name");
	private JTextField name_text = new JTextField("");
//	private Stage_1 stage_1 = new Stage_1();
	private String user_name; // �����̸�
	private JButton start = new JButton("����");; // ���۹�ư
	private JButton record = new JButton("���");; // ��Ϲ�ư
	private JButton end = new JButton("����");; // ��Ϲ�ư
	private int randomsu = 0;
	private int count = 0;
	// stage page
	private ImageIcon icon = new ImageIcon("images/mole.png");
	private JLabel time_label = new JLabel("���� �ð� : 30");
	private JButton stage_1_start = new JButton("start");
	private JLabel score = new JLabel("���� : 0");
	private JButton[] mole_hole = new JButton[12];

	public MyMole(String title) {
		super(title);
		this.MainPage_init();// ���̾ƿ���ġ�� => 12��ư�� ��ü�� for������ ����
		this.MainPage_start(); // ��ưŬ�� �̺�Ʈ�������ڵ鷯���� => 12��ư�� �׼Ǹ����� for������
		// this.stage_1_init();
		// this.stage_start();

		super.setSize(500, 400);
		// setBounds�̿��ϸ� ����
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - super.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - super.getHeight() / 2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true); // �ȵ���̵� Toast�� AlertDialog.Builder =>.show()

	}

	public void MainPage_init() {

		// name Panel Setting
		name.setLayout(new GridLayout(5, 1));
		name.setBackground(Color.BLACK);
		name.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));

		name_label.setHorizontalAlignment(JLabel.CENTER);
		name_label.setFont(new Font("�������", 0, 30));
		name_label.setForeground(Color.WHITE);

		name_text.setHorizontalAlignment(JTextField.CENTER);
		name_text.setFont(new Font("�������", 0, 30));
		name_text.setForeground(Color.BLACK);

		start.setHorizontalAlignment(JLabel.CENTER);
		start.setFont(new Font("�������", 0, 30));
		start.setBackground(Color.WHITE);

		record.setHorizontalAlignment(JLabel.CENTER);
		record.setFont(new Font("�������", 0, 30));
		record.setBackground(Color.WHITE);

		end.setHorizontalAlignment(JLabel.CENTER);
		end.setFont(new Font("�������", 0, 30));
		end.setBackground(Color.WHITE);

		name.add(name_label);
		name.add(name_text);
		name.add(start);
		name.add(record);
		name.add(end);

		MainText.setLayout(new BorderLayout(10, 10));
		MainText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		MainText.setBackground(Color.LIGHT_GRAY);
		JTF.setFont(new Font("�������", 0, 50));
		JTF.setHorizontalAlignment(JLabel.CENTER);
		MainText.add(JTF, BorderLayout.CENTER);

		con_m.setLayout(bl);
		con_m.add("North", MainText);
		con_m.add("Center", name);
		con_m.add("South", ButtonPanel);
		ButtonPanel.setLayout(fl21);
		ButtonPanel.add(start);
		ButtonPanel.add(record);
		ButtonPanel.add(end);

	}

	public void stage_1_init() {
		MainText.setVisible(false);
		name.setVisible(false);
		ButtonPanel.setVisible(false);

		con_m.setLayout(bl);
		con_m.add(time_label, BorderLayout.NORTH);
		con_m.add(stage_1_1, BorderLayout.CENTER);
		stage_1_1.setLayout(gl1);
		for (int i = 0; i < 12; ++i) { // ��ư�� �迭�� ����
			mole_hole[i] = new JButton();
			stage_1_1.add(mole_hole[i]);
		}
		off_button();
		con_m.add("South", stage_1_2);
		stage_1_2.setLayout(gl2);
		stage_1_2.add(score);
		// jp2.add(jp21);
		// jp21.setLayout(fl21);
		// jp21.add(start);
		// jp21.add(end);
	}

	public void stage_2_init() {
		Container con_2 = getContentPane();
	}

	public void stage_3_init() {
		Container con_3 = getContentPane();
	}

	public void MainPage_start() { // Non-static �׼Ǹ����ʿ��� xxx.addActionListener
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.addActionListener(this);
		end.addActionListener(this);
//		for(int i=0; i<12; ++i){ 
//			mole_hole[i].setText(i+"");
//			mole_hole[i].addActionListener(this);
//		}
	} // end

	public void stage_start() {

		for (int i = 0; i < 12; ++i) {
			mole_hole[i].setText(i + "");
			mole_hole[i].addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) { // �׼ǰɱ�
		if (e.getSource() == start) { // Start��ư ������ �ð��� -1��
			stage_1_init();
			stage_start();

			time_label.setText("�ð� => 0:30");
			score.setText("���� : 0");
			count = -100;
			Thread th = new Thread(this); // Thread �ɱ�
			th.start(); // Thread ����

			random(0);

		} else if (e.getSource() == end) {
			System.exit(0);
		}
		for (int i = 0; i < 12; ++i) {
			if (e.getSource() == mole_hole[i]) {
				random(i);
			}
		}
	}

	public void off_button() {
		for (int i = 0; i < 12; ++i) {
			mole_hole[i].setVisible(false);
		}
	} // end

	public void on_button() {
		for (int i = 0; i < 12; ++i) {
			mole_hole[i].setVisible(true); // Ȱ��ȭ
		}
	} // end

	public void run() {
		int time = 30;
		
		
		on_button(); // ��ư on
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			time--;
			if (time == 0) {
				time_label.setText("������ �������ϴ�.");
				off_button();
				break;
			}
			time_label.setText("�ð� => 0:" + time);
		}
	} // end

	public void random(int i) {
		if (i != randomsu)
			return;
		count += 100;
		mole_hole[randomsu].setIcon(null);
		
		randomsu = (int) (Math.random() * 12);
		mole_hole[randomsu].setIcon(icon);
		score.setText("���� : " + count);

	}

}

//end
public class Main {
	public static void main(String[] args) {
		MyMole game = new MyMole("�δ�������");
	}
}

//class Stage_1 extends JPanel {
//	Stage_1() {
//		time_label = new JLabel("���� �ð� : 30");
//		stage_1_start = new JButton("start");
//		score = new JLabel("���� : 0");
//		getBackground();
//		setLayout(new BorderLayout(10, 10));
//
//		// stage_1_start.setSize(10, 10);
//
//		add(stage_1_start, BorderLayout.EAST);
//		add(time_label, BorderLayout.NORTH);
//		add(score, BorderLayout.SOUTH);
//		Thread th = new Thread();
//		th.start();
//	}
//}