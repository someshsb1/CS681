HW 14:
Revised Observable class to be thread-safe.

• Implemented StockQuoteObservable ina thread-safe manner.
– Used 2 locks to guard 2 shared variables. LockT (Ticker), LockQ(Quote)
• Implemented StockEvent as a record
• Tested the code with 10+ threads.
