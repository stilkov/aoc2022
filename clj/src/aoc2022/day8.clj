(ns aoc2022.day8)

(require '[clojure.java.io :as io])

(defn element-at [m a b]
  ((m b) a))

(defn width [m]
  (count (m 0)))

(defn height [m]
  (count m))

(defn top [m x y]
  (map #(element-at m x %) (range 0 y)))

(defn left [m x y]
  (map #(element-at m % y) (range 0 x)))

(defn right [m x y]
  (map #(element-at m % y) (range (inc x) (width m))))

(defn bottom [m x y]
  (map #(element-at m x %) (range (inc y) (height m))))

(defn visible-through [tree trees]
  (every? #(< % tree) trees))

(defn visible [m x y]
  (let [e (element-at m x y)]
    (or (visible-through e (right m x y))
        (visible-through e (left m x y))
        (visible-through e (top m x y))
        (visible-through e (bottom m x y)))))

(defn visible-trees [m]
  (let [edges (+ (* 2 (width m)) (* 2 (height m)) -4)]
    (+ edges (count (filter identity
                            (for [x (range 1 (dec (width m)))
                                  y (range 1 (dec (height m)))]
                              (visible m x y)))))))

(defn parse-char [c] (- (int c) (int \0)))
(defn parse-line [line] (apply vector (map parse-char (seq line))))

(defn part1 []
  (let [lines (line-seq (io/reader "../input/day8.txt"))
        matrix (apply vector (map parse-line lines))]
    (visible-trees matrix)))

(defn scenic-score-elem-dir [tree trees]
  (min (count trees) (inc (count (take-while identity (map #(< % tree) trees))))))


(defn scenic-score-elem [m x y]
  (let [e (element-at m x y)]
    (* (scenic-score-elem-dir e (right m x y))
       (scenic-score-elem-dir e (reverse (left m x y)))
       (scenic-score-elem-dir e (reverse (top m x y)))
       (scenic-score-elem-dir e (bottom m x y)))))

(defn max-scenic-score [m]
  (apply max (for [x (range 1 (width m))
                   y (range 1 (height m))]
               (scenic-score-elem m x y))))

(defn part2 []
  (let [lines (line-seq (io/reader "../input/day8.txt"))
        matrix (apply vector (map parse-line lines))]
    (max-scenic-score matrix)))

