function writeNeighbours(outputFile,neighbours,N)
	fid=fopen(outputFile,'w');
	fclose(fid);
	for k = 1:N
		output = [k,neighbours{1,k}];
		dlmwrite(outputFile,output," ","-append","newline","unix");
	endfor
endfunction
