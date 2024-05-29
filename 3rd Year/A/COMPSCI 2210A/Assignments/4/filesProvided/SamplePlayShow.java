public class SamplePlayShow {

    public static void main (String[] args) {
	try {
	    SoundPlayer player = new SoundPlayer();
	    player.play("gong.wav");

	    PictureViewer viewer = new PictureViewer();
	    viewer.show("flower.jpg");
	}
	catch (MultimediaException e) {
	    System.out.println(e.getMessage());
	}
    }
}
