

videoFile = "test.avi";
staticFile = "./Data/staticFile.txt";
frameFile = "./Frames/frame";

t_init = 0;
t_end = 50;
frames =  t_end - t_init

		getFrames(staticFile,frameFile,t_init,t_end);

		setVideo(videoFile, frameFile, frames);

