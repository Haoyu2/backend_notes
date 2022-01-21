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
