package cn.com.views;

import java.util.Enumeration;

import javax.comm.CommPortIdentifier;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainNorthPanel extends JPanel{
	JLabel lblCom;
	JComboBox<String> cmbComs;
	DefaultComboBoxModel<String> dcmComs;
	JComboBox<Integer> cmbRates;
	DefaultComboBoxModel<Integer> dcmRates;
	JLabel lblRate;
	
	public MainNorthPanel(){
		cmbComs=new JComboBox<String>();
		dcmComs=new DefaultComboBoxModel<String>();
		lblCom=new JLabel("¶Ë¿ÚºÅ:");
		lblRate=new JLabel("²¨ÌØÂÊ");
		cmbRates=new JComboBox<Integer>();
		dcmRates=new DefaultComboBoxModel<Integer>();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		initCmbData();
		this.add(lblCom);
		this.add(cmbComs);
		this.add(lblRate);
		this.add(cmbRates);
		
	}

	private void initCmbData() {
		// TODO Auto-generated method stub
		Enumeration<CommPortIdentifier> portList= CommPortIdentifier.getPortIdentifiers();
		while(portList.hasMoreElements()){
			CommPortIdentifier portId=(CommPortIdentifier) portList.nextElement();
			if(portId.getPortType()==CommPortIdentifier.PORT_SERIAL){
				dcmComs.addElement(portId.getName());
			}
		}
		cmbComs.setModel(dcmComs);
		
		dcmRates.addElement(100);
		dcmRates.addElement(300);
		dcmRates.addElement(600);
		dcmRates.addElement(1200);
		dcmRates.addElement(2400);
		dcmRates.addElement(4800);
		dcmRates.addElement(9600);
		dcmRates.addElement(14400);
		dcmRates.addElement(19200);
		dcmRates.addElement(38400);
		dcmRates.addElement(56000);
		dcmRates.addElement(57600);
		dcmRates.addElement(115200);
		dcmRates.addElement(128000);
		dcmRates.addElement(256000);
		dcmRates.setSelectedItem(9600);
		cmbRates.setModel(dcmRates);
	}
}
