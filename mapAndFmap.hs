myMap :: (a->b) -> [a] -> [b] 
myMap _ []     = []
myMap f (x:xs) = f x : map f xs


data MyTree a = Empty | Node (MyTree a) a (MyTree a) deriving Show
instance Functor MyTree where
    fmap _ Empty = Empty
    fmap f (Node left val right) = Node (fmap f left) (f val) (fmap f right)

add5 :: Num a => a -> a
add5 = (+5)