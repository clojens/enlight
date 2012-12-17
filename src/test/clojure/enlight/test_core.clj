(ns enlight.test-core
  (:use [enlight core])
  (:require [enlight.samples.demo :as d])
  (:require [mikera.vectorz.core :as v])
  (:import [java.awt.image BufferedImage])
  (:use [clojure test]))

(deftest test-camera
  (testing "creating"
    (let [camera (compile-camera nil)]
      (is (not= camera nil))))
  (testing "compiling graph with cameras"
    (let [graph (compile-scene-list [:camera :camera])]
      (is (graph :camera))))) 

(deftest expected-scene-errors
  (testing "compiling graph with no camera"
    (is (thrown? Throwable (render [])))))

(deftest test-render
  (testing "Basic render"
    (let [^BufferedImage im (render d/EXAMPLE-SCENE :width 20 :height 20)]
      (is (= 20 (.getWidth im))))))

(deftest test-vector-compile
  (let [c (compile-all [1 2 3])]
    (is (v/vec? c))
    (is (= 3 (v/length c))))
  (let [c (compile-all [1])]
    (is (v/vec? c))
    (is (= 1 (v/length c)))))

(deftest test-sphere-compile
  (let [c (compile-all [:sphere])]
    (is (scene-object? c))
    (is (= :sphere (:type c)))
    (is (= 1.0 (:radius c))))
  (let [c (compile-all [:sphere [1 1 1] 2])]
    (is (scene-object? c))
    (is (= :sphere (:type c)))
    (is (= 2.0 (:radius c)))
    (is (= (v/vec [1 1 1]) (:centre c)))))

(deftest test-sky-sphere-compile
  (let [c (compile-all [:sky-sphere])]
    (is (scene-object? c))
    (is (= :sky-sphere (:type c)))))

(deftest test-union-compile
  (let [c (compile-all [:union])]
    (is (scene-object? c))
    (is (= :union (:type c)))))