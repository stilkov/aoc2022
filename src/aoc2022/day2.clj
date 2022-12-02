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
  (reduce + (map score (line-seq (io/reader "./input/day2.txt")))))

(def win-map {"A" { "X"  3, "Y"  6, "Z"  0 },
              "B" { "X"  0, "Y"  3, "Z"  6 },
              "C" { "X"  6, "Y"  0, "Z"  3 }})

