public class Sample {

    public static void main (String[] args) {
	try {
	    SoundPlayer player = new SoundPlayer();
	    player.play("gong.wav");

	    PictureViewer viewer = new PictureViewer();
	    viewer.show("flower.jpg");
		
		ShowHTML browser = new ShowHTML();
		browser.show("Matrices.html");
		
		RunCommand processor = new RunCommand();
		processor.run("cube.exe");
	}
	catch (MultimediaException e) {
	    System.out.println(e.getMessage());
	}
    }
}
