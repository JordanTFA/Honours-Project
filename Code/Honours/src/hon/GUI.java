package hon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUI {
	
	public static Boolean removeUnwanted;
	public static Boolean removeWordsFromClue;
	public static String letter;
	
	public static void buildGUI(){
		
		JFrame frame = new JFrame("Semantic Bigrams");
		frame.setBounds(0, 0, 600, 250);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();	
		frame.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 600, 400);
		panel.setLayout(null);	
		frame.getContentPane().add(panel);
		
		JLabel lblCorpus = new JLabel("Corpus:");
		lblCorpus.setBounds(10,10,150,30);
		panel.add(lblCorpus);
		
		JRadioButton rdoSam = new JRadioButton("I am Sam");
		JRadioButton rdoHarry = new JRadioButton("Harry Potter");
		JRadioButton rdoRomeo = new JRadioButton("Romeo & Juliet");
		JRadioButton rdoMyCorpus = new JRadioButton("My Corpus");
		
		
		rdoSam.setSelected(true);
		
		rdoSam.setBounds(10,45,150,30);
		rdoRomeo.setBounds(10,70,150,30);	
		rdoHarry.setBounds(10,95,150,30);
		rdoMyCorpus.setBounds(10,120,150,30);
		
		ButtonGroup grpCorpus = new ButtonGroup();
		
		grpCorpus.add(rdoSam);
		grpCorpus.add(rdoRomeo);
		grpCorpus.add(rdoHarry);
		grpCorpus.add(rdoMyCorpus);
		
		panel.add(rdoSam);
		panel.add(rdoRomeo);
		panel.add(rdoHarry);
		panel.add(rdoMyCorpus);
		
		JLabel lblRemoveUnwanted = new JLabel("Remove Unwanted Words:");
		lblRemoveUnwanted.setBounds(180,10,150,30);
		panel.add(lblRemoveUnwanted);
		
		JCheckBox chkRemoveUnwanted = new JCheckBox("From Corpus");
		chkRemoveUnwanted.setBounds(180,45,150,30);
		panel.add(chkRemoveUnwanted);
		
		JCheckBox chkRemoveFromClue = new JCheckBox("From Clue");
		chkRemoveFromClue.setBounds(180,70,150,30);
		panel.add(chkRemoveFromClue);
		
		JLabel lblClueType = new JLabel("Clue Type:");
		lblClueType.setBounds(400,10,150,30);
		panel.add(lblClueType);
		
		JRadioButton rdoAnagram = new JRadioButton("Anagram");
		rdoAnagram.setSelected(true);
		rdoAnagram.setBounds(400,40,150,30);
		panel.add(rdoAnagram);
		
		JRadioButton rdoDictionary = new JRadioButton("Dictionary");
		rdoDictionary.setBounds(400,65,150,30);
		panel.add(rdoDictionary);
		
		ButtonGroup grpClueType = new ButtonGroup();
		grpClueType.add(rdoAnagram);
		grpClueType.add(rdoDictionary);
		
		JLabel lblLetter = new JLabel("Letter to Load:");
		lblLetter.setBounds(400,120,150,30);
		panel.add(lblLetter);
		
		JTextField tfLetter = new JTextField();
		tfLetter.setBounds(400, 150, 30, 30);
		panel.add(tfLetter);
		
		JTextField tfClue = new JTextField();
		tfClue.setBounds(10,155,300,20);
		panel.add(tfClue);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10,180,75,20);
		panel.add(btnSubmit);
		
		JButton btnLoad = new JButton("Load Corpus");
		btnLoad.setBounds(180,180,120,20);
		panel.add(btnLoad);
		
		btnLoad.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{	
				if(rdoSam.isSelected()){
					Sngram.setCorpus("I am Sam");
				}else if(rdoRomeo.isSelected()){
					Sngram.setCorpus("Romeo");
				}else if(rdoHarry.isSelected()){
					Sngram.setCorpus("Harry Potter");
				}else if(rdoMyCorpus.isSelected()){
					Sngram.setCorpus("mycorpus");
				}
				
				if(chkRemoveUnwanted.isSelected()){
					removeUnwanted = true;
				}else{
					removeUnwanted = false;
				}
				
				try {
					Sngram.prepareCorpus(removeUnwanted);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{	

				Sngram.setClue(tfClue.getText());
				
				if(chkRemoveFromClue.isSelected()){
					removeWordsFromClue = true;
				}else{
					removeWordsFromClue = false;
				}
				try {
					
					if(rdoAnagram.isSelected()){
						Sngram.queryAnagram(removeWordsFromClue);
					}else if(rdoDictionary.isSelected()){
						letter = tfLetter.getText().toUpperCase();
						
						Sngram.queryDictionary(removeWordsFromClue, letter);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

	
	}
}
