package handlers;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class TextHandler {
	
	
	/// <summary>
	/// Author            :  Ajit Kumar
	/// Description       : Updating Log File
	/// </summary>
	
	public static void UpdateFile(String File, String logText) throws IOException
	{
		FileWriter fileWritter = new FileWriter(File, true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

		try
		{
			String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
			bufferWritter.write(timeStamp+" => "+logText);
			bufferWritter.newLine();
			bufferWritter.close();

		}
		catch (Exception LogFileError)
		{
			JOptionPane.showMessageDialog(null, "Log file Exception occured.... "+LogFileError, "InfoBox: ",JOptionPane.INFORMATION_MESSAGE);
			bufferWritter.close();
		}
		
	}

}
