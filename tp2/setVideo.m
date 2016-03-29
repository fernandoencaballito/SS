function setVideo(videoFile, frameFile, frames)
	printf("setting animation video\n");
pkg load video

video = avifile(videoFile);
	

for k = 0:frames
	frameFileName = [frameFile num2str(k) ".png"];
	img = imread(frameFileName);
	img = im2double(img);
	for f = 1:5
		addframe(video,img);
	endfor
endfor	
