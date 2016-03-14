function writeNeighbours(outputFile,neighbours,N)
	fid=fopen(outputFile,'w');
	fclose(fid);
	for k = 1:N
			dlmwrite(outputFile,neighbours{1,k},"-append","newline","unix");
	endfor
endfunction
