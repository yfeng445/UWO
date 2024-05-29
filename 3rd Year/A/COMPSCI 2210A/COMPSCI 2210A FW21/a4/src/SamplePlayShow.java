public class SamplePlayShow {

    public static void main (String[] args) {
	try {
	    SoundPlayer player = new SoundPlayer();
	    player.play("src/roar.wav");

	    PictureViewer viewer = new PictureViewer();
	    viewer.show("src/flower.jpg");
	}
	catch (MultimediaException e) {
	    System.out.println(e.getMessage());
	}
    }
}
