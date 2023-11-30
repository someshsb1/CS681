HW 12: Multi-threaded Visitors

Completed 2-step thread termination in ThreadSafeBankAccount2.java
– in WithdrawRunnable and DepositRunnable classes
– in withdraw() and deposit() threads.

Processing multiple “withdrawal” and “deposit” threads
The main thread terminates both “withdrawal” and “deposit” threads by calling interrupt.
