HW 17:
Revised Observable class to be thread-safe.

Revised the HW14 code by replacing the LinkedList with a ConcurrentLinkedQueue in Observable.java
– Revised Observable.java wihout using a ReentrantLock data field.
• Used forEach() for bulk operations to implement notifyObservers() and replaced HashMap with ConcurrentHashMap
