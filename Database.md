# 1 What is denomalization? and compare it with nomalization.
  Denormalization is the inverse process of normalization where the redundancy is added to the data intentionally to improve the performance of the specific application and data integrity. The reason for performing denormalization is the overheads produced in the query processor by an over-normalized structure. Denormalization reduces the number of tables, and the complicated table joins because a higher number of joins can slow down the process.

  Normalization and denormalization are useful according to the situation. Normalization is used when the faster insertion, deletion and update anomalies, and data consistency are necessarily required. On the other hand, Denormalization is used when the faster search is more important and to optimize the read performance. It also lessens the overheads created by over-normalized data or complicated table joins.

# 2.2 SQL view and stored procedure

A VIEW in SQL is like a virtual table that contains data from one or multiple tables. It does not hold any data and does not exist physically in the database. Similar to a SQL table, the view name should be unique in a database. It contains a set of predefined SQL queries to fetch data from the database.

A stored procedure is **a set of Structured Query Language (SQL) statements with an assigned name**, which are stored in a relational database management system (RDBMS) as a group, so it can be reused and shared by multiple programs.



# 2.3 material view vs view

Materialized view are similar to regular views, in that they are a logical view of your data (based on a select statement), however, the **underlying query result set has been saved to a table**. The upside of this is that when you query a materialized view, **you are querying a table**, which may also be indexed.

Materialized views are disk based and are updated periodically based upon the query definition.

Views are virtual only and run the query definition each time they are accessed.



# 3.1 Optimistic Lock and Pessimistic Lock

There are two models for locking data in database:

- **Pessimistic concurrency control** (or *pessimistic locking*) is called "pessimistic" because the system assumes the worst — it  assumes that two or more users will want to update the same record at  the same time, and then prevents that possibility by locking the record, no matter how unlikely conflicts actually are.

  The locks are placed  as soon as any piece of the row is accessed, making it impossible for  two or more users to update the row at the same time. Depending on the  lock mode (*shared*, *exclusive*, or *update*), other users might be able to read the data even though a lock has been placed.

- **Optimistic concurrency control** (or *optimistic locking*) assumes that although conflicts are possible, they will be very rare.  Instead of locking every record every time that it is used, the system  merely looks for indications that two users actually did try to update  the same record at the same time. If that evidence is found, then one  user's updates are discarded and the user is informed.

  For example, if User1  updates a record and User2 only wants to read it, then User2 simply  reads whatever data is on the disk and then proceeds, without checking  whether the data is locked. User2 might see slightly out-of-date  information if User1 has read the data and updated it, but has not yet  committed the transaction.

#  3.2  How to solve the deadlock?

Source: https://www.geeksforgeeks.org/deadlock-in-dbms/



**Wait-Die Scheme –** 
In this scheme, If a transaction requests a resource that is locked by another transaction, then the DBMS simply checks the timestamp of both transactions and allows the older transaction to wait until the resource is available for execution. 
Suppose, there are two transactions T1 and T2, and Let the timestamp of any transaction T be TS (T). Now, If there is a lock on T2 by some other transaction and T1 is requesting for resources held by T2, then DBMS performs the following actions: 



**Wound Wait Scheme –** 
In this scheme, if an older transaction requests for a resource held by a younger transaction, then an older transaction forces a younger transaction to kill the transaction and release the resource. The younger transaction is restarted with a minute delay but with the same timestamp. If the younger transaction is requesting a resource that is held by an older one, then the younger transaction is asked to wait till the older one releases it. 



# 3.3 Saga design pattern and compare it with 2pc

The Saga architecture pattern **provides transaction management using a sequence of local transactions**. A local transaction is the unit of work performed by a saga participant. Every operation that is part of the Saga can be rolled back by a compensating transaction. Further, the Saga pattern guarantees that either all operations are complete successfully or the corresponding compensation transactions are run to undo the work previously completed.

In the Saga pattern, **a compensating transaction must be idempotent and retryable**. These two principles ensure that a transaction can be managed without any manual intervention. The Saga Execution Coordinator (SEC) ensures guarantees these principles.



Comparisons between 2PC and Saga:  (coming from https://stackoverflow.com/questions/48906817/2pc-vs-sagas-distributed-transactions)

- Typically, 2PC is for *immediate* transactions.
- Typically, Sagas are for *long running* transactions.

Use cases are obvious afterwards:

- 2PC can allow you to commit the whole transaction in a request or so, spanning this request across systems and networks. Assuming each participating system and network follows the protocol, you can commit or rollback the entire transaction seamlessly.
- Saga allows you split transaction into multiple steps, spanning long periods of times (not necessarily systems and networks).

Example:

- 2PC: Save Customer for every received Invoice request, while both are managed by 2 different systems.
- Sagas: Book a flight itinerary consisting of several connecting flights, while each individual flight is operated by different airlines.

I personally consider Saga capable of doing what 2PC can do. Opposite is not accurate.

I think Sagas are universal, while 2PC involves platform/vendor lockdown.

**Updates/Additions** (optional read):

My answer has been here for a while, and I see that the topic has gained some traction since.

I want to clarify a couple of points on this topic for those who come here and are not sure which route to take.

1. Saga is a domain modeling (i.e., technology-agnostic) concept, while 2PC is a technology-specific notion with some (maybe many) vendors implementing it. For an analogy, it's the same if we compare the domain events (bare objects) with message brokers (such as RabbitMQ for example).
2. 2PC can be a good choice if you are anyway married to platforms that implement such a protocol. Not all do, and thus I call this a limitation. I see that people found an argument that Saga is more limiting because it's harder to implement, but that's like saying orange is juicier than apple is sweet. Two different things.
3. Consider the human factor too. Some people (developers, architects) are technology geeks. They call business logic or domain model a boilerplate code. I belong to another group of people who consider the domain model the most valuable piece of code. Such a preference also affects decisions between Saga and 2PC, as well as who likes what. I can't explain why you should prefer domain-driven thinking over technology-driven solutions because it won't fit on this page and you will abandon reading my answer. Please find more online, maybe through my writings.





# 3.3 What are live lock and dead lock



# 3.4 Distributed Transaction

