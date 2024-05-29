
sum([],0).
sum([Head|Subset], Sum):-
    sum(Subset, Subsum),
    Sum is Head+Subsum.

count([],0).
count([_|Tail],Count):-
    count(Tail, Subcount),
    Count is Subcount+1.

mean(List, Mean):-
    sum(List, S),
    count(List, C),
    Mean is S/C.

    
min([X], X) :- !.
min([X,Y|Tail], N):-
    ( X > Y ->
        min([Y|Tail], N);
        min([X|Tail], N)
    ).

max([X], X) :- !.
max([X,Y|Tail], N):-
    ( X < Y ->
        max([Y|Tail], N);
        max([X|Tail], N)
    ).
    
    
