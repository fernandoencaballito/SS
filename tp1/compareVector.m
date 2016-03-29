function ans=compareVector(vec1, vec2)
      l=length(vec1);
      if(l!=length(vec2))
          ans=false;
          disp("no coinciden en size vec1 y vec2");
          vec1
          vec2
      else
          vec1=sort(vec1);
          vec2=sort(vec2);
          ans=true;
          for i=1:length(vec1)
               if( vec1(i)!=vec2(i))
                  ans=false
                   break;
               endif
          endfor
      endif
endfunction