(ns aoc2022.day10)

(require '[clojure.string :as str]
         '[clojure.java.io :as io])

(defn addx [cycles val]
  (let [x (first cycles)] (conj cycles x (+ x val))))

(defn noop [cycles]
  (conj cycles (first cycles)))

(defn process-line [cycles line]
  (let [[cmd arg] (str/split line #" ")]
    (case cmd
      "addx" (addx cycles (parse-long arg))
      "noop" (noop cycles))))

(defn signal-strength [lines]
  (let [values (reverse (reduce process-line '(1) lines))
        positions '(20 60 100 140 180 220)]
    (reduce + (map #(* (nth values (dec %)) %) positions))))

(defn part1 []
  (signal-strength (line-seq (io/reader "../input/day10.txt"))))

(defn close [x p]
  (<= (abs (- x p)) 1))

(defn visible [values cycle]
  (if (close (mod cycle 40) (nth values cycle)) "#" "."))

(defn pixels [lines]
  (let [values (reverse (reduce process-line '(1) lines))
        positions '(20 60 100 140 180 220)]
    (str/join "\n" (map #(apply str %)
                        (partition 40 (map #(visible values %) (range 241)))))))

(defn part2 []
  (pixels (line-seq (io/reader "../input/day10.txt"))))

  
  
