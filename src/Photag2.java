import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;

import static java.nio.file.StandardCopyOption.*;

public class Photag2  extends JFrame
{
	JButton button1 = new JButton("Conf.");
	JFileChooser fc = new JFileChooser();
	ArrayList<ArrayList<String>> lOL;
	ArrayList<File> fileName;
	
	public static void main(String[] args) 
	{
		
		/*
		ClarifaiClient clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
		List<RecognitionResult> results =
		    clarifai.recognize(new RecognitionRequest(new File("kittens.jpg")));

		for (Tag tag : results.get(0).getTags()) {
		  System.out.println(tag.getName() + ": " + tag.getProbability());
		}*/

	}
	public Photag2(File path) //throws IOException
	{
		lOL = new ArrayList<ArrayList<String>>();
		
		File[] directoryListing = path.listFiles(new FilenameFilter()
		{
			public boolean accept(File dir, String name) {
	            return !name.equals(".DS_Store");
	        }
		}
		);
		fileName = new ArrayList<File>();
		
		for(File f: directoryListing)
		{
			System.out.println(f);
			fileName.add(f);
		}
		
		if (directoryListing != null) 
		{
		    for (File child : directoryListing) 
		    {
		    	lOL.add(getTags(child));
		    }
		} 
		else 
		{
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		}
		
		
		
		this.setSize(750,600);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		int xPos = (dim.width/2) - (this.getWidth()/2);
		int yPos = (dim.height/2) - (this.getHeight()/2);
		
		this.setLocation(xPos,yPos);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Photag powered by Clarifai");
		
		
		JLabel homeImage = new JLabel();
		homeImage.setBackground(Color.DARK_GRAY);
		homeImage.setBounds(0,0,200,200);
		this.add(homeImage);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BorderLayout());
		labelPanel.setBackground(Color.DARK_GRAY);
		JLabel label1 = new JLabel("Photag");
		JLabel label2 = new JLabel("Photo Set Completed:");
		label1.setToolTipText("Desktop Application");
		
		JPanel previewPanel = new JPanel();
		previewPanel.setLayout(new BorderLayout());
		previewPanel.setBackground(Color.DARK_GRAY);
		
		
		Font font = label1.getFont();
		// same font but bold
		Font boldFont = new Font("Helvetica", Font.PLAIN, font.getSize()+25);
		label1.setFont(boldFont);
		
		Font font2 = label2.getFont();
		// same font but bold
		Font boldFont2 = new Font("Helvetica", Font.BOLD, font2.getSize()+10);
		label2.setFont(boldFont2);
		
		label1.setForeground(Color.CYAN);
		label2.setForeground(Color.WHITE);
		
		labelPanel.add(label1, BorderLayout.NORTH);
		labelPanel.add(label2, BorderLayout.CENTER);
				
		JPanel tagPanel = new JPanel();
		tagPanel.setBackground(Color.DARK_GRAY);
		tagPanel.setLayout(new BorderLayout());
	
		
		
		ListenForButton lForButton = new ListenForButton();
		ListenForWindow lForWindow = new ListenForWindow();
		
		button1.addActionListener(lForButton);
		this.addWindowListener(lForWindow);
		
		//tagPanel.add(button1, BorderLayout.WEST);
		
		
		JLabel preview = new JLabel();
		preview.setText("Enjoy your sort!");
		preview.setBackground(Color.DARK_GRAY);
		preview.setForeground(Color.DARK_GRAY);
		Font font3 = preview.getFont();
		// same font but bold
		Font boldFont3 = new Font("Helvetica", Font.BOLD, font3.getSize()+50);
		preview.setFont(boldFont3);
		
		previewPanel.add(labelPanel, BorderLayout.WEST);
		previewPanel.add(preview, BorderLayout.SOUTH);
		JPanel southExtra = new JPanel();
		southExtra.setLayout(new BorderLayout());
		
		southExtra.add(tagPanel, BorderLayout.WEST);
		this.add(southExtra, BorderLayout.SOUTH);
		this.add(previewPanel, BorderLayout.NORTH);
		
		this.setResizable(false);
		this.setVisible(true);
		
		organize(path);
	
	}
	
	public void organize(File p)
	{
		//lOL;
		int i = 0;
		for(ArrayList<String> t: lOL)
		{
			String topTag = t.get(0);
			new File(p.toString() + "/" + topTag).mkdir();
			String target = p.toString() + "/" + topTag;
			System.out.println(fileName.get(i).getName());
			System.out.println(p);
			System.out.println(topTag);
			System.out.println(target);
			boolean success = fileName.get(i).renameTo(new File(target + "/"+ fileName.get(i).getName()));
			
			
			if(success)
			{
				System.out.println("Success");
			}
			else
			{
				System.out.println("Sorry");
			}
			//Files.move(fileName.get(i), fTarget);*/
			i++;
		}
		
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
				
					
				}				
				
			}
			
		}
	}
	
	public ArrayList<String> getTags(File x)
	{
		ArrayList<String> aTagList = new ArrayList<String>();
		String APP_ID = "ORnpyh_pZSE46j9b9Hl06XcfIc8HN4OvJTBsnFeV",APP_SECRET = "8Qe7yvDFOQgqIQ04FTXWqJAFXjvz3sFZuiZ97Hno";
		ClarifaiClient clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
		List<RecognitionResult> results =
		    clarifai.recognize(new RecognitionRequest(x));
	//////////////
		for (Tag tag : results.get(0).getTags()) 
		{
			//System.out.println(x + " ===: Tags: ===  " + tag.getName() + " " + tag.getProbability());
			aTagList.add(tag.getName());// + ": " + tag.getProbability());
		}
		return aTagList;
	}
}
