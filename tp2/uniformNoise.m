#funcion que calcula ruido uniforme entre [-n/2,n/2].
# n es la amplitud del ruido
function rads=uniformNoise(n)
B=(n/2);
A=-B;

rads=A +(B-A) *rand();

endfunction


%!test

%! for n=5:10;
%!        A=-n/2;
%!        B=n/2;
%!        for i=1:20
%!            resp=uniformNoise(n);
%!            assert(resp>= A);
%!            assert (resp<= B);
%!        endfor
%! endfor