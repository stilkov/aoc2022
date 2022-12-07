(ns aoc2022.core
  (:gen-class))

(require '[aoc2022.day1 :as day1]
         '[aoc2022.day2 :as day2]
         '[aoc2022.day3 :as day3])


(defn -main
  
  [] 
  (println "Day 1 (1st half)" (day1/part1))
  (println "Day 1 (2nd half)" (day1/part2))
  (println "Day 2 (1st half)" (day2/part1))
  (println "Day 2 (2nd half)" (day2/part2))
  (println "Day 3 (1st half)" (day3/part1))
  (println "Day 3 (2nd half)" (day3/part2)))
