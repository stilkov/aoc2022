(ns aoc2022.day1)

(require '[clojure.string :as str])

(defn split-elves [s]
  (str/split s #"\n\n"))

(defn sum-up [elf] (reduce + (map parse-long (str/split-lines elf))))

(def elves-calories (map sum-up (split-elves (slurp "../input/day1.txt"))))

(defn part1 [] 
  (reduce max elves-calories))

(defn part2 [] 
  (reduce + (take 3 (sort > elves-calories))))

