import java.io.*;

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class SoundPlayer { 

	// constructor to initialize streams and clip 
	public void play(String fileName) throws MultimediaException { 
		// create AudioInputStream object 
		try {
			AudioInputStream audioInputStream = 
				AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile()); 
		
			// create clip reference 
			Clip clip = AudioSystem.getClip(); 
		
			// open audioInputStream to the clip 
			clip.open(audioInputStream); 
		
			clip.start(); 
			System.out.print("Press RET to continue");
			BufferedReader keyboard =  new BufferedReader
		                   (new InputStreamReader(System.in));
			String c = keyboard.readLine();			
			stop(clip);
		}
		catch (Exception e) {
			throw new MultimediaException("Error processing input file "+fileName);
		}
	} 


	// Method to stop the audio 
	public void stop(Clip clip) throws UnsupportedAudioFileException, 
	IOException, LineUnavailableException { 
		clip.stop(); 
		clip.close(); 
	} 
} 
