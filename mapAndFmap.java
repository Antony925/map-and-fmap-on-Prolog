import java.util.*;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;

// Узагальнена структура дерева
class Tree<T> {
    T value;
    Tree<T> left, right;

    public Tree(T value, Tree<T> left, Tree<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    // Реалізація fmap для дерев
    public <R> Tree<R> fmap(Function<T, R> f) {
        return new Tree<>(
                f.apply(this.value),
                this.left == null ? null : this.left.fmap(f),
                this.right == null ? null : this.right.fmap(f)
        );
    }

    // Вивід дерева (прямий обхід)
    public void printTree() {
        System.out.print(this.value + " ");
        if (left != null) left.printTree();
        if (right != null) right.printTree();
    }
}

// Загальний інтерфейс Functor
interface Functor<T, F extends Functor<?, ?>> {
    <R> F fmap(Function<T, R> f);
}

class FunctorList<T> implements Functor<T, FunctorList<?>> {
    private final List<T> list;

    public FunctorList(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public <R> FunctorList<R> fmap(Function<T, R> f) {
        List<R> newList = new ArrayList<>();
        for (T item : list) {
            newList.add(f.apply(item));
        }
        return new FunctorList<>(newList);
    }

    public void printList() {
        System.out.println(list);
    }
}


class myFmap {
    public static void main(String[] args) {
        // tree з fmap
        Tree<Integer> tree = new Tree<>(10, new Tree<>(5, null, null), new Tree<>(15, null, null));
        Tree<Integer> newTree = tree.fmap(n -> n + 5); // збільшуємо значення ноди на 5
        newTree.printTree();
        System.out.println("");

        // list з fmap
        FunctorList<Integer> numbers = new FunctorList<>(List.of(1, 2, 3, 4, 5));
        FunctorList<Integer> squaredNumbers = numbers.fmap(x -> x * x); // отримуємо квадрат списку
        squaredNumbers.printList();

        // list з map
        List<Integer> numbers_map = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> squaredNumbers_map = map(x -> x * x, numbers_map);
        System.out.println(squaredNumbers_map);
    }


    public static <T, R> List<R> map(Function<T, R> func, List<T> list) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(func.apply(item));
        }
        return result;
    }
}
