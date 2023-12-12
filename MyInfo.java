package myInfo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyInfo extends JFrame implements ListSelectionListener, ActionListener{
	
	// 컴포넌트 변수 선언
	// 리스트
	String[] year = {"2005", "2006", "2007", "2008", "2009"};
	Integer[] month = new Integer[12];
	String[] day = new String[31];
	
	JList<String> listYear = new JList<String>(year);
	JList<Integer> listMonth = new JList<Integer>(month);
	JList<String> listDay = new JList<String>(day);
	
	// 스크롤 생성
	JScrollPane scrollMonth = new JScrollPane(listMonth);
	JScrollPane scrollDay = new JScrollPane(listDay);
	
	// 라디오 버튼 생성
	JRadioButton radio_male = new JRadioButton("남자");
	JRadioButton radio_female = new JRadioButton("여자");
	
	// 라벨 만들기
	JLabel sample = new JLabel("20507 김현빈");
	
	// 체크 박스 만들기
	JCheckBox check_agree = new JCheckBox(" 개인정보 제공에 동의합니다.");
	
	// 버튼 만들기
	JButton btnOk = new JButton("확인");
	JButton btnNo = new JButton("취소");
	
	// 폰트
	Font defaultFont = new Font("굴림", Font.BOLD, 20);
	
	public MyInfo() {
		
		// 데이터 채우기
		for(int i=0; i<month.length; i++)
			month[i] = i+1;
		
		for(int i=0; i<day.length; i++)
			day[i] = Integer.toString(i+1);
		
		// 라디오 버튼을 그룹에 추가해서 하나만 선택되도록
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radio_male);
		buttonGroup.add(radio_female);
		
		// 레이아웃 그리기
		initLayout();
		
		// 이벤트 리스너 달기
		listYear.addListSelectionListener(this);
		listMonth.addListSelectionListener(this);
		listDay.addListSelectionListener(this);
		
		radio_male.addActionListener(this);
		radio_female.addActionListener(this);
		
		btnOk.addActionListener(this);
		btnNo.addActionListener(this);
	}
	
	// initLayout 메소드 생성
	private void initLayout() {
		
		// 패널 생성 및 추가
		JPanel p = new JPanel();
		add(p);
		p.setLayout(null);	// 절댓값 좌표 사용
		
		// 리스트의 위치
		listYear.setBounds(10, 30, 90, 160);
		scrollMonth.setBounds(120, 30, 90, 160);
		scrollDay.setBounds(220, 30, 90, 160);
		
		// 리스트의 첫번째 항목이 강제 선택되도록
		listYear.setSelectedIndex(0);
		listMonth.setSelectedIndex(0);
		listDay.setSelectedIndex(0);
		
		ChangeSample();
		
		// 라디오버튼 위치, 폰트값을 설정
		radio_male.setBounds(320, 30, 80, 50);
		radio_female.setBounds(320, 80, 80, 50);
		
		radio_male.setFont(defaultFont);
		radio_female.setFont(defaultFont);
		
		radio_male.setSelected(true);	// 기본 선택을 남자로 설정
		
		// 라벨 위치, 폰트값을 설정
		sample.setBounds(10, 180, 500, 50);
		sample.setFont(defaultFont);
		
		// 체크박스 위치, 폰트값을 설정
		check_agree.setBounds(10, 300, 500, 50);
		check_agree.setFont(defaultFont);
		
		// 버튼 위치
		btnOk.setBounds(100, 360, 80, 20);
		btnNo.setBounds(200, 360, 80, 20);
		
		// 패널에 붙이기
		p.add(listYear);
		p.add(scrollMonth);
		p.add(scrollDay);
		p.add(radio_male);
		p.add(radio_female);
		p.add(sample);
		p.add(check_agree);
		p.add(btnOk);
		p.add(btnNo);
		
		// 기본설정
		setTitle("수행평가 연습");
		setSize(450, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}

	public static void main(String[] args) {
		new MyInfo();	// 생성자 호출
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if((e.getSource() ==radio_male) || (e.getSource() == radio_female)) {
			
			ChangeSample();
		}
		else if(e.getSource() == btnOk) {
			
			if(check_agree.isSelected()) {
				JOptionPane.showMessageDialog(null, "감사합니다.");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "개인정보 동의 버튼을 클릭해주세요.");
			}
		}
		else if(e.getSource() == btnNo) {
			dispose();
		}
	}

	private void ChangeSample() {
		
		String y = listYear.getSelectedValue();
		int m = listMonth.getSelectedValue();
		String d = listDay.getSelectedValue();
		
		String info = "생년월일 : " + y + "년 " + m + "월 " + d + "일";
		
		if(radio_male.isSelected()) {
			info = "성별 : 남자 "  + info;
		}
		else if(radio_female.isSelected()) {
			info = "성별 : 여자 "  + info;
		}
		
		sample.setText(info);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		ChangeSample();
	}

}
