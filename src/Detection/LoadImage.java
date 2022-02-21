package Detection;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class LoadImage extends JFrame {

	public LoadImage()
	{	

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		 
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.validate();
		this.setVisible(true);
	}	 
	
	public void displayimage ()
	{
		ImageIcon image = new ImageIcon("C:\\Gcertain\\result\\result.png");
		this.setSize(image.getIconWidth()+100,image.getIconHeight()+100);
		// Draw the Image data into the BufferedImage
		JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
		this.getContentPane().add(label1);
	}

}
