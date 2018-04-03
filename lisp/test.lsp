(defun test_fsa(test_string)
  (defvar start_state 0)
  (defvar final_states (cons 1 '(3)))
  (defvar matrix (cons '(x y nil nil nil) '('(nil nil x nil nil)
    '(nil nil x y nil) '(nil nil nil x z) '(nil a nil nil x))))
  (defvar current_state start_state)

  (dotimes (i (length matrix))
    (setq ch (subseq test_string i (+ i 1)))
    (loop for j in (nth current_state matrix) do
      ((if (= ch j) (setq current_state j)
        (if (= j (- (length matrix) 1)) nil)))))

  (loop for k in final_states do
    ((if (= current_state k) t (if (= k (length final_states)) nil)))))

(defun read_file(file_name)
  (setq fp (open file_name :direction :input))
  (read fp "done"))

(defun demo()
  (run "theString.txt"))

(defun run(file_name)
  (if (test_fsa (read_file file_name))
    (print "String valid.")
    (print "string invalid.")))
