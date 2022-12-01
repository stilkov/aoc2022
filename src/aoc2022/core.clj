(ns aoc2022.core
  (:gen-class))

(require '[clojure.string :as str])

(defn sum-up [elf] (reduce + (map #(Integer/parseInt %) (str/split elf #"\n"))))
(defn day-1-1 [] 
  (reduce max (map sum-up (str/split (slurp (java.io.FileReader. "./input/day1.txt")) #"\n\n"))))

(defn day-1-2 [] 
  (reduce + (take 3 (sort > (map sum-up (str/split (slurp (java.io.FileReader. "./input/day1.txt")) #"\n\n"))))))

(defn -main
  [] 
  (println "Day 1 (1st half)" (day-1-1))
  (println "Day 1 (2nd half)" (day-1-2)))

