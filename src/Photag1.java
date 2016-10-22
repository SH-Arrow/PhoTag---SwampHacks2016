import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.*;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;

public class Photag1  extends JFrame
{
	
	JButton button1 = new JButton("Assign Main Folder");
	JFileChooser fc = new JFileChooser();
	File picPath;
	
	public static void main(String[] args) 
	{
		new Photag1();

	}
	public Photag1()
	{
		this.setSize(750,600);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		int xPos = (dim.width/2) - (this.getWidth()/2);
		int yPos = (dim.height/2) - (this.getHeight()/2);
		
		this.setLocation(xPos,yPos);
		
		fc.setCurrentDirectory(new java.io.File(","));
		fc.setDialogTitle("Main Picture Folder");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setLocation(xPos, yPos);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Photag powered by Clarifai");
		
		ImageIcon bg = new ImageIcon(getClass().getResource("BACK.JPG"));
		JLabel homeImage = new JLabel(bg);
		homeImage.setBounds(0,0,200,200);
		this.add(homeImage);
		
		
		
		
		JPanel welcomePanel = new JPanel();
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(Color.DARK_GRAY);
		JLabel label1 = new JLabel("WELCOME TO");
		JLabel label2 = new JLabel("Photag");
		label2.setToolTipText("Desktop Application");
		
		welcomePanel.setLayout(new BorderLayout());
		welcomePanel.setBackground(Color.DARK_GRAY);
		
		
		Font font = label1.getFont();
		// same font but bold
		Font boldFont = new Font("Helvetica", Font.BOLD, font.getSize()+25);
		label1.setFont(boldFont);
		
		Font font2 = label2.getFont();
		// same font but bold
		Font boldFont2 = new Font("Helvetica", Font.PLAIN, font2.getSize()+25);
		label2.setFont(boldFont2);
		
		label1.setForeground(Color.WHITE);
		label2.setForeground(Color.CYAN);
		
		labelPanel.add(label1);
		labelPanel.add(label2);
				
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.DARK_GRAY);
		JButton button2 = new JButton("Continue");
		
		
		ListenForButton lForButton = new ListenForButton();
		ListenForWindow lForWindow = new ListenForWindow();
		
		button1.addActionListener(lForButton);
		this.addWindowListener(lForWindow);
		
		buttonPanel.add(button1);
		//buttonPanel.add(button2);
		
		welcomePanel.add(labelPanel, BorderLayout.WEST);
		welcomePanel.add(buttonPanel, BorderLayout.EAST);
		this.add(welcomePanel, BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.setVisible(true);
	
	}
	
	private class ListenForWindow implements WindowListener
	{

		public ListenForWindow() {
			// TODO Auto-generated constructor stub
		}

		public void windowOpened(WindowEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void windowClosing(WindowEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void windowClosed(WindowEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void windowIconified(WindowEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void windowActivated(WindowEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		public void windowDeactivated(WindowEvent e) 
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	private class ListenForButton implements ActionListener
	{
		public ListenForButton() {
			// TODO Auto-generated constructor stub
		}

		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == button1)
			{
				if(fc.showOpenDialog(button1) == JFileChooser.APPROVE_OPTION)
				{
					picPath = fc.getSelectedFile().getAbsoluteFile();
					new Photag2(picPath);
				}				
				
			}
			
		}
	}

}
