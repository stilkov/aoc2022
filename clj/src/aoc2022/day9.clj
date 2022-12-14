(ns aoc2022.day9)

(require '[clojure.java.io :as io]
         '[clojure.string :as str])

(defn right-of [x1 y1 x2 y2] (> (- x2 x1) 1))
(defn left-of [x1 y1 x2 y2] (> (- x1 x2) 1))
(defn above [x1 y1 x2 y2] (> (- y2 y1) 1))
(defn below [x1 y1 x2 y2] (> (- y1 y2) 1))

(defn adjustment [hx hy tx ty]
  (if (right-of hx hy tx ty)
    [(inc hx) hy]
    (if (left-of hx hy tx ty)
      [(dec hx) hy]
      (if (below hx hy tx ty)
        [hx (dec hy)]
        (if (above hx hy tx ty)
          [hx (inc hy)])))))
      
(defn follow-tail-if-needed [bridge]
  (let [{:keys [h-pos-x h-pos-y t-pos-x t-pos-y tail-history]} bridge]
    (if-let [[tx ty] (adjustment h-pos-x h-pos-y t-pos-x t-pos-y)]
      (-> bridge
          (update :tail-history #(conj % [t-pos-x t-pos-y]))
          (assoc :t-pos-x tx)
          (assoc :t-pos-y ty))
      bridge)))
             
(defn move [bridge dir follow-fn]
  (let [{:keys [h-pos-x h-pos-y]} bridge]
    (follow-fn (case dir
                             "R" (update bridge :h-pos-x inc)
                             "L" (update bridge :h-pos-x dec)
                             "U" (update bridge :h-pos-y inc)
                             "D" (update bridge :h-pos-y dec)))))

  
(defn move-n [bridge dir count follow-fn]
  (let [moved (move bridge dir follow-fn)]
    (if (= count 1)
      moved
      (move-n moved dir (dec count) follow-fn))))

(defn process-line [bridge line follow-fn]
  (let [[dir cnt] (str/split line #" ")] (move-n bridge dir (parse-long cnt) follow-fn)))


(defn process-lines [lines follow-fn]
  (let [bridge {:h-pos-x 0, :h-pos-y 0, :t-pos-x 0, :t-pos-y 0, :tail-history #{}}
        {:keys [t-pos-x t-pos-y tail-history]} (reduce #(process-line %1 %2 follow-fn) bridge lines)]
    (count (conj tail-history [t-pos-x t-pos-y]))))

(defn part1 []
  (let [lines (line-seq (io/reader "../input/day9.txt"))]
    (process-lines lines follow-tail-if-needed)))

(defn follow-tail-if-needed-10 [bridge]
  (let [{:keys [h-pos-x h-pos-y t-pos-x t-pos-y tail-history]} bridge]
    (if-let [[tx ty] (adjustment h-pos-x h-pos-y t-pos-x t-pos-y)]
      (-> bridge
          (update :tail-history #(conj % [t-pos-x t-pos-y]))
          (assoc :t-pos-x tx)
          (assoc :t-pos-y ty))
      bridge)))
             

(defn part2 []
  (let [lines (line-seq (io/reader "../input/day9.txt"))]
    (process-lines lines follow-tail-if-needed-10)))

