
function [time,meanExits,errorExits] =getExitData(filename)
    
    data= csvread (filename);
time=data(:,1);
    exits=data(:,2:end);

    meanExits = mean(exits')';
    errorExits = std(exits')'./2;


endfunction

