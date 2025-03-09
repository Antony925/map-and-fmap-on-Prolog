% реалізація fmap для дерев який проходить рекурсивно по гілкам
fmap(_, empty, empty).  % для порожніх вершин повертаємо порожнє значення
fmap(Predicate, tree(Left, Value, Right), tree(NewLeft, NewValue, NewRight)) :-
    fmap(Predicate, Left, NewLeft),  
    call(Predicate, Value, NewValue),  
    fmap(Predicate, Right, NewRight).

add1(X, Y) :- Y is X + 1.
