/*
 * Created by JFormDesigner on Sat Nov 20 00:04:47 ICT 2021
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import controller.*;
import model.*;

/**
 * @author Nguyen Hoang Phuong
 */
public class HomeFrm extends JFrame {
	
	private ClientCtr mySocket;
	ArrayList<Player> list = new ArrayList<Player>();
	
	public HomeFrm(ClientCtr socket) {
		mySocket = socket;
		
		initComponents();
		
		mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_SCOREBOARD, this));
		mySocket.sendData(new ObjectWrapper(ObjectWrapper.SCOREBOARD, "ok"));
	}
	
	public void receiveDataScoreBoard(ObjectWrapper data) {
		list = (ArrayList<Player>)data.getData();
		if (data.getData() instanceof ArrayList<?>) {
			
			Collections.sort(list,new Comparator<Player>() {
				@Override
				public int compare(Player p1,Player p2) {
					
					return p1.getTotalScore()<=p2.getTotalScore() ?1:-1;
				}
			});
			String [] columnNames = {"Rank", "Username", "Total Score", "Status"};
			String[][] value = new String[list.size()][columnNames.length];
			for (int i = 0; i < list.size(); i++) {
				value[i][0] = (i + 1) + "";
				value[i][1] = list.get(i).getUsername();
				value[i][2] = list.get(i).getTotalScore() + "";
				if (list.get(i).getStatus() == null)
					value[i][3] = "away";		
				else
					value[i][3] = list.get(i).getStatus();
			}
			DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					//unable to edit cells
					return false;
				}
			};
			table2.setModel(tableModel);
		}
		else
			table2.setModel(null);
		
		table2.addMouseListener( new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JTable source = (JTable)e.getSource();
	            int row = source.rowAtPoint(e.getPoint());
	            int column = source.columnAtPoint(e.getPoint());
	            PopUpScoreBoard popup = new  PopUpScoreBoard(row, list);
	            source.setComponentPopupMenu(popup);
	            if (row >= 0 && row < table2.getRowCount()) {
	            	table2.setRowSelectionInterval(row, row);
	            } else {
	            	table2.clearSelection();
	            }
			}
		});
	}
	
	public class PopUpScoreBoard extends JPopupMenu{
		JMenuItem menuitem1 = new JMenuItem("Challenge");
		
		public PopUpScoreBoard(int index, ArrayList<Player> list) {	
			
			if (list.get(index).getStatus() == null || list.get(index).getStatus().equals("away")) {
				menuitem1.setEnabled(false);
			}
			
			menuitem1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {				
					//myControl.sendData(new ObjectWrapper(ObjectWrapper.CHALLENGE_PLAYER, list.get(index)));	
//					JOptionPane.showMessageDialog(this, "waiting");
				}
			});
//			menu	
			add(menuitem1);
			setEnabled(true);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Nguyen Hoang Phuong
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		scrollPane2 = new JScrollPane();
		table2 = new JTable();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();

		//======== this ========
		setBackground(new Color(255, 204, 153));
		var contentPane = getContentPane();

		//======== scrollPane1 ========
		{

			//---- table1 ----
			table1.setFont(new Font("Roboto", Font.PLAIN, 14));
			scrollPane1.setViewportView(table1);
		}

		//======== scrollPane2 ========
		{

			//---- table2 ----
			table2.setFont(new Font("Roboto", Font.PLAIN, 14));
			scrollPane2.setViewportView(table2);
		}

		//---- label1 ----
		label1.setText("Online Players:");
		label1.setFont(new Font("Roboto", Font.BOLD, 16));
		label1.setForeground(new Color(0, 0, 51));

		//---- label2 ----
		label2.setText("Top Players:");
		label2.setFont(new Font("Roboto", Font.BOLD, 16));
		label2.setForeground(new Color(0, 0, 51));

		//---- label3 ----
		label3.setText("Username");
		label3.setFont(new Font("Roboto", Font.BOLD, 16));
		label3.setForeground(new Color(0, 0, 51));

		//---- label4 ----
		label4.setText("Total Score");
		label4.setFont(new Font("Roboto", Font.BOLD, 16));
		label4.setForeground(new Color(0, 0, 51));

		//---- label5 ----
		label5.setText("xxx");
		label5.setFont(new Font("Roboto", Font.BOLD, 16));
		label5.setForeground(new Color(0, 153, 204));

		//---- label6 ----
		label6.setText("123");
		label6.setFont(new Font("Roboto", Font.BOLD, 16));
		label6.setForeground(new Color(0, 204, 51));

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(242, 242, 242)
					.addComponent(label1)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 424, Short.MAX_VALUE)
					.addComponent(label2)
					.addGap(226, 226, 226))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(label3)
						.addComponent(label4))
					.addGap(29, 29, 29)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(label6)
						.addComponent(label5))
					.addContainerGap(940, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label3)
						.addComponent(label5))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label4)
						.addComponent(label6))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label1)
						.addComponent(label2))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
						.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Nguyen Hoang Phuong
	private JScrollPane scrollPane1;
	private JTable table1;
	private JScrollPane scrollPane2;
	private JTable table2;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
}
