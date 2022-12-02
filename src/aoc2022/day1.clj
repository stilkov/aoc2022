(ns aoc2022.day1)

(require '[clojure.string :as str])

(defn sum-up [elf] (reduce + (map #(Integer/parseInt %) (str/split elf #"\n"))))

(defn part1 [] 
  (reduce max (map sum-up (str/split (slurp "./input/day1.txt") #"\n\n"))))

(defn part2 [] 
  (reduce + (take 3 (sort > (map sum-up (str/split (slurp "./input/day1.txt") #"\n\n"))))))

