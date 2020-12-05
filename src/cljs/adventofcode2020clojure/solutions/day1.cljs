(ns adventofcode2020clojure.solutions.day1
  (:require [clojure.string :refer [split-lines]]))

(defn part1
  [input]
  (let [lines (map int (split-lines input))]
    (first (for [x lines
                 y lines
                 :let [z (+ x y)]
                 :when (= z 2020)]
             (* x y)))))

(defn part2
  [input]
  (let [lines (map int (split-lines input))]
    (first (for [x lines
                 y lines
                 z lines
                 :let [a (+ x y z)]
                 :when (= a 2020)]
             (* x y z)))))