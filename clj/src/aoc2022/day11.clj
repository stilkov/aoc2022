(ns aoc2022.day11)

(require '[clojure.string :as str])

(defn build-op-fn [op arg]
  (let [op-fn (resolve (symbol op))]
    (if (= arg "old") (fn [x] (op-fn x x)) (partial op-fn (parse-long arg)))))

(defn parse-monkey-rule [s]
  (let [[matches id items operation argument divisor true-monkey false-monkey]
        (re-matches #".*(\d+):\n  Starting items: (.*)\n  Operation: new = old (.) (old|\d+)\n  Test: divisible by (\d+)\n    If true: throw to monkey (\d+)\n    If false: throw to monkey (\d+)\n*" s)]
    {(parse-long id) {:items (apply vector (map parse-long (str/split items #", ")))
                      :op-fn (build-op-fn operation argument)
                      :divisor (parse-long divisor)
                      :true-monkey (parse-long true-monkey)
                      :false-monkey (parse-long false-monkey)
                      :inspections 0}}))

(defn item-move [monkeys index]
  (let [monkey (get monkeys index)
        items (:items monkey)]  
    (if (empty? items)
      monkeys
      (let [item (first items)]
 ;;      (println "item-move for item" item)
        (let [new-worry-level ((:op-fn monkey) item)
              bored-level (quot new-worry-level 3)]
          (let [destination (if (= (mod bored-level (:divisor monkey)) 0)
                              (:true-monkey monkey)
                              (:false-monkey monkey))
                monkeys (-> monkeys
                            (assoc-in [index :items] (apply vector (rest items)))
                            (update-in [index :inspections] inc)
                            (update-in [destination :items] #(conj % bored-level)))]
            ;; (println "Throwing" bored-level "to monkey" destination)
            ;; (println "items at Monkey" index ":" (get-in monkeys [index :items]))
            ;; (println "items at Monkey" destination ":" (get-in monkeys [destination :items]))
            (item-move monkeys index)))))))
     
(defn monkey-move [monkeys index]
;;  (println "Move for monkey" index)
    (item-move monkeys index))
  
(defn play-rounds [monkeys n]
  (if (= n 0)
    monkeys
    (play-rounds (reduce monkey-move monkeys (range (count monkeys))) (dec n))))

(defn inspections-after-round [monkeys n]
  (map :inspections (vals (play-rounds monkeys n))))
  
(defn part1 []
  (let [lines (str/split (slurp "../input/day11.txt") #"\n\n")
        monkeys (apply merge (map parse-monkey-rule lines))]
    (apply * (take 2 (sort > (inspections-after-round monkeys 20))))))

