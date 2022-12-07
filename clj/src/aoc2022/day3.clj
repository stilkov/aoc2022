(ns aoc2022.day3)

(require '[clojure.java.io :as io])

(defn compartments [s]
  (let [l (count s) m (/ l 2)]
    [(subs s 0 m) (subs s m l)]))

(defn priority-for-char [c]
  (let [p (inc (- (int c) (int \A)))]
    (if (Character/isUpperCase c) (+ 26 p) (- p 32))))

(defn common-elements [a b]
  (filter #(contains? (set b) %) (set a)))

(defn priority [s]
  (let [[a b] (compartments s)]
    (reduce + (map priority-for-char (common-elements a b)))))

(def lines (line-seq (io/reader "./input/day3.txt")))

(defn part1 []
  (reduce + (map priority lines)))

(defn common-elements-in-list [l]
  (reduce common-elements l))

(defn part2 []
  (reduce + (map priority-for-char
                 (flatten (map common-elements-in-list (partition 3 lines))))))



