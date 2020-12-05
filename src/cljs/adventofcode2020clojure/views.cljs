(ns adventofcode2020clojure.views
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [adventofcode2020clojure.solutions.day1 :as solutionday1]
   [adventofcode2020clojure.subs :as subs]))

(defn input-form
  [{:keys [day part func]}]
  (let [in (r/atom "")
        answer (r/atom 0)]
    (fn []
      [:div
       [:h3 (str "Day " day " Part " part)]
       [:div
        [:textarea
         {:value @in
          :on-change #(reset! in (-> % .-target .-value))}]
        [:input
         {:type "button"
          :value "Submit"
          ;; :on-click #(println @in)}]]
          :on-click #(reset! answer (func @in))}]]
       [:div "Answer: " @answer]])))

;; day1
(defn day1-panel []
  [:div
    [input-form {:day 1 :part 1 :func solutionday1/part1}]
    [input-form {:day 1 :part 2 :func solutionday1/part2}]])

;; main
(defn- panels [panel-name]
  (case panel-name
    :day1-panel [day1-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (rf/subscribe [::subs/active-panel])]
    [:div {:class "app"}
     [:div {:class "header"}
      [:h1 (str "Advent of Code 2020 in Clojurescript")]
      [:p
       [:a {:href "https://github.com/michaelknowles/adventofcode2020clojure"} "Code"]
       " by "
       [:a {:href "https://mikeknowl.es"} "Mike Knowles"]]]
     [:div {:class "solutions-container"}
      [:div
       [:h2 "Solutions"]
       [:a {:href "#/day1"} "Day 1"]]
      [show-panel @active-panel]]]))
