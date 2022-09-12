echo([],[]).
echo([I|R],[I,I|RD]) :-
        echo(R, RD).


nthElem([H|T],H,0).
nthElem([_|T],X,N):- nthElem(T, X, Ntemp), N is Ntemp+1.

echomore([X|[]],[X|[]], 0).
echomore([X|[]], [X,X|[]], 1).
echomore([X|[]], [X|T], N):- echomore([X],T,Ntemp), N is Ntemp+1.

even(N):- N mod 2 =:= 0.
filterevens([],[]).
filterevens([H|T], [H|T2]):-
        even(H), filterevens(T, T2).
filterevens([_|T],L) :- filterevens(T,L).

echolots([X|[]], [X,X|[]],1).
echolots([X|T], [X,X|T2],1):- echolots(T,T2,1).
echolots([X|[]], [X,X,X|[]],2).
echolots([X|T], [X,X,X|T2],2):- echolots(T,T2,2).
echolots([X|[]], [X,X,X,X|[]],3).
echolots([X|T], [X,X,X,X|T2],3):- echolots(T,T2,3).
echolots([X|[]], [X,X,X,X,X|[]],4).
echolots([X|T], [X,X,X,X,X|T2],4):- echolots(T,T2,4).
