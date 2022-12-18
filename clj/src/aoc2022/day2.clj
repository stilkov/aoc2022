(ns aoc2022.day2)

(require '[clojure.string :as str]
         '[clojure.java.io :as io])

(def score-map {"A" { "X" (+ 1 3), "Y" (+ 2 6), "Z" (+ 3 0) },
                "B" { "X" (+ 1 0), "Y" (+ 2 3), "Z" (+ 3 6) },
                "C" { "X" (+ 1 6), "Y" (+ 2 0), "Z" (+ 3 3) }})

(defn score [l]
  (let [[a b] (str/split l #" ")]
    (get-in score-map [a b])))
        

(defn part1 []
  (reduce + (map score (line-seq (io/reader "../input/day2.txt")))))


(def win-map {"A" { "X"  "Z", "Y"  "X", "Z"  "Y" },
              "B" { "X"  "X", "Y"  "Y", "Z"  "Z" },
              "C" { "X"  "Y", "Y"  "Z", "Z"  "X" }})

(defn score-by-win [l]
  (let [[a win] (str/split l #" ")
        b (get-in win-map [a win])]
    (get-in score-map [a b])))
        

(defn part2 []
  (reduce + (map score-by-win (line-seq (io/reader "../input/day2.txt")))))


