
parent(john,estelle).
parent(john, rob).
parent(peter, grace).
parent(peter,george).
parent(george, john).
parent(george, mary).
parent(george, jay).
parent(sue, rob).
parent(sue, estelle).
parent(ida, george).
parent(ida, grace).
parent(estelle, john).
parent(estelle, mary).
parent(estelle, jay).
male(john).
male(peter).
male(rob).
male(george).
male(jay).
male(john).
female(sue).
female(ida).
female(estelle).
female(grace).
female(mary).

grandparent(Grandparent,Grandchild):-
   parent(Grandparent, Z),
       parent(Z, Grandchild). 

grandfather(Grandfather, Grandchild):-
    male(Grandfather),
    parent(Grandfather, Z),
    parent(Z, Grandchild).

uncle(Uncle, Nephew):-
    grandparent(Y,Nephew),
    parent(Y, Uncle),
    \+ parent(Uncle, Y),
    male(Uncle).

mother_in_law(Mother_in_law, Child_in_law):-
    parent(Child_in_law, Y),
    grandparent(Mother_in_law, Y),
    \+ parent(Mother_in_law, Child_in_law),
    female(Mother_in_law).

brother(brother, bro):-
    \+ brother = bro,
    parent(Y, brother),
    parent(Y, bro),
    male(brother).
    
two_brother(brother1,brother2, bro):-
    male(brother1),
    male(brother2),
    \+ brother1 = brother2,
    \+ brother1 = bro,
    \+ brother2 = bro,
    parent(Y, brother1),
    parent(Y, brother2),
    parent(Y, bro).

    







    
    