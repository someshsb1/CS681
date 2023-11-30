HW 12: ThreadSafeBankAccount2, 2-Step Thread Termination to make bank app terminate gracefully.

Completed 2-step thread termination in ThreadSafeBankAccount2.java
– in WithdrawRunnable and DepositRunnable classes
– in withdraw() and deposit() threads.

Processing multiple “withdrawal” and “deposit” threads
The main thread terminates both “withdrawal” and “deposit” threads by calling interrupt.
