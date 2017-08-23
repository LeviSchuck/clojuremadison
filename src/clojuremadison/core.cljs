(ns clojuremadison.core)

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state
  (atom {:text "Hello world!", :active 1, :counter 0}))

(defn clock []
  (swap! app-state update-in [:counter] inc)
  (js/setRedrawAlpha (/ (mod (:counter @app-state) 100) 100))
  )

(defn incstate []
  (swap! app-state update-in [:active] inc)
  (js/setActiveCount (:active @app-state))
  )
(defn reset []
  (swap! app-state update-in [:active] (constantly 0))
  (js/setActiveCount 0)
  )

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  (js/setFrameCallback clock)
)

; Consider this like main
(js/setActiveCount (:active @app-state))
(js/setFrameCallback clock)
