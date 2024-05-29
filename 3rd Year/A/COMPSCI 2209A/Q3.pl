last(X,[Xs]):-
	X = Xs.
    last(X, [_|Tail]):-
            last(X, Tail).