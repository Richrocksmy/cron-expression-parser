# Cron Expression Parser

A small command line utility that parses a cron string and displays the times at which the expression would cause an application to run

---

## Description

This application can be used to parse a given cron expression and then display it in an easy to read table format as console output

## Prequisites

- Java 16 :coffee:
- Gradle

---

## Usage :notebook_with_decorative_cover:

Cron expressions must be in the form:

```<minute> <hour> <day-of-month> <month> <day-of-week> <command>```

Where the fields are in the following format:

| Field               | Allowed Numerical Values | Allowed Special Values |
|---------------------|--------------------------|------------------------|
|```<minute>```       | 0 - 59                   |, - * /                 |
|```<hour>```         | 0 - 59                   |, - * /                 |
|```<day-of-month>``` | 1 - 31                   |, - * ? /               |
|```<month>```        | 1 - 12                   |, - * /                 |
|```<day-of-week>```  | 1 - 7                    |, - * ? /               |

For a detailed explanation on how to use or create a cron expression, [this](https://crontogo.com/blog/the-complete-guide-to-cron/) is a handy guide.

### Running the Application :rocket:

The application must be supplied with the cron expression (in the format mention in the previous 'Usage' section) as a *single string input*. The application can then
be executed in your preferred manner:

#### In the IDE

Using your IDE of choice, run the ```main()``` method in the ```org.richrocksmy.cronexpressionparser.Application``` class (don't forget to setup the input parameters in the run configuration!)

(In some versions of IntelliJ this causes an ```The JavaExec.main property has been deprecated. This is scheduled to be removed in Gradle 8.0. Please use the mainClass property instead.```)

#### In the terminal using Gradle wrapper

In the root of the project directory, run:

```./gradlew run --args='"*/15 0 1,15 * 1-5 /usr/bin/find"'```

#### In the terminal as a jar

In the root of the project directory, first build the jar using:


Then execute the jar using:

java -jar "*/15 0 1,15 * 1-5 /usr/bin/find"


---

## Additional Functionality not supported in this version

Certain cron functionality has been marked as out of scope for this implementation, the following items are _not_ supported in this implementation of the parser:

- ```<month>``` - Support for 3 character month strings (JAN - DEC) <br>
- ```<day-of-week>``` - Support for 3 character day strings (SUN-SAT) <br>
- ```<day-of-week>``` - Support 'L' (last) and # (nth day of the month) <br>
- ```<day-of-month>``` - Support 'L' (last) and 'W' (weekday) <br>
- Other special timeUnit input strings such as ```@yearly```
