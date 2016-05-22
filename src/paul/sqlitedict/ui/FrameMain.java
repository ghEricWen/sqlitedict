package paul.sqlitedict.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import paul.sqlitedict.entity.Word;
import paul.sqlitedict.service.WordService;

public class FrameMain {
	
	private final JFrame frame ;
	
	private final JTextField fieldWord = new JTextField();
	
	private final JList<Word> listCandidates = new JList<>();	
	
	private final JLabel labelWord = new JLabel();
	
	private final JLabel labelType = new JLabel();
	
	private final JLabel labelDesc = new JLabel();
	
	private final DefaultListModel<Word> defaultListModel = new DefaultListModel<>();
	
	
	public FrameMain(){
		frame = new JFrame("主程序");		
		initComponents();
	}
	
	private void initComponents(){		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(440,200));
		panel.setLayout(new BorderLayout());		
		fieldWord.setHorizontalAlignment(JTextField.RIGHT);		
		panel.add(fieldWord,BorderLayout.NORTH);
		panel.add(listCandidates, BorderLayout.CENTER);
		
		JPanel panelResult = new JPanel();
		panelResult.setPreferredSize(new Dimension(440,150));
		panelResult.setLayout(new BorderLayout());
		JPanel panelPart = new JPanel();		
		panelPart.setPreferredSize(new Dimension(440,40));
		panelPart.setLayout(new GridLayout(1, 2));
		
		labelWord.setText("word");
		labelWord.setHorizontalAlignment(JLabel.CENTER);
		labelType.setText("type");
		labelType.setHorizontalAlignment(JLabel.CENTER);
		labelDesc.setText("desc");
		labelDesc.setHorizontalAlignment(JLabel.CENTER);
		panelPart.add(labelWord);
		panelPart.add(labelType);	
		panelResult.add(panelPart,BorderLayout.NORTH);
		panelResult.add(labelDesc, BorderLayout.CENTER);
		
		
		listCandidates.setModel(defaultListModel);
		fieldWord.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!defaultListModel.isEmpty()){
					defaultListModel.clear();
				}
				String word = fieldWord.getText().trim();
				if(word == null || word.isEmpty())
					return;
				List<Word> res = WordService.instance().search(word);
				for(Word w : res){
					defaultListModel.addElement(w);
				}
			}			
		});
		
		listCandidates.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = listCandidates.getSelectedIndex();
				Word word = defaultListModel.getElementAt(index);
				labelWord.setText(word.getWord().trim());
				labelType.setText(word.getType().trim());
				labelDesc.setText(word.getDefinition().trim());
			}
			
		});
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(panelResult, BorderLayout.SOUTH);
		frame.setLocation(300,300);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
