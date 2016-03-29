function plotEj3(plotCells, cellsCant, particlesCant, inputFile, outputFile, M_min)

	result_CIM = dlmread(inputFile);

	colormap cool
		colors = 1:plotCells;
		k=1;
		color = colormap();	
		color(1,:)
		for M = cellsCant
			##color = colors(k);
			q= polyfit(particlesCant,result_CIM(:,M-M_min+1)',1);
			#particlesCant, result_CIM(:,M-M_min+1), "*", 

			plot(particlesCant, polyval(q, particlesCant),"linestyle","-","Color",[rand,rand,rand]);
			hold on
			k=k+1;
		endfor

		title("test optimo M");
		xlabel("Densidad (N/L^2)");
		ylabel("tiempo de ejecucion [s]");
		legend("M=8","M=9","M=10","M=11","M=12","M=13","location","northwest");
		hold off
		p = gcf();
		print(p,outputFile);
endfunction