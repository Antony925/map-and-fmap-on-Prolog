map(_, [], []).  % для порожнього списоку
map(Predicate, [X|Xs], [Y|Ys]) :-  
    call(Predicate, X, Y),  % Застосовуємо функцію до поточного елементу
    map(Predicate, Xs, Ys). % Рекурсивний виклик для хвоста списку

add1(In, Out) :-
    Out is In + 1.