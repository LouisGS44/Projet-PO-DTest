import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class Frame_Info extends JFrame implements ActionListener{
	
	public Frame_Info(int num) {
		setTitle("Informations DTest");
		
		setSize(new Dimension(372, 226));
		setResizable(false);
		getContentPane().setLayout(null);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(122, 167, 117, 29);
		getContentPane().add(btnOk);
		
		JLabel lblNewLabel = new JLabel("Resultat = 7 : risque faible (1%)");
		lblNewLabel.setBounds(28, 17, 256, 16);
		if(num <=7 && num > 0){
			lblNewLabel.setForeground(Color.green);
		}
		getContentPane().add(lblNewLabel);
		
		JLabel lblResultat = new JLabel("Resultat = 7-11 : risque legerement eleve (4%)");
		lblResultat.setBounds(28, 45, 317, 16);
		if(num > 7 && num <= 11){
			lblResultat.setForeground(Color.yellow);
		}
		getContentPane().add(lblResultat);
		
		JLabel lblResultat_1 = new JLabel("Resultat = 12-14 : risque modere (17%)");
		lblResultat_1.setBounds(28, 73, 317, 16);
		if(num > 11 && num <= 14){
			lblResultat_1.setForeground(Color.orange);
		}
		getContentPane().add(lblResultat_1);
		
		JLabel lblResultat_2 = new JLabel("Resultat = 15-20 : risque eleve (33%)");
		lblResultat_2.setBounds(28, 100, 317, 16);
		if(num > 14 && num <= 20){
			lblResultat_2.setForeground(Color.red);
		}
		getContentPane().add(lblResultat_2);
		
		JLabel lblResultat_3 = new JLabel("Resultat > 20 : risque tres eleve (50%)");
		lblResultat_3.setBounds(28, 128, 317, 16);
		if(num > 20){
			lblResultat_2.setForeground(Color.red);
		}
		getContentPane().add(lblResultat_3);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
