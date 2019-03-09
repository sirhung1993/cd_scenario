
# Scenario Exercise
Use whichever use whatever programming language and platform that you feel most
comfortable in.
Our team who’s working on a SaaS client receives a batch of CSV files every night; which we
need to load into a database. Our team uses this data to develop dashboards for the client,
and to try to make predictions on whether or not a lead will convert.
For the purposes of this exercise, we have given you one CSV file with ~10k rows; please
assume that you receive 1k rows each night.
Given the attached Lead List, please:

1. Define and architect a database using the available columns
a. Examine the columns – what columns do you think are relevant to creating a
prediction model?
2. Write a load and store function
3. Write a function to return “Hot” leads from the “Qualification Level” in JSON format.
Please return:
1. Your answers to the above
2. The JSON file output from above

## System requirements
- Java > 8.x
- Scala 2.11
- Spark 2.4.0
- sbt 1.2.7