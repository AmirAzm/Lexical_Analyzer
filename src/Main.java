

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Main extends JFrame{
		static JPanel contentPane = new JPanel();
	public static void main(String[] args)throws IOException {
		JFrame frame =new JFrame("Compiler");
		JList list = new JList<>();
		JButton btnNewButton = new JButton();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200, 200,1400,820);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setForeground(Color.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		
		
		JTextArea area = new JTextArea();
		area.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		area.setBackground(Color.gray);
		area.setBounds(300,45,1050,530);
		contentPane.add(area);
		
		
		JTextArea area2 = new JTextArea();
		area2.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
		area2.setBackground(Color.white);
		area2.setBounds(300,595,1050 ,140);
		contentPane.add(area2);
		

		list = new JList();
		list.setBounds(25,45,250,690);
		contentPane.add(list);
		
		btnNewButton = new JButton("Run");
		btnNewButton.setBackground(new Color(200,200,200));
		btnNewButton.setBounds(300, 10, 96, 29);
		contentPane.add(btnNewButton);
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBackground(new Color(250,250,250));
		scrollBar.setBounds(1032,0,18,530);
		area.add(scrollBar);
		frame.add(contentPane);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String[] read;
				String str1 = null;
				str1 = area.getText();
				if(str1.length() > 0)
				{
					int co = 0;
					while(str1.charAt(co)==' ')
					{
						co++;
					}
					String str =str1.substring(co);
					read = str.split("\\s+");
					area2.setText(null);
					area2.append("Ready \n");
					ParseTable parser = new ParseTable();
					boolean b = parser.Parser(read);
					if(b == false && !ParseTable.string.equals("missing"))
					{
						String error = parser.string;
						area2.setForeground(Color.red);
						area2.append(error);
					}
					if( b == false && ParseTable.string.equals("missing"))
					{
						area2.setForeground(Color.red);
						if(parser.check1 > parser.check2)
							area2.append("Error : missing )");
						else if(parser.check1 < parser.check2)
							area2.append("Error : missing (");
						else
							area2.append("missing }");
					}
					if(b == true)
					{
						area2.setForeground(Color.GREEN);
						area2.append("Accepted");
					}
				}
				else
					area2.setText("Empty");
			}
		});
		

	}
}
