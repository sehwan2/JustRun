//import javax.swing.*;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//public class MyFrame extends JFrame implements ActionListener, Runnable {
//	
//	//
//	private BorderLayout bl = new BorderLayout(10,10);
//	private JPanel jp1 = new JPanel();
//	private GridLayout gl1 = new GridLayout(3,4);
//	private JPanel jp2 = new JPanel();
//	private GridLayout gl2 = new GridLayout(1,2);
//	private JPanel jp21 = new JPanel();
//	private FlowLayout fl21 = new FlowLayout(FlowLayout.RIGHT);
//	// main page
//	private JPanel MainText = new JPanel(); // 메인화면 상단부
//	private JPanel name = new JPanel(); // 메인화면 하단부
//	private JPanel ButtonPanel = new JPanel();
//	private JLabel JTF = new JLabel("두더지 잡기 게임");
//	private JLabel name_label = new JLabel("User_name");
//	private JTextField name_text = new JTextField("");
//	private Stage_1 stage_1 = new Stage_1();
//	private String user_name; // 유저이름
//	private JButton start = new JButton("시작");; // 시작버튼
//	private JButton record = new JButton("기록");; // 기록버튼
//	private JButton end = new JButton("종료");; // 기록버튼
//	private int randomsu = 0;
//	private int count = -1;
//	// stage page
//	private ImageIcon icon = new ImageIcon("images/mole.png");
//	private JLabel time_label = new JLabel("남은 시간 : 30");
//	private JButton stage_1_start = new JButton("start");
//	private JLabel score = new JLabel("점수 : 0");
//	private JButton[] mole_hole = new JButton[12];
//
//	public MyFrame(String title) {
//		super(title);
//		this.MainPage_init();//레이아웃배치도 => 12버튼의 개체를 for문에서 생성
//		this.start(); //버튼클릭 이벤트리스너핸들러연결 => 12버튼에 액션리스너 for문연결
//		super.setSize(300,200);
//		//setBounds이용하면 편함
//		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
//		int ypos = (int)(screen.getHeight()/2 - super.getHeight()/2);
//		super.setLocation(xpos, ypos);
//		super.setResizable(false);
//		super.setVisible(true); //안드로이드 Toast나 AlertDialog.Builder =>.show()
//		
//	}
//
//	public void MainPage_init() {
//		
//		//name Panel Setting
//		name.setLayout(new GridLayout(5, 1));
//		name.setBackground(Color.BLACK);
//		name.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));
//		
//		name_label.setHorizontalAlignment(JLabel.CENTER);
//		name_label.setFont(new Font("맑은고딕", 0, 30));
//		name_label.setForeground(Color.WHITE);
//
//		name_text.setHorizontalAlignment(JTextField.CENTER);
//		name_text.setFont(new Font("맑은고딕", 0, 30));
//		name_text.setForeground(Color.BLACK);
//
//		start.setHorizontalAlignment(JLabel.CENTER);
//		start.setFont(new Font("맑은고딕", 0, 30));
//		start.setBackground(Color.WHITE);
//
//		record.setHorizontalAlignment(JLabel.CENTER);
//		record.setFont(new Font("맑은고딕", 0, 30));
//		record.setBackground(Color.WHITE);
//
//		end.setHorizontalAlignment(JLabel.CENTER);
//		end.setFont(new Font("맑은고딕", 0, 30));
//		end.setBackground(Color.WHITE);
//
//		name.add(name_label);
//		name.add(name_text);
//		name.add(start);
//		name.add(record);
//		name.add(end);
//		
//		MainText.setLayout(new BorderLayout(10, 10));
//		MainText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//		MainText.setBackground(Color.LIGHT_GRAY);
//		JTF.setFont(new Font("맑은고딕", 0, 50));
//		JTF.setHorizontalAlignment(JLabel.CENTER);
//		MainText.add(JTF, BorderLayout.CENTER);
//
//		
//		Container con_m = getContentPane();
//		con_m.setLayout(bl);
//		con_m.add("North", MainText);
//		con_m.add("Center", name);
//		con_m.add("South", ButtonPanel);
//		ButtonPanel.setLayout(fl21);
//		ButtonPanel.add(start);
//		ButtonPanel.add(record);
//		ButtonPanel.add(end);
//		
//	}
//	public void stage_1_init() {
//		Container con_1 = getContentPane();
//		con_1.setLayout(bl);
//		con_1.add("North", time_label);
//		con_1.add("Center", new JPanel());
//		jp1.setLayout(gl1);
//		for(int i=0; i<12; ++i){ //버튼을 배열로 만듦
//			mole_hole[i] = new JButton();
//			jp1.add(mole_hole[i]);
//		}
//		off_button();
//		con_1.add("South", jp2);
//		jp2.setLayout(gl2);
//		jp2.add(score);
//		jp2.add(jp21);
//		jp21.setLayout(fl21);
//		jp21.add(start);
//		jp21.add(end);
//	}
//	public void stage_2_init() {
//		Container con_2 = getContentPane();
//	}
//	public void stage_3_init() {
//		Container con_3 = getContentPane();
//	}
//	
//	public void start(){ //Non-static 액션리스너연결 xxx.addActionListener
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		start.addActionListener(this);
//		end.addActionListener(this);
//		for(int i=0; i<12; ++i){ 
//			mole_hole[i].setText(i+"");
//			mole_hole[i].addActionListener(this);
//		}
//	} //end
//
//	
//
//	public void actionPerformed(ActionEvent e) { // 액션걸기
//		if (e.getSource() == start) { // Start버튼 누르면 시간이 -1씩
//			time_label.setText("시간 => 0:30");
//			score.setText("점수 : 0");
//			count = -1;
//			Thread th = new Thread(this); // Thread 걸기
//			th.start(); // Thread 시작
//			on_button(); // 버튼 on
//			random(0);
//		} else if (e.getSource() == end) {
//			System.exit(0);
//		}
//		for (int i = 0; i < 12; ++i) {
//			if (e.getSource() == mole_hole[i]) {
//				random(i);
//			}
//		}
//	}
//
//	public void off_button() {
//		for (int i = 0; i < 12; ++i) {
//			mole_hole[i].setEnabled(false);
//		}
//	} // end
//
//	public void on_button() {
//		for (int i = 0; i < 12; ++i) {
//			mole_hole[i].setEnabled(true); // 활성화
//		}
//	} // end
//
//	public void run() {
//		int time = 30;
//		while (true) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//			}
//			time--;
//			if (time == 0) {
//				time_label.setText("게임이 끝났습니다.");
//				off_button();
//				break;
//			}
//			time_label.setText("시간 => 0:" + time);
//		}
//	} // end
//
//	public void random(int i) {
//		if (i != randomsu)
//			return;
//		count++;
//		mole_hole[randomsu].setIcon(null);
//		randomsu = (int) (Math.random() * 12);
//		mole_hole[randomsu].setIcon(icon);
//		score.setText("점수 : " + count);
//
//	}
//	
//}
////end
//public class Main {
//	public static void main(String[] args) {
//		new MyFrame("두더지게임");
//	}
//}
//
//
////class Stage_1 extends JPanel {
////	Stage_1() {
////		time_label = new JLabel("남은 시간 : 30");
////		stage_1_start = new JButton("start");
////		score = new JLabel("점수 : 0");
////		getBackground();
////		setLayout(new BorderLayout(10, 10));
////
////		// stage_1_start.setSize(10, 10);
////
////		add(stage_1_start, BorderLayout.EAST);
////		add(time_label, BorderLayout.NORTH);
////		add(score, BorderLayout.SOUTH);
////		Thread th = new Thread();
////		th.start();
////	}
////}